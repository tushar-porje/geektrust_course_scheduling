package com.geektrust.backend.repository.repositoryImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.geektrust.backend.dto.EmployeeDto;
import com.geektrust.backend.model.Employee;
import com.geektrust.backend.repository.EmployeeRepository;

public class EmployeeRepositoryImpl implements EmployeeRepository{

    HashMap<String,Employee> employeeMap=new HashMap<>();
    
    @Override
    public String save(EmployeeDto employeeDto) {
        Employee employee=new Employee(employeeDto.getEmailAddress());
        employeeMap.put(employee.getEmailAddress(), employee);
        return employee.getEmailAddress();
    }

    @Override
    public List<EmployeeDto> findAll() {
        return this.employeeMap.values().stream().map(employee->getEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public Optional<EmployeeDto> findById(String id) {
        return Optional.of(getEmployeeDto(employeeMap.get(id)));
    }

    @Override
    public boolean existsById(String id) {
        return employeeMap.containsKey(id);
    }

    @Override
    public void delete(EmployeeDto employeeDto) {
        employeeMap.entrySet().removeIf(a -> a.getValue().equals(getEmployee(employeeDto))); 
    }

    @Override
    public void deleteById(String id) {
        employeeMap.remove(id);
    }

    @Override
    public long count() {
        return employeeMap.size();
    }
    
    @Override
    public EmployeeDto getEmployeeDto(Employee employee){
        return new EmployeeDto(employee.getName(), employee.getEmailAddress());
    }

    @Override
    public Employee getEmployee(EmployeeDto employeeDto){
        return new Employee(employeeDto.getEmailAddress());
    }
}
