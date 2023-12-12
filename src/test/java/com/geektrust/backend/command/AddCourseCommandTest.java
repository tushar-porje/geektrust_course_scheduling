package com.geektrust.backend.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import com.geektrust.backend.dto.CourseDto;
import com.geektrust.backend.service.CourseService;
import com.geektrust.backend.utils.Constant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AddCourseCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    String command;
    String courseName;
    String instructor;
    String date;
    String minEmployee;
    String maxEmployee;
    List<String> correctTokens;
    List<String> wrongTokens;


    @Mock
    CourseService courseServiceMock;
    @InjectMocks
    AddCourseCommand addCourseCommand;

    //ADD-COURSE-OFFERING JAVA JAMES 15062022 1 2
    @BeforeEach
    void setup(){
        System.setOut(new PrintStream(outputStreamCaptor));

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
    void addCourseCommandShouldReturnCourseId(){
        //arrrange
        String expectedCourseId="OFFERING-"+courseName+"-"+instructor+"";
        when(courseServiceMock.createCourse(any(CourseDto.class))).thenReturn(expectedCourseId);
        //act
        addCourseCommand.execute(correctTokens);
        //assert
        assertEquals(expectedCourseId, outputStreamCaptor.toString().trim());
        verify(courseServiceMock).createCourse(any(CourseDto.class));
    }

    @Test
    @DisplayName("calling addCourseCommand method should return exception InvalidInputException with message INPUT_DATA_ERROR")
    void addCourseCommandShouldReturnInput_Data_Error(){
        //arrrange
        String expectedCourseId=Constant.INPUT_DATA_ERROR_MESSAGE;
        //act
        addCourseCommand.execute(wrongTokens);
        //assert
        assertEquals(expectedCourseId, outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
