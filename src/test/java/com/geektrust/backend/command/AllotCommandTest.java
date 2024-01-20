// package com.geektrust.backend.command;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;
// import java.io.ByteArrayOutputStream;
// import java.io.PrintStream;
// import java.util.ArrayList;
// import java.util.List;
// import com.geektrust.backend.dto.AllotResponse;
// import com.geektrust.backend.service.CourseService;
// import com.geektrust.backend.utils.Constant;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// @ExtendWith(MockitoExtension.class)
// public class AllotCommandTest {
//     private final PrintStream standardOut = System.out;
//     private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

//     @Mock
//     CourseService courseServiceMock;

//     @InjectMocks
//     AllotCommand allotCommand;

//     String emailId;
//     String courseName;
//     String instructor;
//     String date;
//     String status;
//     String regId;
//     String command;
//     String courseId;
//     List<String> correctTokens;
//     List<String> wrongTokens;
//     List<AllotResponse> allotResponse;

//     @BeforeEach
//     void setup(){
//         System.setOut(new PrintStream(outputStreamCaptor));
//         command="ALLOT";
//         courseId="OFFERING-JAVA-JAMES";
//         instructor="JAMES";date="15062022";status="CONFIRMED";regId="REG-COURSE-ANDY-JAVA";courseName="JAVA";
//         emailId="ANDY@GMAIL.COM";
//         correctTokens=new ArrayList<>(List.of(command,courseId));
//         wrongTokens=new ArrayList<>(List.of(command));

//         allotResponse=new ArrayList<>(List.of(new AllotResponse(regId, emailId, courseId, courseName, instructor, date, status)));

//     }

//     @Test
//     @DisplayName("allotCommand should print registered employee")
//     void allotCommandShouldPrintRegisterEmployee(){
//         //arrange
//         String expectedAllotResponse=allotResponse.get(0).toString();
//         when(courseServiceMock.allot(correctTokens.get(1))).thenReturn(allotResponse);
//         //act
//         allotCommand.execute(correctTokens);
//         //assert
//         assertEquals(expectedAllotResponse, outputStreamCaptor.toString().trim());
//         verify(courseServiceMock).allot(correctTokens.get(1));
//     }

//     @Test
//     @DisplayName("allotCommand should return exception InvalidInputException with message INPUT_DATA_ERROR")
//     void allotCommandWithWrongTokensSizeShouldPrintInput_Data_Erro(){
//         //arrange
//         String expectedAllotResponse=Constant.INPUT_DATA_ERROR_MESSAGE;
//         //act
//         allotCommand.execute(wrongTokens);
//         //assert
//         assertEquals(expectedAllotResponse, outputStreamCaptor.toString().trim());
//     }

//     @AfterEach
//     public void tearDown() {
//         System.setOut(standardOut);
//     }
// }
