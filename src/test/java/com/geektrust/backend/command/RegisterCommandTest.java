package com.geektrust.backend.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import com.geektrust.backend.dto.RegistrationDto;
import com.geektrust.backend.service.RegistrationSevice;
import com.geektrust.backend.utils.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RegisterCommandTest {

    String command;
    String emailAddress;
    String courseId;
    List<String> correctTokens;
    List<String> wrongTokens;

    @Mock
    RegistrationSevice registrationSeviceMock;

    @InjectMocks
    RegisterCommand registerCommand;

    //REGISTER ANDY@GMAIL.COM OFFERING-JAVA-JAMES
    @BeforeEach
    void setup(){
        command="REGISTER";
        emailAddress="ANDY@GMAIL.COM";
        courseId="OFFERING-JAVA-JAMES";
        correctTokens=new ArrayList<>(List.of(command,emailAddress,courseId));
        wrongTokens=new ArrayList<>(List.of(command,emailAddress));
    }

    @Test
    @DisplayName("RegisterCommand should return regId")
    void registerCommandShouldReturnRegId(){
        //arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String expectedRegIdWithStatus="REG-COURSE-ANDY-JAVA ACCEPTED";
        when(registrationSeviceMock.create(any(RegistrationDto.class))).thenReturn("REG-COURSE-ANDY-JAVA");
        //act
        registerCommand.execute(correctTokens);
        //assert
        assertEquals(expectedRegIdWithStatus, outputStream.toString().trim());
        verify(registrationSeviceMock).create(any(RegistrationDto.class));
        //Clean up
        System.setOut(System.out);
    }

    @Test
    @DisplayName("RegisterCommand should return exception InvalidInputException with message INPUT_DATA_ERROR")
    void registerCommandShouldPrintInput_Data_Error(){
        //arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String expectedRegIdWithStatus=Constant.INPUT_DATA_ERROR_MESSAGE;
        //act
        registerCommand.execute(wrongTokens);
        //assert
        assertEquals(expectedRegIdWithStatus, outputStream.toString().trim());
        //Clean up
        System.setOut(System.out);
    }
    
}
