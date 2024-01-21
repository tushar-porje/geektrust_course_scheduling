package com.geektrust.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.geektrust.backend.dto.AllotResponse;
import com.geektrust.backend.dto.CourseDto;
import com.geektrust.backend.dto.EmployeeDto;
import com.geektrust.backend.dto.RegistrationDto;
import com.geektrust.backend.exception.InvalidInputException;
import com.geektrust.backend.repository.CourseRepository;
import com.geektrust.backend.repository.EmployeeRepository;
import com.geektrust.backend.repository.RegistrationRepository;
import com.geektrust.backend.service.serviceImpl.CourseServiceImpl;
import com.geektrust.backend.utils.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("CourseServiceTest")
@ExtendWith({MockitoExtension.class})
public class CourseServiceTest {

    private CourseDto courseDto1;
    private CourseDto courseDtoWithId;

    @Mock
    CourseRepository courseRepository;

    @Mock
    RegistrationRepository registrationRepository;

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    CourseServiceImpl courseService;
    
    @BeforeEach
    void setup(){
        courseDtoWithId=new CourseDto("OFFERING-JAVA-JAMES","JAVA", "JAMES", "15062022", 1, 2, false, false, new ArrayList<>());
    }

    //String createCourse(CourseDto course);
    //course input:JAVA JAMES 15062022 1 2
    //output:OFFERING-JAVA-JAMES
    @Test
    @DisplayName("createCourseTest should return courseId")
    void testCreateCourse_ValidInput(){
        //arrange
        courseDto1=new CourseDto("JAVA", "JAMES", "15062022", 1, 2, false, false, new ArrayList<>());
        //mock
        when(courseRepository.save(courseDto1)).thenReturn("OFFERING-JAVA-JAMES");
        String ExpectedCourseId="OFFERING-JAVA-JAMES";
        //act and assert
        assertEquals(ExpectedCourseId,courseService.createCourse(courseDto1));
    }

    @Test
    void testAllot_ValidInput() {
        // Arrange
        String courseId = "COURSE123";
        CourseDto courseDto = new CourseDto(courseId, "JAVA", "Instructor", "20220101", 2, 10, false, false, new ArrayList<>());
        List<RegistrationDto> allAcceptedRegistrations = Arrays.asList(
            new RegistrationDto("REG-COURSE-JOHN-JAVA","JOHN@example.com", courseId,true),
            new RegistrationDto("REG-COURSE-JANE-JAVA","JANE@example.com", courseId,true)
        );

        // Mock repository methods
        when(courseRepository.findById(courseId)).thenReturn(java.util.Optional.of(courseDto));
        when(registrationRepository.findAllByCourseId(courseId)).thenReturn(allAcceptedRegistrations);
        when(employeeRepository.findById("JOHN@example.com")).thenReturn(java.util.Optional.of(new EmployeeDto("JOHN", "JOHN@example.com")));
        when(employeeRepository.findById("JANE@example.com")).thenReturn(java.util.Optional.of(new EmployeeDto("JONE", "JANE@example.com")));

        // Act
        List<AllotResponse> allotResponses = courseService.allot(courseId);

        // Assert

        assertNotNull(allotResponses);
        assertEquals(2, allotResponses.size());
        assertEquals(Constant.ALLOT_COURSE_MESSAGE, allotResponses.get(0).getStatus());
        assertEquals(Constant.ALLOT_COURSE_MESSAGE, allotResponses.get(1).getStatus());
        verify(courseRepository, times(1)).save(courseDto);
    }

    
    @Test
    void testAllot_InvalidInput() {
        //arrange
        String courseId = "COURSE123";
        //mock
        when(courseRepository.findById(courseId)).thenThrow(new InvalidInputException(Constant.INPUT_DATA_ERROR_MESSAGE));
        //assert
        assertThrows(InvalidInputException.class, ()->courseService.allot(courseId));
    }
}
