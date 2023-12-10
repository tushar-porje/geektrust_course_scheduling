package com.geektrust.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.List;


@Data
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CourseDto {
    private String courseId;
    private final String courseName;
    private final String instructor;
    private final String date;
    private final int minEmployee;
    private final int maxEmployee;
    @NonNull
    private boolean isAllotted;
    @NonNull
    private boolean isCancelled;
    @NonNull
    private List<EmployeeDto> registeredEmployees;   
}
