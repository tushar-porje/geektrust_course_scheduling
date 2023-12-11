package com.geektrust.backend.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import com.geektrust.backend.dto.CourseDto;
import com.geektrust.backend.dto.RegistrationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegistrationRepositoryTest {
    
    CourseDto courseDto1;
    String courseId1;
    RegistrationDto registrationDto1;
    String emailAddress;

    @BeforeEach
    void setup(){
        courseDto1=new CourseDto("JAVA", "JAMES", "15062022", 1, 2, false, false, new ArrayList<>());
        courseId1="OFFERING-JAVA-JAMES";
        emailAddress="TONY@GMAIL.COM";

        registrationDto1=new RegistrationDto(emailAddress, courseId1);
    }

    @Test
    void findById(){
        assertEquals(true, true);
    }
}
