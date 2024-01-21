package com.geektrust.backend.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import com.geektrust.backend.dto.CourseDto;
import com.geektrust.backend.service.CourseService;
import com.geektrust.backend.utils.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class AddCourseCommandTest {
    private CourseService courseService;
    private AddCourseCommand addCourseCommand;

    @BeforeEach
    void setUp() {
        courseService = Mockito.mock(CourseService.class);
        addCourseCommand = new AddCourseCommand(courseService);
    }

    @Test
    void execute_shouldPrintCourseId() {
        // Arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        List<String> tokens = Arrays.asList("ADD-COURSE-OFFERING", "JAVA", "JAMES", "15062022", "1", "2");

        // Mock the CourseService behavior
        when(courseService.createCourse(any(CourseDto.class))).thenReturn("YourExpectedCourseId");

        // Act
        addCourseCommand.execute(tokens);

        // Assert
        String expectedOutput = "YourExpectedCourseId\n";
        assertEquals(expectedOutput, outputStream.toString());

        // Verify that createCourse method of CourseService is called with the correct parameters
        verify(courseService, times(1)).createCourse(any(CourseDto.class));

        // Clean up
        System.setOut(System.out);
    }

    @Test
    void execute_shouldPrintErrorMessageForInvalidInput() {
        // Arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        List<String> tokens = Arrays.asList("ADD-COURSE-OFFERING", "JAVA", "JAMES", "15062022", "1");

        // Act
        addCourseCommand.execute(tokens);

        // Assert
        String expectedOutput = Constant.INPUT_DATA_ERROR_MESSAGE + "\n";
        assertEquals(expectedOutput, outputStream.toString());

        // Verify that createCourse method of CourseService is not called
        verify(courseService, never()).createCourse(any(CourseDto.class));

        // Clean up
        System.setOut(System.out);
    }

    @Test
    void execute_shouldPrintErrorMessageForNumberFormatException() {
        // Arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        List<String> tokens = Arrays.asList("ADD-COURSE-OFFERING", "JAVA", "JAMES", "15062022", "invalid", "2");

        // Act
        addCourseCommand.execute(tokens);

        // Assert
        String expectedOutput = "For input string: \"invalid\"\n";
        assertEquals(expectedOutput, outputStream.toString());

        // Verify that createCourse method of CourseService is not called
        verify(courseService, never()).createCourse(any(CourseDto.class));

        // Clean up
        System.setOut(System.out);
    }
}
