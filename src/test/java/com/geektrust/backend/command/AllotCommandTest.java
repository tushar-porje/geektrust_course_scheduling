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
import com.geektrust.backend.dto.AllotResponse;
import com.geektrust.backend.exception.InvalidInputException;
import com.geektrust.backend.service.CourseService;
import com.geektrust.backend.utils.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AllotCommandTest {
    private CourseService courseService;
    private AllotCommand allotCommand;

    @BeforeEach
    void setUp() {
        courseService = Mockito.mock(CourseService.class);
        allotCommand = new AllotCommand(courseService);
    }

    @Test
    void execute_shouldPrintAllotResponses() {
        // Arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        List<String> tokens = Arrays.asList("ALLOT", "YourCourseId");

        // Mock the CourseService behavior
        List<AllotResponse> allotResponses = Arrays.asList(
                new AllotResponse("reg123", "john.doe@example.com", "YourCourseId","CourseName", "Java Instructor", "15062022", "Allotted"),
                new AllotResponse("reg456", "jane.doe@example.com", "YourCourseId","CourseName" ,"Java Instructor", "15062022", "Allotted")
        );
        when(courseService.allot(any(String.class))).thenReturn(allotResponses);

        // Act
        allotCommand.execute(tokens);

        // Assert
        String expectedOutput = "reg456 jane.doe@example.com YourCourseId CourseName Java Instructor 15062022 Allotted\n" +
                    "reg123 john.doe@example.com YourCourseId CourseName Java Instructor 15062022 Allotted\n";
        assertEquals(expectedOutput, outputStream.toString());

        // Verify that the allot method of CourseService is called with the correct parameters
        verify(courseService, times(1)).allot("YourCourseId");

        // Clean up
        System.setOut(System.out);
    }

    @Test
    void execute_shouldPrintErrorMessageForInvalidInput() {
        // Arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        List<String> tokens = Arrays.asList("ALLOT");

        // Act
        allotCommand.execute(tokens);

        // Assert
        String expectedOutput = Constant.INPUT_DATA_ERROR_MESSAGE + "\n";
        assertEquals(expectedOutput, outputStream.toString());

        // Verify that the allot method of CourseService is not called
        verify(courseService, never()).allot(any(String.class));

        // Clean up
        System.setOut(System.out);
    }

    @Test
    void execute_shouldPrintErrorMessageForException() {
        // Arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        List<String> tokens = Arrays.asList("ALLOT", "InvalidCourseId");

        // Mock the CourseService behavior to throw an exception
        when(courseService.allot(any(String.class))).thenThrow(new InvalidInputException("Invalid course ID"));

        // Act
        allotCommand.execute(tokens);

        // Assert
        String expectedOutput = "Invalid course ID\n";
        assertEquals(expectedOutput, outputStream.toString());

        // Verify that the allot method of CourseService is called with the correct parameters
        verify(courseService, times(1)).allot("InvalidCourseId");

        // Clean up
        System.setOut(System.out);
    }
}
