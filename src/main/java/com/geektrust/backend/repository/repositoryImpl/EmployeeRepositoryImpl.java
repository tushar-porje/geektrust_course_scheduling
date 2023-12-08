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
        List<Employee> employees=new ArrayList<>(employeeMap.values());
        return employees.stream().map(employee->getEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public Optional<EmployeeDto> findById(String id) {
        return Optional.of(getEmployeeDto(employeeMap.get(id)));
    }

    @Override
    public boolean existsById(String id) {
        Optional<EmployeeDto> employee = findById(id);
        return employee.isPresent();
    }

    @Override
    public void delete(EmployeeDto entity) {
        if (existsById(entity.getEmailAddress()))
            employeeMap.remove(entity.getEmailAddress());
    }

    @Override
    public void deleteById(String id) {
        if (existsById(id))
            employeeMap.remove(id);
        
    }

    @Override
    public long count() {
        return employeeMap.size();
    }
    
    private EmployeeDto getEmployeeDto(Employee employee){
        return new EmployeeDto(employee.getName(), employee.getEmailAddress());
    }
}
