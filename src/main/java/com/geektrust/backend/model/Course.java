package com.geektrust.backend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import com.geektrust.backend.dto.EmployeeDto;

@Data
@Getter
@Setter
public class Course {
    private final String courseId;
    private final String courseName;
    private final String instructor;
    private final String date;
    private final int minEmployee;
    private final int maxEmployee;
    private boolean isAllotted;
    private boolean isCancelled;
    private List<EmployeeDto> registeredEmployees;

    public Course(String courseId, String courseName, String instructor, String date,
            int minEmployee, int maxEmployee, boolean isAllotted, boolean isCancelled,
            List<EmployeeDto> registeredEmployees) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructor = instructor;
        this.date = date;
        this.minEmployee = minEmployee;
        this.maxEmployee = maxEmployee;
        this.isAllotted = isAllotted;
        this.isCancelled = isCancelled;
        this.registeredEmployees = registeredEmployees;
    }

    @Override
    public String toString() {
        return "Course [courseId=" + courseId + ", courseName=" + courseName + ", date=" + date
                + ", instructor=" + instructor + ", isAllotted=" + isAllotted + ", isCancelled="
                + isCancelled + ", maxEmployee=" + maxEmployee + ", minEmployee=" + minEmployee
                + ", registeredEmployees=" + registeredEmployees + "]";
    }                           
    
}
