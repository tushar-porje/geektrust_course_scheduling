package com.geektrust.backend.service.serviceImpl;

import com.geektrust.backend.dto.CourseDto;
import com.geektrust.backend.dto.EmployeeDto;
import com.geektrust.backend.dto.RegistrationDto;
import com.geektrust.backend.exception.InvalidInputException;
import com.geektrust.backend.model.Employee;
import com.geektrust.backend.repository.CourseRepository;
import com.geektrust.backend.repository.EmployeeRepository;
import com.geektrust.backend.repository.RegistrationRepository;
import com.geektrust.backend.service.RegistrationSevice;
import com.geektrust.backend.utils.Constant;
import com.geektrust.backend.utils.EmailValidator;

public class RegistrationServiceImpl implements RegistrationSevice{

    private final CourseRepository courseRepository;
    private final EmployeeRepository employeeRepository;
    private final RegistrationRepository registrationRepository;


    public RegistrationServiceImpl(CourseRepository courseRepository,
            EmployeeRepository employeeRepository, RegistrationRepository registrationRepository) {
        this.courseRepository = courseRepository;
        this.employeeRepository = employeeRepository;
        this.registrationRepository = registrationRepository;
    }


    @Override
    public String create(RegistrationDto registrationDto) {
        if(!EmailValidator.validate(registrationDto.getEmailAddress())){
            throw new InvalidInputException(Constant.INPUT_DATA_ERROR_MESSAGE);
        }
        if(isCoursePresentAndNotAlloted(registrationDto.getCourseID()) && seatsAvailability(registrationDto.getCourseID())){
            // boolean b = employeeRepository.existsById(registrationDto.getEmailAddress());
            // if (!b) {
                employeeRepository.save(new EmployeeDto(registrationDto.getEmailAddress()));
            // }
            // if(!alreadyRegistered(registrationDto)){
                registrationDto.setRegID(getRegistrationId(registrationDto));
                registrationDto.setAccepted(true);
                String regId = registrationRepository.save(registrationDto);
                return regId;
            // }else{
            //     throw new InvalidInputException(Constant.INPUT_DATA_ERROR_MESSAGE);
            // }
        }else{
            throw new InvalidInputException(Constant.COURSE_FULL_ERROR_MESSAGE);
        }
    }
    
    private String getRegistrationId(RegistrationDto registrationDto) {
        String courseName=getCourseName(registrationDto);
        String employeeName=getEmployeeName(registrationDto);
        return "REG-COURSE-"+employeeName+"-"+courseName+"";
    }
    private String getCourseName(RegistrationDto registrationDto){
        String courseId=registrationDto.getCourseID();
        return courseRepository.findById(courseId).get().getCourseName();
    }

    private String getEmployeeName(RegistrationDto registrationDto){
        String emailAddress=registrationDto.getEmailAddress();
        return new Employee(emailAddress).getName();
    }

    // private boolean alreadyRegistered(RegistrationDto registrationDto) {
    //     RegistrationDto RegistrationDto=registrationRepository.findById(getRegistrationId(registrationDto)).orElseThrow(()-> new InvalidInputException(Constant.CANCEL_REJECTED_MESSAGE));
    //     if (RegistrationDto.isAccepted()==false) {
    //         return true;
    //     } else {
    //         return false;
    //     }
    // }


    @Override
    public boolean isCoursePresentAndNotAlloted(String courseId){
        CourseDto courseDto=courseRepository.findById(courseId).orElse(null);
        if(courseDto!=null){
            return (courseDto.isAllotted()==true)?false:true;
        }else{
            return false;
        }
    }


    @Override
    public boolean seatsAvailability(String courseId) {
        long count=registrationRepository.findAllByCourseId(courseId).stream().filter(RegistrationDto->RegistrationDto.isAccepted()==true).count();
        System.out.println(count+" +_+_");
        long max=courseRepository.findById(courseId).get().getMaxEmployee();
        // System.out.println(count+" "+max);
        return (count<max)?true:false;
    }


    @Override
    public String cancelRegistration(String RegId) throws InvalidInputException{
        RegistrationDto registrationDto=registrationRepository.findById(RegId).orElseThrow(()-> new InvalidInputException(Constant.CANCEL_REJECTED_MESSAGE));
        CourseDto courseDto=courseRepository.findById(registrationDto.getCourseID()).get();
        if(courseDto.isAllotted()!=true ){
            registrationDto.setAccepted(false);
            registrationRepository.save(registrationDto);
            return registrationDto.getRegID();
        }else{
            throw new InvalidInputException(Constant.CANCEL_REJECTED_MESSAGE);
        }
    }

    
}
