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
        //it will return courseId that is created in repo layer for the course object
        return courseRepository.save(courseDto);
    }


    @Override
    public List<AllotResponse> allot(String courseId) {
        //check course is present or not
        //get allregistered employee by course and whose isaccepted is true
        //check if course is not cancelled and minEmployee condition is meet
        CourseDto courseDto= courseRepository.findById(courseId).orElseThrow(()->new InvalidInputException(Constant.INPUT_DATA_ERROR_MESSAGE));
        List<RegistrationDto> allRegistration=registrationRepository.findAllByCourseId(courseId).stream().filter(registration->registration.isAccepted()==true).collect(Collectors.toList());
        String status="";
        List<AllotResponse> allotResponses=new ArrayList<>();
        List<EmployeeDto> employees=new ArrayList<>();
        if(isValidAllotation(courseDto,allRegistration.size())){
            courseDto.setAllotted(true);
            courseDto.setCancelled(false);
            status=Constant.ALLOT_COURSE_MESSAGE;
        }else{
            courseDto.setAllotted(false);
            courseDto.setCancelled(true);
            status=Constant.COURSE_CANCELLED;
        }
        for(RegistrationDto registrationDto:allRegistration){
            AllotResponse allotResponse=new AllotResponse(registrationDto.getRegID(), registrationDto.getEmailAddress(),
                                                         courseDto.getCourseId(), courseDto.getCourseName(), courseDto.getInstructor(), 
                                                         courseDto.getDate(), status);
            allotResponses.add(allotResponse);

            EmployeeDto employeeDto=employeeRepository.findById(registrationDto.getEmailAddress()).get();
            employees.add(employeeDto);
        }
        courseDto.setRegisteredEmployees(employees);
        courseRepository.save(courseDto);

        return allotResponses;
    }

    EmployeeDto getEmployee(String emailId){
        return employeeRepository.findById(emailId).get();
    }


    private boolean isValidAllotation(CourseDto courseDto,long totalEmployee) {
        return (courseDto.isCancelled()==false && courseDto.getMinEmployee()<=totalEmployee)?true:false;
    }

    
}
