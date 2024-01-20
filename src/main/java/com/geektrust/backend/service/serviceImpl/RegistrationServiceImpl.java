package com.geektrust.backend.service.serviceImpl;

import com.geektrust.backend.dto.CourseDto;
import com.geektrust.backend.dto.EmployeeDto;
import com.geektrust.backend.dto.RegistrationDto;
import com.geektrust.backend.exception.CourseAlreadyAllotedException;
import com.geektrust.backend.exception.CourseCanceledException;
import com.geektrust.backend.exception.InvalidInputException;
import com.geektrust.backend.exception.RegistrationCancelException;
import com.geektrust.backend.model.Employee;
import com.geektrust.backend.repository.CourseRepository;
import com.geektrust.backend.repository.EmployeeRepository;
import com.geektrust.backend.repository.RegistrationRepository;
import com.geektrust.backend.service.RegistrationService;
import com.geektrust.backend.utils.Constant;
import com.geektrust.backend.utils.EmailValidator;

public class RegistrationServiceImpl implements RegistrationService{

    private final CourseRepository courseRepository;
    private final EmployeeRepository employeeRepository;
    private final RegistrationRepository registrationRepository;

    public RegistrationServiceImpl(CourseRepository courseRepository,EmployeeRepository employeeRepository, RegistrationRepository registrationRepository) {
        this.courseRepository = courseRepository;
        this.employeeRepository = employeeRepository;
        this.registrationRepository = registrationRepository;
    }

    @Override
    public String create(RegistrationDto registrationDto) {
        if(!EmailValidator.validate(registrationDto.getEmailAddress())){
            throw new InvalidInputException(Constant.INPUT_DATA_ERROR_MESSAGE);
        }
        validateCourse(registrationDto.getCourseID());
        if(seatsAvailability(registrationDto.getCourseID())){
            boolean b = employeeRepository.existsById(registrationDto.getEmailAddress());
            if(!b) employeeRepository.save(new EmployeeDto(registrationDto.getEmailAddress()));
            // if(!alreadyRegistered(registrationDto)){
            registrationDto.setRegID(getRegistrationId(registrationDto));
            registrationDto.setAccepted(true);
            String regId = registrationRepository.save(registrationDto);
            return regId;
        }else{
            throw new InvalidInputException(Constant.COURSE_FULL_ERROR_MESSAGE);
        }
    }
    
    private String getRegistrationId(RegistrationDto registrationDto) {
        String courseName=getCourseName(registrationDto);
        String employeeName=getEmployeeName(registrationDto);
        return Constant.REGCOURSE+employeeName+Constant.HYPHEN+courseName;
    }

    private String getNameFromCourseRepository(String courseId) {
        CourseDto courseDto = courseRepository.findById(courseId).orElseThrow(() -> new InvalidInputException(Constant.INPUT_DATA_ERROR_MESSAGE));
        return courseDto.getCourseName();
    }
    
    private String getNameFromEmployeeRepository(String emailAddress) {
        EmployeeDto employeeDto = employeeRepository.findById(emailAddress).orElseThrow(() -> new InvalidInputException(Constant.INPUT_DATA_ERROR_MESSAGE));
        return new Employee(employeeDto.getEmailAddress()).getName();
    }
    
    private String getCourseName(RegistrationDto registrationDto) {
        return getNameFromCourseRepository(registrationDto.getCourseID());
    }
    
    private String getEmployeeName(RegistrationDto registrationDto) {
        return getNameFromEmployeeRepository(registrationDto.getEmailAddress());
    }
    
    private boolean validateCourse(String courseId){
        CourseDto courseDto=courseRepository.findById(courseId).orElseThrow(()->new InvalidInputException(Constant.INPUT_DATA_ERROR_MESSAGE));
        if(courseDto.isAllotted()) throw new CourseAlreadyAllotedException(Constant.COURSE_ALREADY_ALLOTED);
        if(courseDto.isCancelled()) throw new CourseCanceledException(Constant.COURSE_CANCELLED);
        return true;
    }

    private boolean seatsAvailability(String courseId) {
        long count=registrationRepository.findAllByCourseId(courseId).stream().filter(RegistrationDto->RegistrationDto.isAccepted()==true).count();
        CourseDto courseDto=courseRepository.findById(courseId).get();
        long max=courseDto.getMaxEmployee();
        return (count<max)?true:false;
    }

    @Override
    public String cancelRegistration(String RegId) throws InvalidInputException , RegistrationCancelException{
        RegistrationDto registrationDto=registrationRepository.findById(RegId).orElseThrow(()-> new InvalidInputException(Constant.INPUT_DATA_ERROR_MESSAGE));
        CourseDto courseDto=courseRepository.findById(registrationDto.getCourseID()).get();
        if(courseDto.isAllotted()!=true ){
            registrationDto.setAccepted(false);
            registrationRepository.save(registrationDto);
            return registrationDto.getRegID();
        }else{
            throw new RegistrationCancelException(Constant.CANCEL_REJECTED_MESSAGE);
        }
    }
}
