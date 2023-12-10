package com.geektrust.backend.dto;

import java.util.List;


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

    public CourseDto(String courseId, String courseName, String instructor, String date,
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

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getDate() {
        return date;
    }

    public int getMinEmployee() {
        return minEmployee;
    }

    public int getMaxEmployee() {
        return maxEmployee;
    }

    public boolean isAllotted() {
        return isAllotted;
    }

    public void setAllotted(boolean isAllotted) {
        this.isAllotted = isAllotted;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public List<EmployeeDto> getRegisteredEmployees() {
        return registeredEmployees;
    }

    public void setRegisteredEmployees(List<EmployeeDto> registeredEmployees) {
        this.registeredEmployees = registeredEmployees;
    }

    @Override
    public String toString() {
        return "CourseDto [courseId=" + courseId + ", courseName=" + courseName + ", date=" + date
                + ", instructor=" + instructor + ", isAllotted=" + isAllotted + ", isCancelled="
                + isCancelled + ", maxEmployee=" + maxEmployee + ", minEmployee=" + minEmployee
                + ", registeredEmployees=" + registeredEmployees + "]";
    }
    
}
