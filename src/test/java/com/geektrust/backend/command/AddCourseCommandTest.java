package com.geektrust.backend.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.geektrust.backend.dto.CourseDto;
import com.geektrust.backend.service.CourseService;
import com.geektrust.backend.utils.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class AddCourseCommandTest {

    
    String command;
    String courseName;
    String instructor;
    String date;
    String minEmployee;
    String maxEmployee;
    List<String> correctTokens;
    List<String> wrongTokens;


    //ADD-COURSE-OFFERING JAVA JAMES 15062022 1 2
    @BeforeEach
    void setup(){
        command="ADD-COURSE-OFFERING";
        courseName="JAVA";
        instructor="JAMES";
        date="15062022";
        minEmployee="1";
        maxEmployee="2";
        correctTokens=new ArrayList<>(List.of(command,courseName,instructor,date,minEmployee,maxEmployee));
        wrongTokens=new ArrayList<>(List.of(command,courseName));
    }

    @Test
    @DisplayName("calling addCourseCommand method should return with courseId")
    void addCourseCommandShouldReturnCourseId() {
        //arrrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String expectedCourseId="OFFERING-"+courseName+"-"+instructor+"";
        when(courseService.createCourse(any(CourseDto.class))).thenReturn(expectedCourseId);
        //act
        addCourseCommand.execute(correctTokens);
        //assert
        assertEquals(expectedCourseId, outputStream.toString().trim());
        verify(courseService).createCourse(any(CourseDto.class));
        //Clean up
        System.setOut(System.out);
    }

    @Test
    @DisplayName("calling addCourseCommand method should return exception InvalidInputException with message INPUT_DATA_ERROR")
    void addCourseCommandShouldReturnInput_Data_Error(){
        //arrrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String expectedCourseId=Constant.INPUT_DATA_ERROR_MESSAGE;
        //act
        addCourseCommand.execute(wrongTokens);
        //assert
        assertEquals(expectedCourseId, outputStream.toString().trim());
        //Clean up
        System.setOut(System.out);
    }

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
