package com.geektrust.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;
import com.geektrust.backend.dto.EmployeeDto;

@Data
@AllArgsConstructor
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
    
}
