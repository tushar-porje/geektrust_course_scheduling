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
import com.geektrust.backend.dto.RegistrationDto;
import com.geektrust.backend.exception.InvalidInputException;
import com.geektrust.backend.service.RegistrationService;
import com.geektrust.backend.utils.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RegisterCommandTest {

    private RegistrationService registrationService;
    private RegisterCommand registerCommand;

    @BeforeEach
    void setUp() {
        registrationService = Mockito.mock(RegistrationService.class);
        registerCommand = new RegisterCommand(registrationService);
    }

    @Test
    void execute_shouldPrintRegistrationMessage() {
        // Arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        List<String> tokens = Arrays.asList("REGISTER", "YourEmailAddress", "YourCourseId");

        // Mock the RegistrationService behavior
        when(registrationService.create(any(RegistrationDto.class))).thenReturn("YourRegId");

        // Act
        registerCommand.execute(tokens);

        // Assert
        String expectedOutput = "YourRegId " + Constant.ACCEPTED_MESSAGE + "\n";
        assertEquals(expectedOutput, outputStream.toString());

        // Verify that create method of RegistrationService is called with the correct parameters
        verify(registrationService, times(1)).create(any(RegistrationDto.class));

        // Clean up
        System.setOut(System.out);
    }

    @Test
    void execute_shouldPrintErrorMessageForInvalidInput() {
        // Arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        List<String> tokens = Arrays.asList("REGISTER");

        // Act
        registerCommand.execute(tokens);

        // Assert
        String expectedOutput = Constant.INPUT_DATA_ERROR_MESSAGE + "\n";
        assertEquals(expectedOutput, outputStream.toString());

        // Verify that create method of RegistrationService is not called
        verify(registrationService, never()).create(any(RegistrationDto.class));

        // Clean up
        System.setOut(System.out);
    }

    @Test
    void execute_shouldPrintErrorMessageForException() {
        // Arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        List<String> tokens = Arrays.asList("REGISTER", "InvalidEmailAddress", "InvalidCourseId");

        // Mock the RegistrationService behavior to throw an exception
        when(registrationService.create(any(RegistrationDto.class))).thenThrow(new InvalidInputException("Invalid input"));

        // Act
        registerCommand.execute(tokens);

        // Assert
        String expectedOutput = "Invalid input\n";
        assertEquals(expectedOutput, outputStream.toString());

        // Verify that create method of RegistrationService is called with the correct parameters
        verify(registrationService, times(1)).create(any(RegistrationDto.class));

        // Clean up
        System.setOut(System.out);
    }
}

//     String command;
//     String emailAddress;
//     String courseId;
//     List<String> correctTokens;
//     List<String> wrongTokens;

//     @Mock
//     RegistrationSevice registrationSeviceMock;

//     @InjectMocks
//     RegisterCommand registerCommand;

//     //REGISTER ANDY@GMAIL.COM OFFERING-JAVA-JAMES
//     @BeforeEach
//     void setup(){
//         command="REGISTER";
//         emailAddress="ANDY@GMAIL.COM";
//         courseId="OFFERING-JAVA-JAMES";
//         correctTokens=new ArrayList<>(List.of(command,emailAddress,courseId));
//         wrongTokens=new ArrayList<>(List.of(command,emailAddress));
//     }

//     @Test
//     @DisplayName("RegisterCommand should return regId")
//     void registerCommandShouldReturnRegId(){
//         //arrange
//         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//         PrintStream printStream = new PrintStream(outputStream);
//         System.setOut(printStream);
//         String expectedRegIdWithStatus="REG-COURSE-ANDY-JAVA ACCEPTED";
//         when(registrationSeviceMock.create(any(RegistrationDto.class))).thenReturn("REG-COURSE-ANDY-JAVA");
//         //act
//         registerCommand.execute(correctTokens);
//         //assert
//         assertEquals(expectedRegIdWithStatus, outputStream.toString().trim());
//         verify(registrationSeviceMock).create(any(RegistrationDto.class));
//         //Clean up
//         System.setOut(System.out);
//     }

//     @Test
//     @DisplayName("RegisterCommand should return exception InvalidInputException with message INPUT_DATA_ERROR")
//     void registerCommandShouldPrintInput_Data_Error(){
//         //arrange
//         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//         PrintStream printStream = new PrintStream(outputStream);
//         System.setOut(printStream);
//         String expectedRegIdWithStatus=Constant.INPUT_DATA_ERROR_MESSAGE;
//         //act
//         registerCommand.execute(wrongTokens);
//         //assert
//         assertEquals(expectedRegIdWithStatus, outputStream.toString().trim());
//         //Clean up
//         System.setOut(System.out);
//     }
