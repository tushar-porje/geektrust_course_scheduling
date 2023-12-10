package com.geektrust.backend.service;

import com.geektrust.backend.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto getEmployee(String emailId) throws Exception;
}
