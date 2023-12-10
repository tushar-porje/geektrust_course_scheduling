package com.geektrust.backend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Data
@Getter
@Setter
public class CourseDto {
    private String courseId;
    private final String courseName;
    private final String instructor;
    private final String date;
    private final int minEmployee;
    private final int maxEmployee;
    private boolean isAllotted;
    private boolean isCancelled;
    private List<EmployeeDto> registeredEmployees;

    public CourseDto( String courseName, String instructor, String date,
            int minEmployee, int maxEmployee, boolean isAllotted, boolean isCancelled,
            List<EmployeeDto> registeredEmployees) {
        this.courseName = courseName;
        this.instructor = instructor;
        this.date = date;
        this.minEmployee = minEmployee;
        this.maxEmployee = maxEmployee;
        this.isAllotted = isAllotted;
        this.isCancelled = isCancelled;
        this.registeredEmployees = registeredEmployees;
    }

    public CourseDto(String courseId, String courseName, String instructor, String date,
            int minEmployee, int maxEmployee, boolean isAllotted, boolean isCancelled,
            List<EmployeeDto> registeredEmployees) {
        this(courseName,instructor,date,minEmployee,maxEmployee,isAllotted,isCancelled,registeredEmployees);
        this.courseId = courseId;
    }
    
}
