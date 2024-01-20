package com.geektrust.backend.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import com.geektrust.backend.dto.CourseDto;
import com.geektrust.backend.dto.RegistrationDto;
import com.geektrust.backend.repository.repositoryImpl.RegistrationRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegistrationRepositoryTest {
    
    private RegistrationRepositoryImpl registrationRepository;

    @BeforeEach
    void setUp() {
        registrationRepository = new RegistrationRepositoryImpl(new HashMap<>());
    }

    @Test
    void save_shouldSaveRegistrationAndReturnRegID() {
        RegistrationDto registrationDto = new RegistrationDto("reg123", "john.doe@example.com", "java-course", true);

        String savedRegID = registrationRepository.save(registrationDto);

        assertTrue(registrationRepository.existsById(savedRegID));
        assertEquals(1, registrationRepository.count());
    }

    @Test
    void findAll_shouldReturnListOfRegistrationDtos() {
        RegistrationDto registrationDto1 = new RegistrationDto("reg123", "john.doe@example.com", "java-course", true);
        RegistrationDto registrationDto2 = new RegistrationDto("reg456", "jane.doe@example.com", "python-course", false);

        registrationRepository.save(registrationDto1);
        registrationRepository.save(registrationDto2);

        List<RegistrationDto> registrationDtos = registrationRepository.findAll();

        assertEquals(2, registrationDtos.size());
        assertTrue(registrationDtos.stream().anyMatch(dto -> dto.getRegID().equals("reg123")));
        assertTrue(registrationDtos.stream().anyMatch(dto -> dto.getRegID().equals("reg456")));
    }

    @Test
    void findById_shouldReturnOptionalOfRegistrationDto() {
        RegistrationDto registrationDto = new RegistrationDto("reg123", "john.doe@example.com", "java-course", true);
        String savedRegID = registrationRepository.save(registrationDto);

        Optional<RegistrationDto> foundRegistrationDto = registrationRepository.findById(savedRegID);

        assertTrue(foundRegistrationDto.isPresent());
        assertEquals("john.doe@example.com", foundRegistrationDto.get().getEmailAddress());
    }

    @Test
    void existsById_shouldReturnTrueIfExists() {
        RegistrationDto registrationDto = new RegistrationDto("reg123", "john.doe@example.com", "java-course", true);
        String savedRegID = registrationRepository.save(registrationDto);

        assertTrue(registrationRepository.existsById(savedRegID));
    }

    @Test
    void existsById_shouldReturnFalseIfNotExists() {
        assertFalse(registrationRepository.existsById("nonexistent-reg-id"));
    }

    @Test
    void delete_shouldRemoveRegistration() {
        RegistrationDto registrationDto = new RegistrationDto("reg123", "john.doe@example.com", "java-course", true);
        String savedRegID = registrationRepository.save(registrationDto);

        registrationRepository.delete(registrationDto);

        assertFalse(registrationRepository.existsById(savedRegID));
        assertEquals(0, registrationRepository.count());
    }

    @Test
    void deleteById_shouldRemoveRegistrationById() {
        RegistrationDto registrationDto = new RegistrationDto("reg123", "john.doe@example.com", "java-course", true);
        String savedRegID = registrationRepository.save(registrationDto);

        registrationRepository.deleteById(savedRegID);

        assertFalse(registrationRepository.existsById(savedRegID));
        assertEquals(0, registrationRepository.count());
    }

    @Test
    void count_shouldReturnNumberOfRegistrations() {
        RegistrationDto registrationDto1 = new RegistrationDto("reg123", "john.doe@example.com", "java-course", true);
        RegistrationDto registrationDto2 = new RegistrationDto("reg456", "jane.doe@example.com", "python-course", false);

        registrationRepository.save(registrationDto1);
        registrationRepository.save(registrationDto2);

        assertEquals(2, registrationRepository.count());
    }

    @Test
    void findAllByCourseId_shouldReturnListOfRegistrationDtosForCourse() {
        RegistrationDto registrationDto1 = new RegistrationDto("reg123", "john.doe@example.com", "java-course", true);
        RegistrationDto registrationDto2 = new RegistrationDto("reg456", "jane.doe@example.com", "java-course", false);

        registrationRepository.save(registrationDto1);
        registrationRepository.save(registrationDto2);

        List<RegistrationDto> registrationsForJavaCourse = registrationRepository.findAllByCourseId("java-course");

        assertEquals(2, registrationsForJavaCourse.size());
        assertTrue(registrationsForJavaCourse.stream().allMatch(dto -> dto.getCourseID().equals("java-course")));
    }

    CourseDto courseDto1;
    String courseId1;
    RegistrationDto registrationDto1;
    String emailAddress;
    String regId;

    String courseId2;
    String emailAddress2;
    String regId2;
    RegistrationDto registrationDto2;


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
