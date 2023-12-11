package com.geektrust.backend.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.geektrust.backend.dto.AllotResponse;
import com.geektrust.backend.dto.CourseDto;
import com.geektrust.backend.dto.EmployeeDto;
import com.geektrust.backend.dto.RegistrationDto;
import com.geektrust.backend.exception.InvalidInputException;
import com.geektrust.backend.repository.CourseRepository;
import com.geektrust.backend.repository.EmployeeRepository;
import com.geektrust.backend.repository.RegistrationRepository;
import com.geektrust.backend.service.CourseService;
import com.geektrust.backend.utils.Constant;

public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final RegistrationRepository registrationRepository;
    private final EmployeeRepository employeeRepository;

    
    public CourseServiceImpl(CourseRepository courseRepository,RegistrationRepository registrationRepository,EmployeeRepository employeeRepository) {
        this.courseRepository = courseRepository;
        this.registrationRepository=registrationRepository;
        this.employeeRepository=employeeRepository;
    }


    @Override
    public String createCourse(CourseDto courseDto) {
        return courseRepository.save(courseDto);
    }


    @Override
    public List<AllotResponse> allot(String courseId) {
        //check course is present or not
        //get allregistered employee by course and whose isaccepted is true
        //check if course is not cancelled and minEmployee condition is meet
        CourseDto courseDto= courseRepository.findById(courseId).orElseThrow(()->new InvalidInputException(Constant.INPUT_DATA_ERROR_MESSAGE));
        List<RegistrationDto> allAcceptedRegistration=registrationRepository.findAllByCourseId(courseId).stream()
                        .filter(registration->registration.isAccepted()==true).collect(Collectors.toList());
        
        String status = getStatus(courseDto, allAcceptedRegistration.size());
        List<AllotResponse> allotResponses=new ArrayList<>();
        List<EmployeeDto> employees=new ArrayList<>();

        courseDto = updateCourseStatus(courseDto, status);
        // if(isValidAllotation(courseDto,allAcceptedRegistration.size())){
        //     status=Constant.ALLOT_COURSE_MESSAGE;
        //     courseDto=getRequiredCourseDto(courseDto,true,false);
        // }else{
        //     status=Constant.COURSE_CANCELLED;
        //     courseDto=getRequiredCourseDto(courseDto,false,true);
        // }
        
        for(RegistrationDto registrationDto:allAcceptedRegistration){
            AllotResponse allotResponse=getAllotedResponse(registrationDto,courseDto,status);                                                         
            allotResponses.add(allotResponse);

            // String emailAddress=registrationDto.getEmailAddress();
            // EmployeeDto employeeDto=employeeRepository.findById(emailAddress).get();
            EmployeeDto employeeDto = getEmployeeDetails(registrationDto.getEmailAddress());
            employees.add(employeeDto);
        }
        courseDto.setRegisteredEmployees(employees);

        courseRepository.save(courseDto);
        return allotResponses;
    }

    private String getStatus(CourseDto courseDto, int totalAcceptedRegistrations) {
        if (!courseDto.isCancelled() && courseDto.getMinEmployee() <= totalAcceptedRegistrations) {
            return Constant.ALLOT_COURSE_MESSAGE;
        } else {
            return Constant.COURSE_CANCELLED;
        }
    }

    private CourseDto updateCourseStatus(CourseDto courseDto, String status) {
        courseDto.setAllotted(status.equals(Constant.ALLOT_COURSE_MESSAGE));
        courseDto.setCancelled(status.equals(Constant.COURSE_CANCELLED));
        return courseDto;
    }

    private EmployeeDto getEmployeeDetails(String emailAddress) {
        return employeeRepository.findById(emailAddress).orElse(null);
    }

    private AllotResponse getAllotedResponse(RegistrationDto registrationDto,CourseDto courseDto,String status) {
        return new AllotResponse(registrationDto.getRegID(), registrationDto.getEmailAddress(),
        courseDto.getCourseId(), courseDto.getCourseName(), courseDto.getInstructor(),
        courseDto.getDate(), status);
    }


    // private CourseDto getRequiredCourseDto(CourseDto courseDto, boolean isAlloted, boolean isCancelled) {
    //     courseDto.setAllotted(isAlloted);
    //     courseDto.setCancelled(isCancelled);
    //     return courseDto;
    // }

    // private boolean isValidAllotation(CourseDto courseDto,long totalEmployee) {
    //     return (courseDto.isCancelled()==false && courseDto.getMinEmployee()<=totalEmployee)?true:false;
    // }

}
