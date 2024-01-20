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
import com.geektrust.backend.exception.RegistrationCancelException;
import com.geektrust.backend.service.RegistrationService;
import com.geektrust.backend.utils.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;
// import java.io.ByteArrayOutputStream;
// import java.io.PrintStream;
// import java.util.ArrayList;
// import java.util.List;
// import com.geektrust.backend.service.RegistrationSevice;
// import com.geektrust.backend.utils.Constant;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CancelCommandTest {

    private RegistrationService registrationService;
    private CancelCommand cancelCommand;

    @BeforeEach
    void setUp() {
        registrationService = Mockito.mock(RegistrationService.class);
        cancelCommand = new CancelCommand(registrationService);
    }

    @Test
    void execute_shouldPrintCancellationMessage() {
        // Arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        List<String> tokens = Arrays.asList("CANCEL", "YourRegId");

        // Mock the RegistrationService behavior
        when(registrationService.cancelRegistration(any(String.class))).thenReturn("YourRegId");

        // Act
        cancelCommand.execute(tokens);

        // Assert
        String expectedOutput = "YourRegId " + Constant.CANCEL_ACCEPTED_MESSAGE + "\n";
        assertEquals(expectedOutput, outputStream.toString());

        // Verify that cancelRegistration method of RegistrationService is called with the correct parameters
        verify(registrationService, times(1)).cancelRegistration("YourRegId");

        // Clean up
        System.setOut(System.out);
    }

    @Test
    void execute_shouldPrintErrorMessageForInvalidInput() {
        // Arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        List<String> tokens = Arrays.asList("CANCEL");

        // Act
        cancelCommand.execute(tokens);

        // Assert
        String expectedOutput = Constant.INPUT_DATA_ERROR_MESSAGE + "\n";
        assertEquals(expectedOutput, outputStream.toString());

        // Verify that cancelRegistration method of RegistrationService is not called
        verify(registrationService, never()).cancelRegistration(any(String.class));

        // Clean up
        System.setOut(System.out);
    }

    @Test
    void execute_shouldPrintErrorMessageForException() {
        // Arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        List<String> tokens = Arrays.asList("CANCEL", "InvalidRegId");

        // Mock the RegistrationService behavior to throw an exception
        when(registrationService.cancelRegistration(any(String.class))).thenThrow(new RegistrationCancelException("Registration cancellation rejected"));

        // Act
        cancelCommand.execute(tokens);

        // Assert
        String expectedOutput = "Registration cancellation rejected\n";
        assertEquals(expectedOutput, outputStream.toString());

        // Verify that cancelRegistration method of RegistrationService is called with the correct parameters
        verify(registrationService, times(1)).cancelRegistration("InvalidRegId");

        // Clean up
        System.setOut(System.out);
    }
//     String command;
//     String regId;
//     List<String> correctToken;
//     List<String> wrongToken;

//     @Mock
//     RegistrationSevice registrationSeviceMock;

//     @InjectMocks
//     CancelCommand cancelCommand;
  
//     @BeforeEach
//     void setup(){
//         command="CANCEL";
//         regId="REG-COURSE-ANDY-JAVA";
//         correctToken=new ArrayList<>(List.of(command,regId));
//         wrongToken=new ArrayList<>(List.of(command));
//     }

//     @Test
//     @DisplayName("cancelCommand Should Print RegId And Status")
//     void cancelCommandShouldPrintRegIdAndStatus(){
//         //arrange
//         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//         PrintStream printStream = new PrintStream(outputStream);
//         System.setOut(printStream);
//         String expectedregIdAndStatus="REG-COURSE-ANDY-JAVA CANCEL_ACCEPTED";
//         when(registrationSeviceMock.cancelRegistration(regId)).thenReturn("REG-COURSE-ANDY-JAVA");
//         //act
//         cancelCommand.execute(correctToken);
//         //assert
//         assertEquals(expectedregIdAndStatus,outputStream.toString().trim());
//         verify(registrationSeviceMock).cancelRegistration(regId);
//         //Clean up
//         System.setOut(System.out);
//     }

//     @Test
//     @DisplayName("cancelCommand Should return exception InvalidInputException with message INPUT_DATA_ERROR")
//     void cancelCommandShouldPrintInput_Data_Error(){
//         //arrange
//         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//         PrintStream printStream = new PrintStream(outputStream);
//         System.setOut(printStream);
//         String expectedMeassage=Constant.INPUT_DATA_ERROR_MESSAGE;
//         //act
//         cancelCommand.execute(wrongToken);
//         //assert
//         assertEquals(expectedMeassage,outputStream.toString().trim());
//         //Clean up
//         System.setOut(System.out);
//     }

}
