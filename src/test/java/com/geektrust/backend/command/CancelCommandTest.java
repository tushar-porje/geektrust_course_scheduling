package com.geektrust.backend.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import com.geektrust.backend.service.RegistrationSevice;
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
public class CancelCommandTest {

    String command;
    String regId;
    List<String> correctToken;
    List<String> wrongToken;

    @Mock
    RegistrationSevice registrationSeviceMock;

    @InjectMocks
    CancelCommand cancelCommand;
  
    @BeforeEach
    void setup(){
        command="CANCEL";
        regId="REG-COURSE-ANDY-JAVA";
        correctToken=new ArrayList<>(List.of(command,regId));
        wrongToken=new ArrayList<>(List.of(command));
    }

    @Test
    @DisplayName("cancelCommand Should Print RegId And Status")
    void cancelCommandShouldPrintRegIdAndStatus(){
        //arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String expectedregIdAndStatus="REG-COURSE-ANDY-JAVA CANCEL_ACCEPTED";
        when(registrationSeviceMock.cancelRegistration(regId)).thenReturn("REG-COURSE-ANDY-JAVA");
        //act
        cancelCommand.execute(correctToken);
        //assert
        assertEquals(expectedregIdAndStatus,outputStream.toString().trim());
        verify(registrationSeviceMock).cancelRegistration(regId);
        //Clean up
        System.setOut(System.out);
    }

    @Test
    @DisplayName("cancelCommand Should return exception InvalidInputException with message INPUT_DATA_ERROR")
    void cancelCommandShouldPrintInput_Data_Error(){
        //arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String expectedMeassage=Constant.INPUT_DATA_ERROR_MESSAGE;
        //act
        cancelCommand.execute(wrongToken);
        //assert
        assertEquals(expectedMeassage,outputStream.toString().trim());
        //Clean up
        System.setOut(System.out);
    }

}
