package com.geektrust.backend.service.serviceImpl;

import com.geektrust.backend.dto.EmployeeDto;
import com.geektrust.backend.exception.InvalidInputException;
import com.geektrust.backend.repository.EmployeeRepository;
import com.geektrust.backend.service.EmployeeService;
import com.geektrust.backend.utils.Constant;

public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto getEmployee(String emailId) throws InvalidInputException {;
        String errorMessage=String.format(Constant.errorMessage, emailId);
        return employeeRepository.findById(emailId).orElseThrow(()->new InvalidInputException(errorMessage));
    }
    
}
