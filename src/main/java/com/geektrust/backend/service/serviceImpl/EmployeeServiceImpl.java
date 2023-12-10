package com.geektrust.backend.service.serviceImpl;

import com.geektrust.backend.dto.EmployeeDto;
import com.geektrust.backend.repository.EmployeeRepository;
import com.geektrust.backend.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public EmployeeDto getEmployee(String emailId) throws Exception {
        return employeeRepository.findById(emailId).orElseThrow(()->new Exception("Employee with "+emailId+" not exists"));
    }
    
}
