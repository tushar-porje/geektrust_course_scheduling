package com.geektrust.backend.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import com.geektrust.backend.dto.EmployeeDto;
import com.geektrust.backend.repository.repositoryImpl.EmployeeRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmployeeRepositoryTest{
    private EmployeeRepositoryImpl employeeRepository;

    @BeforeEach
    void setUp() {
        employeeRepository = new EmployeeRepositoryImpl(new HashMap<>());
    }

    @Test
    void save_shouldSaveEmployeeAndReturnEmailAddress() {
        EmployeeDto employeeDto = new EmployeeDto("John Doe", "john.doe@example.com");
        String savedEmail = employeeRepository.save(employeeDto);

        assertTrue(employeeRepository.existsById(savedEmail));
    }

    @Test
    void findAll_shouldReturnListOfEmployeeDtos() {
        EmployeeDto employeeDto1 = new EmployeeDto("John Doe", "john.doe@example.com");
        EmployeeDto employeeDto2 = new EmployeeDto("Jane Doe", "jane.doe@example.com");

        employeeRepository.save(employeeDto1);
        employeeRepository.save(employeeDto2);

        List<EmployeeDto> employeeDtos = employeeRepository.findAll();

        assertEquals(2, employeeDtos.size());
        assertTrue(employeeDtos.stream().anyMatch(dto -> dto.getEmailAddress().equals("john.doe@example.com")));
        assertTrue(employeeDtos.stream().anyMatch(dto -> dto.getEmailAddress().equals("jane.doe@example.com")));
    }

    @Test
    void findById_shouldReturnOptionalOfEmployeeDto() {
        EmployeeDto employeeDto = new EmployeeDto("John Doe", "john.doe@example.com");
        String savedEmail = employeeRepository.save(employeeDto);

        Optional<EmployeeDto> foundEmployeeDto = employeeRepository.findById(savedEmail);

        assertTrue(foundEmployeeDto.isPresent());
        assertEquals("john.doe@example.com", foundEmployeeDto.get().getEmailAddress());
    }

    @Test
    void existsById_shouldReturnTrueIfExists() {
        EmployeeDto employeeDto = new EmployeeDto("John Doe", "john.doe@example.com");
        String savedEmail = employeeRepository.save(employeeDto);

        assertTrue(employeeRepository.existsById(savedEmail));
    }

    @Test
    void existsById_shouldReturnFalseIfNotExists() {
        assertFalse(employeeRepository.existsById("nonexistent@example.com"));
    }

    @Test
    void delete_shouldRemoveEmployee() {
        EmployeeDto employeeDto = new EmployeeDto("John Doe", "john.doe@example.com");
        String savedEmail = employeeRepository.save(employeeDto);

        employeeRepository.delete(employeeDto);

        assertFalse(employeeRepository.existsById(savedEmail));
    }

    @Test
    void deleteById_shouldRemoveEmployeeById() {
        EmployeeDto employeeDto = new EmployeeDto("John Doe", "john.doe@example.com");
        String savedEmail = employeeRepository.save(employeeDto);

        employeeRepository.deleteById(savedEmail);

        assertFalse(employeeRepository.existsById(savedEmail));
    }

    @Test
    void count_shouldReturnNumberOfEmployees() {
        EmployeeDto employeeDto1 = new EmployeeDto("John Doe", "john.doe@example.com");
        EmployeeDto employeeDto2 = new EmployeeDto("Jane Doe", "jane.doe@example.com");

        employeeRepository.save(employeeDto1);
        employeeRepository.save(employeeDto2);

        assertEquals(2, employeeRepository.count());
    }
}