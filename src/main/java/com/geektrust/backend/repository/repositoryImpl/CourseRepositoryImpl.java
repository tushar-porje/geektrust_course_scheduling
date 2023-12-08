package com.geektrust.backend.repository.repositoryImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import com.geektrust.backend.dto.CourseDto;
import com.geektrust.backend.exception.InvalidInputException;
import com.geektrust.backend.model.Course;
import com.geektrust.backend.repository.CourseRepository;
import com.geektrust.backend.utils.Constant;

public class CourseRepositoryImpl implements CourseRepository{

    private final HashMap<String,Course> courseMap=new HashMap<>();

    @Override
    public String save(CourseDto courseDto) {
        courseDto.setCourseId("OFFERING-" + courseDto.getCourseName() + "-" + courseDto.getInstructor()); 
        Course course=getCourse(courseDto);
        if(course.getCourseId()==null) throw new InvalidInputException(Constant.INPUT_DATA_ERROR_MESSAGE);
        
        courseMap.put(course.getCourseId(), course);
        return course.getCourseId();
    }

    @Override
    public List<CourseDto> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<CourseDto> findById(String id) {
        if(id!=null){
            CourseDto courseDto=getCourseDto(courseMap.get(id));
            return Optional.of(courseDto);
        }else{
            return Optional.empty();
        }
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void delete(CourseDto entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public CourseDto findByCourseId(String courseId) {
        // TODO Auto-generated method stub
        return null;
    }
    
    CourseDto getCourseDto(Course course){
        return new CourseDto(course.getCourseId(), course.getCourseName(), course.getInstructor(), course.getDate(),
        course.getMinEmployee(), course.getMaxEmployee(), course.isAllotted(), course.isCancelled(),null);
    }
   
    Course getCourse(CourseDto courseDto){
        return new Course(courseDto.getCourseId(), courseDto.getCourseName(), courseDto.getInstructor(), courseDto.getDate(),
        courseDto.getMinEmployee(), courseDto.getMaxEmployee(), courseDto.isAllotted(), courseDto.isCancelled(),courseDto.getRegisteredEmployees());
    }

   
    
}
