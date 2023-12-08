package com.geektrust.backend.command;

import java.util.List;
import com.geektrust.backend.dto.RegistrationDto;
import com.geektrust.backend.exception.InvalidInputException;
import com.geektrust.backend.service.RegistrationSevice;
import com.geektrust.backend.utils.Constant;

public class RegisterCommand implements ICommand{

    private final RegistrationSevice registrationSevice;

    public RegisterCommand(RegistrationSevice registrationSevice) {
        this.registrationSevice = registrationSevice;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            if(tokens.size()!=Constant.EXPECTED_ARGS_REGISTER_COURSE){
                throw new InvalidInputException(Constant.INPUT_DATA_ERROR_MESSAGE);
            }
            RegistrationDto registrationDto=getRegistrationDto(tokens);
            String registrationId=registrationSevice.create(registrationDto);
            System.out.println(registrationId+" "+Constant.ACCEPTED_MESSAGE);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    private RegistrationDto getRegistrationDto(List<String> tokens) {
        RegistrationDto registrationDto=new RegistrationDto(tokens.get(1),tokens.get(2));
        return registrationDto;
    }
    
}
