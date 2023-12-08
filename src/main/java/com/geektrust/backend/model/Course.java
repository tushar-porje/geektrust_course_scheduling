package com.geektrust.backend.model;

import lombok.Builder;
import java.util.List;
import com.geektrust.backend.dto.EmployeeDto;

@Builder
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

    public String getCourseId() {
        return courseId;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
        result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((instructor == null) ? 0 : instructor.hashCode());
        result = prime * result + (isAllotted ? 1231 : 1237);
        result = prime * result + (isCancelled ? 1231 : 1237);
        result = prime * result + maxEmployee;
        result = prime * result + minEmployee;
        result = prime * result
                + ((registeredEmployees == null) ? 0 : registeredEmployees.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Course other = (Course) obj;
        if (courseId == null) {
            if (other.courseId != null)
                return false;
        } else if (!courseId.equals(other.courseId))
            return false;
        if (courseName == null) {
            if (other.courseName != null)
                return false;
        } else if (!courseName.equals(other.courseName))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (instructor == null) {
            if (other.instructor != null)
                return false;
        } else if (!instructor.equals(other.instructor))
            return false;
        if (isAllotted != other.isAllotted)
            return false;
        if (isCancelled != other.isCancelled)
            return false;
        if (maxEmployee != other.maxEmployee)
            return false;
        if (minEmployee != other.minEmployee)
            return false;
        if (registeredEmployees == null) {
            if (other.registeredEmployees != null)
                return false;
        } else if (!registeredEmployees.equals(other.registeredEmployees))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Course [courseId=" + courseId + ", courseName=" + courseName + ", date=" + date
                + ", instructor=" + instructor + ", isAllotted=" + isAllotted + ", isCancelled="
                + isCancelled + ", maxEmployee=" + maxEmployee + ", minEmployee=" + minEmployee
                + ", registeredEmployees=" + registeredEmployees + "]";
    }                           
    
    
    
}
