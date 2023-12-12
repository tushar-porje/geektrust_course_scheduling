package com.geektrust.backend.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.ArrayList;
import java.util.List;
import com.geektrust.backend.dto.CourseDto;
import com.geektrust.backend.dto.RegistrationDto;
import com.geektrust.backend.repository.repositoryImpl.RegistrationRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegistrationRepositoryTest {
    
    CourseDto courseDto1;
    String courseId1;
    RegistrationDto registrationDto1;
    String emailAddress;
    String regId;

    String courseId2;
    String emailAddress2;
    String regId2;
    RegistrationDto registrationDto2;

    RegistrationRepository registrationRepository;

    @BeforeEach
    void setup(){
        courseDto1=new CourseDto("JAVA", "JAMES", "15062022", 1, 2, false, false, new ArrayList<>());
        courseId1="OFFERING-JAVA-JAMES";
        emailAddress="TONY@GMAIL.COM";
        regId="REG-COURSE-TONY-JAVA";

        // courseId2="OFFERING-JAVA-JAMES";
        emailAddress2="BOB@GMAIL.COM";
        regId2="REG-COURSE-BOB-PYTHON";

        registrationRepository=new RegistrationRepositoryImpl();
        registrationDto1=new RegistrationDto(regId,emailAddress, courseId1,true);
        registrationDto2=new RegistrationDto(regId2,emailAddress2, courseId1,true);
    }

    @Test
    void saveRegistration(){
        //arrange
        String expectedRegId=regId;
        //act
        String actualRegId=registrationRepository.save(registrationDto1);
        //assert
        assertEquals(expectedRegId, actualRegId);
    }

    

    @Test
    void findById(){
        //arrange
        registrationRepository.save(registrationDto1);
        RegistrationDto exceptedRegistrationDto=registrationDto1;
        //act
        RegistrationDto actualRegistrationDto=registrationRepository.findById(regId).get();

        assertEquals(exceptedRegistrationDto, actualRegistrationDto);
    }

    @Test
    void countTest(){
        //arrange
        registrationRepository.save(registrationDto1);
        long expectedCount=1;
        //act
        long actualCount=registrationRepository.count();
        //assert
        assertEquals(expectedCount, actualCount);

    }

    @Test
    void deleteById(){
        //arrange
        registrationRepository.save(registrationDto1);
        long expectedcount=0;
        //act
        registrationRepository.deleteById(regId);
        long actualCount=registrationRepository.count();
        //assert
        assertEquals(expectedcount, actualCount);
        assertFalse(registrationRepository.existsById(regId));
    }

    @Test
    void deleteByRegistrationDto(){
        //arrange
        registrationRepository.save(registrationDto1);
        long expectedcount=0;
        //act
        registrationRepository.delete(registrationDto1);
        long actualCount=registrationRepository.count();
        //assert
        assertEquals(expectedcount, actualCount);
    }

    @Test
    void findAllByCourseId(){
        //arrange
        registrationRepository.save(registrationDto1);
        registrationRepository.save(registrationDto2);
        List<RegistrationDto> expectedAllRegistrationDtoByCourseId=new ArrayList<>();
        expectedAllRegistrationDtoByCourseId.add(registrationDto2);
        expectedAllRegistrationDtoByCourseId.add(registrationDto1);

        //act
        List<RegistrationDto> actualAllRegistrationDtosByCourseId=registrationRepository.findAllByCourseId(courseId1);
        //assert
        assertEquals(expectedAllRegistrationDtoByCourseId, actualAllRegistrationDtosByCourseId);

    }
}
