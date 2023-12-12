package com.geektrust.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import com.geektrust.backend.dto.CourseDto;
import com.geektrust.backend.repository.CourseRepository;
import com.geektrust.backend.repository.EmployeeRepository;
import com.geektrust.backend.repository.RegistrationRepository;
import com.geektrust.backend.service.serviceImpl.CourseServiceImpl;
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
    CourseRepository CourseRepositoryMock;

    @Mock
    RegistrationRepository registrationRepository;

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    CourseServiceImpl courseService;
    
    @BeforeEach
    void setup(){
        courseDto1=new CourseDto("JAVA", "JAMES", "15062022", 1, 2, false, false, new ArrayList<>());
        courseDtoWithId=new CourseDto("OFFERING-JAVA-JAMES","JAVA", "JAMES", "15062022", 1, 2, false, false, new ArrayList<>());
    }

    //String createCourse(CourseDto course);
    //course input:JAVA JAMES 15062022 1 2
    //output:OFFERING-JAVA-JAMES
    @Test
    @DisplayName("createCourseTest should return courseId")
    void createCourseTest(){
        //arrange
        when(CourseRepositoryMock.save(courseDto1)).thenReturn("OFFERING-JAVA-JAMES");
        String ExpectedCourseId="OFFERING-JAVA-JAMES";
        //act and assert
        assertEquals(ExpectedCourseId,courseService.createCourse(courseDto1));
    }


    

}
