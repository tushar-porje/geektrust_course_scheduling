package com.geektrust.backend.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import com.geektrust.backend.dto.CourseDto;
import com.geektrust.backend.repository.repositoryImpl.CourseRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CourseRepositoryTest {

    private CourseRepositoryImpl courseRepository;

    @BeforeEach
    void setUp() {
        courseRepository = new CourseRepositoryImpl(new HashMap<>());
    }

    @Test
    void save_shouldSaveCourseAndReturnCourseId() {
        CourseDto courseDto = new CourseDto("Java Basics", "Instructor1", "2024-01-20",
                5, 20, false, false, new ArrayList<>());

        String savedCourseId = courseRepository.save(courseDto);

        assertTrue(courseRepository.existsById(savedCourseId));
    }

    @Test
    void findAll_shouldReturnListOfCourseDtos() {
        CourseDto courseDto1 = new CourseDto("Java", "Instructor1", "2024-01-20",
                5, 20, false, false, new ArrayList<>());
        CourseDto courseDto2 = new CourseDto("Python", "Instructor2", "2024-01-21",
                5, 15, false, false, new ArrayList<>());

        courseRepository.save(courseDto1);
        courseRepository.save(courseDto2);

        List<CourseDto> courseDtos = courseRepository.findAll();

        assertEquals(2, courseDtos.size());
        assertTrue(courseDtos.stream().anyMatch(dto -> dto.getCourseName().equals("Java")));
        assertTrue(courseDtos.stream().anyMatch(dto -> dto.getCourseName().equals("Python")));
    }

    @Test
    void findById_shouldReturnOptionalOfCourseDto() {
        CourseDto courseDto = new CourseDto("Java Basics", "Instructor1", "2024-01-20",
                5, 20, false, false, new ArrayList<>());
        String savedCourseId = courseRepository.save(courseDto);

        Optional<CourseDto> foundCourseDto = courseRepository.findById(savedCourseId);

        assertTrue(foundCourseDto.isPresent());
        assertEquals("Java Basics", foundCourseDto.get().getCourseName());
    }

    @Test
    void existsById_shouldReturnTrueIfExists() {
        CourseDto courseDto = new CourseDto("Java Basics", "Instructor1", "2024-01-20",
                5, 20, false, false, new ArrayList<>());
        String savedCourseId = courseRepository.save(courseDto);

        assertTrue(courseRepository.existsById(savedCourseId));
    }

    @Test
    void existsById_shouldReturnFalseIfNotExists() {
        assertFalse(courseRepository.existsById("nonexistent-course-id"));
    }

    @Test
    void delete_shouldRemoveCourse() {
        CourseDto courseDto = new CourseDto("Java Basics", "Instructor1", "2024-01-20",
                5, 20, false, false, new ArrayList<>());
        String savedCourseId = courseRepository.save(courseDto);

        courseRepository.delete(courseDto);

        assertFalse(courseRepository.existsById(savedCourseId));
    }

    @Test
    void deleteById_shouldRemoveCourseById() {
        CourseDto courseDto = new CourseDto("Java Basics", "Instructor1", "2024-01-20",
                5, 20, false, false, new ArrayList<>());
        String savedCourseId = courseRepository.save(courseDto);

        courseRepository.deleteById(savedCourseId);

        assertFalse(courseRepository.existsById(savedCourseId));
    }

    @Test
    void count_shouldReturnNumberOfCourses() {
        CourseDto courseDto1 = new CourseDto("Java Basics", "Instructor1", "2024-01-20",
                5, 20, false, false, new ArrayList<>());
        CourseDto courseDto2 = new CourseDto("Python Basics", "Instructor2", "2024-01-21",
                5, 15, false, false, new ArrayList<>());

        courseRepository.save(courseDto1);
        courseRepository.save(courseDto2);

        assertEquals(2, courseRepository.count());
    }
}
