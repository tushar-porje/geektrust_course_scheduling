package com.geektrust.backend.command;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import com.geektrust.backend.dto.CourseDto;
import com.geektrust.backend.exception.InvalidInputException;
import com.geektrust.backend.service.CourseService;
import com.geektrust.backend.utils.Constant;

//ADD-COURSE-OFFERING JAVA JAMES 15062022 1 2
public class AddCourseCommand implements ICommand{
    private final CourseService courseService;

    public AddCourseCommand(CourseService courseService){
        this.courseService=courseService;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            if(tokens.size()!=Constant.EXPECTED_ARGS_ADD_COURSE){
                throw new InvalidInputException(Constant.INPUT_DATA_ERROR_MESSAGE);
            }

            CourseDto courseDto=getCourseDto(tokens);
            String courseId=courseService.createCourse(courseDto);

            System.out.println(courseId);
        } catch(NumberFormatException e){
            System.out.println(e.getMessage());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        } 
            
    }

    public CourseDto getCourseDto(List<String> tokens){
        // CourseDto courseDto=CourseDto.builder()
        //                     .courseName(tokens.get(1))
        //                     .instructor(tokens.get(2))
        //                     .date(tokens.get(3))
        //                     .minEmployee(Integer.parseInt(tokens.get(4)))
        //                     .maxEmployee(Integer.parseInt(tokens.get(5)))
        //                     .isAllotted(false)
        //                     .isCancelled(false)
        //                     .build();
        
        CourseDto courseDto=new CourseDto( tokens.get(1), tokens.get(2), tokens.get(3), Integer.parseInt(tokens.get(4))
        , Integer.parseInt(tokens.get(5)), false, false, new ArrayList<>());
        return courseDto;                   
    }
    
}
