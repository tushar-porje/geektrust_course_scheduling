package com.geektrust.backend.command;

import java.util.List;
import com.geektrust.backend.exception.InvalidInputException;
import com.geektrust.backend.service.RegistrationService;
import com.geektrust.backend.utils.Constant;

public class CancelCommand implements ICommand{

    private final RegistrationService registrationSevice;

    public CancelCommand(RegistrationService registrationSevice) {
        this.registrationSevice = registrationSevice;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            if(tokens.size()!=Constant.EXPECTED_ARGS_CANCEL){
                throw new InvalidInputException(Constant.INPUT_DATA_ERROR_MESSAGE);
            }else{
                String regid=registrationSevice.cancelRegistration(tokens.get(1));
                System.out.println(regid+Constant.SPACE+Constant.CANCEL_ACCEPTED_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());            
        }
    }
}
