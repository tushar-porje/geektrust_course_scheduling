package com.geektrust.backend.repository;

import com.geektrust.backend.dto.EmployeeDto;
import com.geektrust.backend.model.Employee;

public interface EmployeeRepository extends CRUDRepository<EmployeeDto,String>{
    EmployeeDto getEmployeeDto(Employee employee);
    Employee getEmployee(EmployeeDto employeeDto);
}
