// package com.geektrust.backend.service;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.verifyNoInteractions;
// import static org.mockito.Mockito.when;
// import java.util.ArrayList;
// import java.util.Optional;
// import com.geektrust.backend.dto.CourseDto;
// import com.geektrust.backend.dto.EmployeeDto;
// import com.geektrust.backend.dto.RegistrationDto;
// import com.geektrust.backend.exception.CourseAlreadyAllotedException;
// import com.geektrust.backend.exception.InvalidInputException;
// import com.geektrust.backend.repository.CourseRepository;
// import com.geektrust.backend.repository.EmployeeRepository;
// import com.geektrust.backend.repository.RegistrationRepository;
// import com.geektrust.backend.service.serviceImpl.RegistrationServiceImpl;
// import com.geektrust.backend.utils.EmailValidator;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// public class RegistrationServiceTest {
    
//     private CourseRepository courseRepository;
//     private EmployeeRepository employeeRepository;
//     private RegistrationRepository registrationRepository;
//     private RegistrationServiceImpl registrationService;

//     @BeforeEach
//     void setUp() {
//         courseRepository = mock(CourseRepository.class);
//         employeeRepository = mock(EmployeeRepository.class);
//         registrationRepository = mock(RegistrationRepository.class);
//         registrationService = new RegistrationServiceImpl(courseRepository, employeeRepository, registrationRepository);
//     }

//     @Test
//     void testCreateRegistration_ValidInput() {
//         // Arrange
//         String emailAddress = "john.doe@example.com";
//         String courseId = "COURSE123";
//         RegistrationDto registrationDto = new RegistrationDto(emailAddress, courseId);

//         CourseDto courseDto = new CourseDto("COURSE123", "Java Course", "Instructor", "20220101", 1, 10, false, false, new ArrayList<>());

//         // when(EmailValidator.validate(emailAddress)).thenReturn(true);
//         when(courseRepository.findById(courseId)).thenReturn(Optional.of(courseDto));
//         when(employeeRepository.existsById(emailAddress)).thenReturn(false);
//         when(employeeRepository.save(any(EmployeeDto.class))).thenReturn("john.doe@example.com");
//         when(registrationRepository.save(any(RegistrationDto.class))).thenReturn("REG123");

//         // Act
//         String registrationId = registrationService.create(registrationDto);

//         // Assert
//         assertEquals("REG123", registrationId);
//         verify(courseRepository, times(1)).findById(courseId);
//         verify(employeeRepository, times(1)).existsById(emailAddress);
//         verify(employeeRepository, times(1)).save(any(EmployeeDto.class));
//         verify(registrationRepository, times(1)).save(any(RegistrationDto.class));
//     }

//     @Test
//     void testCreateRegistration_InvalidEmailAddress() {
//         // Arrange
//         RegistrationDto registrationDto = new RegistrationDto("invalid-email", "COURSE123");

//         // Act & Assert
//         assertThrows(InvalidInputException.class, () -> registrationService.create(registrationDto));
//         verify(EmailValidator, times(1)).validate("invalid-email");
//         verifyNoInteractions(courseRepository);
//         verifyNoInteractions(employeeRepository);
//         verifyNoInteractions(registrationRepository);
//     }

//     @Test
//     void testCreateRegistration_CourseAlreadyAlloted() {
//         // Arrange
//         String emailAddress = "john.doe@example.com";
//         String courseId = "COURSE123";
//         RegistrationDto registrationDto = new RegistrationDto(emailAddress, courseId);

//         CourseDto courseDto = new CourseDto("COURSE123", "Java Course", "Instructor", "20220101", 1, 10, true, false, null);

//         when(EmailValidator.validate(emailAddress)).thenReturn(true);
//         when(courseRepository.findById(courseId)).thenReturn(Optional.of(courseDto));

//         // Act & Assert
//         assertThrows(CourseAlreadyAllotedException.class, () -> registrationService.create(registrationDto));
//         verify(EmailValidator, times(1)).validate(emailAddress);
//         verify(courseRepository, times(1)).findById(courseId);
//         verifyNoInteractions(employeeRepository);
//         verifyNoInteractions(registrationRepository);
//     }
// }
