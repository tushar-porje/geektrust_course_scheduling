package com.geektrust.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import com.geektrust.backend.dto.EmployeeDto;
import com.geektrust.backend.exception.InvalidInputException;
import com.geektrust.backend.repository.EmployeeRepository;
import com.geektrust.backend.service.serviceImpl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class EmployeeServiceTest {
    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    void getEmployee_shouldReturnEmployeeDto() throws Exception {
        // Arrange
        String emailId = "test@example.com";
        EmployeeDto expectedEmployeeDto = new EmployeeDto("Test Employee", emailId);

        // Mock the EmployeeRepository behavior
        when(employeeRepository.findById(emailId)).thenReturn(Optional.of(expectedEmployeeDto));

        // Act
        EmployeeDto result = employeeService.getEmployee(emailId);

        // Assert
        assertEquals(expectedEmployeeDto, result);

        // Verify that findById method of EmployeeRepository is called with the correct parameter
        verify(employeeRepository, times(1)).findById(emailId);
    }

    @Test
    void getEmployee_shouldThrowEmployeeNotFoundException() {
        // Arrange
        String emailId = "nonexistent@example.com";

        // Mock the EmployeeRepository behavior
        when(employeeRepository.findById(emailId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(InvalidInputException.class, () -> employeeService.getEmployee(emailId));

        // Verify that findById method of EmployeeRepository is called with the correct parameter
        verify(employeeRepository, times(1)).findById(emailId);
    }
}
