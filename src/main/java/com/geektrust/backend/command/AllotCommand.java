package com.geektrust.backend.command;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import com.geektrust.backend.dto.AllotResponse;
import com.geektrust.backend.exception.InvalidInputException;
import com.geektrust.backend.service.CourseService;
import com.geektrust.backend.utils.Constant;

public class AllotCommand implements ICommand{

    private final CourseService courseService;
    
    public AllotCommand(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            if(tokens.size()==Constant.EXPECTED_ARGS_ALLOT){
                List<AllotResponse> allotResponses = courseService.allot(tokens.get(1)).stream().sorted(Comparator.comparing(AllotResponse::getEmailId)).collect(Collectors.toList());
                for(AllotResponse allotResponse:allotResponses){
                    System.out.println(allotResponse.toString());
                }
            }else{
                throw new InvalidInputException(Constant.INPUT_DATA_ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
