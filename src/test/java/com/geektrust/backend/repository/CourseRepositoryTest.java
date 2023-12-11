package com.geektrust.backend.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import com.geektrust.backend.dto.CourseDto;
import com.geektrust.backend.repository.repositoryImpl.CourseRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CourseRepositoryTest {

    CourseDto courseDto1;
    String id1;
    String id2;
    CourseDto courseDto2;
    CourseDto courseDtoWithId1;
    CourseDto courseDtoWithId2;

    CourseRepositoryImpl courseRepository;
    
    @BeforeEach
    void setup(){
        courseDto1=new CourseDto("JAVA", "JAMES", "15062022", 1, 2, false, false, new ArrayList<>());
        id1="OFFERING-JAVA-JAMES";
        courseDto2=new CourseDto("PYTHON", "BOB", "15062022", 1, 2, false, false, new ArrayList<>());
        id2="OFFERING-PYTHON-BOB";
        courseDtoWithId1=new CourseDto("OFFERING-JAVA-JAMES","JAVA", "JAMES", "15062022", 1, 2, false, false, new ArrayList<>());
        courseDtoWithId2=new CourseDto("OFFERING-PYTHON-BOB","PYTHON", "BOB", "15062022", 1, 2, false, false, new ArrayList<>());
        courseRepository=new CourseRepositoryImpl();
    }

    @Test
    void saveTest(){
        //arrange
        String expectedId="OFFERING-JAVA-JAMES";
        //act
        String actualId=courseRepository.save(courseDto1);
        //assert
        assertEquals(expectedId, actualId);
    }

    @Test
    @DisplayName("finding coures by id")
    void findByIdTest(){
        //arrange
        courseRepository.save(courseDto1);
        courseRepository.save(courseDto2);
        CourseDto actualCourseDto=courseDto1;
        //act
        CourseDto expectedCourseDto=courseRepository.findById(id1).get();
        //assert
        assertEquals(expectedCourseDto, actualCourseDto);
    }

    @Test
    @DisplayName("finding all courses")
    void findAllTest(){
        //arrange
        courseRepository.save(courseDto1);
        courseRepository.save(courseDto2);
        List<CourseDto> actualAllCourseDto=new ArrayList<>();
        actualAllCourseDto.add(courseDtoWithId1);
        actualAllCourseDto.add(courseDtoWithId2);
        //act
        List<CourseDto> expectedAllCourseDto=courseRepository.findAll();
        //assert
        assertEquals(expectedAllCourseDto, actualAllCourseDto);
    }


}
