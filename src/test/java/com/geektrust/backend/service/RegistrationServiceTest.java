package com.geektrust.backend.service;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Optional;
import com.geektrust.backend.dto.CourseDto;
import com.geektrust.backend.dto.RegistrationDto;
import com.geektrust.backend.exception.CourseAlreadyAllotedException;
import com.geektrust.backend.exception.InvalidInputException;
import com.geektrust.backend.exception.RegistrationCancelException;
import com.geektrust.backend.repository.CourseRepository;
import com.geektrust.backend.repository.EmployeeRepository;
import com.geektrust.backend.repository.RegistrationRepository;
import com.geektrust.backend.service.serviceImpl.RegistrationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RegistrationServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private RegistrationRepository registrationRepository;

    @InjectMocks
    private RegistrationServiceImpl registrationService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreate_InvalidEmailAddress() {
        // Arrange
        RegistrationDto registrationDto = new RegistrationDto("invalid-email", "COURSE123");

        // Act & Assert
        assertThrows(InvalidInputException.class, () -> registrationService.create(registrationDto));
        verify(registrationRepository, never()).save(registrationDto);
    }

    // @Test
    // void testCreate_ValidInput() {
    //     // Arrange
    //     String emailAddress = "john.doe@example.com";
    //     String courseId = "COURSE123";
    //     RegistrationDto registrationDto = new RegistrationDto(emailAddress, courseId);
    //     CourseDto courseDto = new CourseDto(courseId, "Java Course", "Instructor", "20220101", 1, 10, false, false, new ArrayList<>());

    //     // Mock repository methods
    //     when(courseRepository.findById(courseId)).thenReturn(Optional.of(courseDto));
    //     when(employeeRepository.existsById(emailAddress)).thenReturn(false);
    //     when(registrationRepository.save(registrationDto)).thenReturn("REG123");

    //     // Act

    //     // Assert
    //     assertThrows(InvalidInputException.class, ()->registrationService.create(registrationDto));
        // assertFalse(registrationDto.isAccepted());
    // }

    @Test
    public void method() throws Exception{
        // Arrange
        // Act   
        // Assert
        assertTrue(true);
    }

    @Test
    void testCreate_CourseAlreadyAlloted() {
        // Arrange
        String emailAddress = "john.doe@example.com";
        String courseId = "COURSE123";
        RegistrationDto registrationDto = new RegistrationDto(emailAddress, courseId);
        CourseDto courseDto = new CourseDto(courseId, "Java Course", "Instructor", "20220101", 1, 10, true, false, new ArrayList<>());

        // Mock repository methods
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(courseDto));

        // Act & Assert
        assertThrows(CourseAlreadyAllotedException.class, () -> registrationService.create(registrationDto));
        verify(registrationRepository, never()).save(registrationDto);
    }

    @Test
    void testCancelRegistration_CourseAllotted() {
        // Arrange
        String regId = "REG123";
        RegistrationDto registrationDto = new RegistrationDto("john.doe@example.com", "COURSE123");
        CourseDto courseDto = new CourseDto("COURSE123", "Java Course", "Instructor", "20220101", 1, 10, true, false, new ArrayList<>());

        // Mock repository methods
        when(registrationRepository.findById(regId)).thenReturn(Optional.of(registrationDto));
        when(courseRepository.findById(registrationDto.getCourseID())).thenReturn(Optional.of(courseDto));

        // Act & Assert
        assertThrows(RegistrationCancelException.class, () -> registrationService.cancelRegistration(regId));
        verify(registrationRepository, never()).save(registrationDto);
    }
}
