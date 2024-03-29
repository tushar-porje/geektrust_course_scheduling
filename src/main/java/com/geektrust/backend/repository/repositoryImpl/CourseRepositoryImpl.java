package com.geektrust.backend.repository.repositoryImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.geektrust.backend.dto.CourseDto;
import com.geektrust.backend.model.Course;
import com.geektrust.backend.repository.CourseRepository;

public class CourseRepositoryImpl implements CourseRepository{

    private final HashMap<String,Course> courseMap;

    public CourseRepositoryImpl() {
        courseMap=new HashMap<>();
    }

    public CourseRepositoryImpl(HashMap<String, Course> courseMap) {
        this.courseMap = courseMap;
    }

    @Override
    public String save(CourseDto courseDto) {
        courseDto.setCourseId("OFFERING-" + courseDto.getCourseName() + "-" + courseDto.getInstructor()); 
        Course course=getCourse(courseDto);        
        courseMap.put(course.getCourseId(), course);
        return course.getCourseId();
    }

    @Override
    public List<CourseDto> findAll() {
        return this.courseMap.values().stream().map(course->getCourseDto(course)).collect(Collectors.toList());
    }

    @Override
    public Optional<CourseDto> findById(String id) {
        CourseDto courseDto=getCourseDto(courseMap.get(id));
        return Optional.ofNullable(courseDto);
    }

    @Override
    public boolean existsById(String id) {
        return courseMap.containsKey(id);
    }

    @Override
    public void delete(CourseDto courseDto) {
        courseMap.entrySet().removeIf(a -> a.getValue().equals(getCourse(courseDto)));        
    }

    @Override
    public void deleteById(String id) {
        courseMap.remove(id);        
    }

    @Override
    public long count() {
        return courseMap.size();
    }

    @Override
    public CourseDto getCourseDto(Course course){
        return new CourseDto(course.getCourseId(), course.getCourseName(), course.getInstructor(), course.getDate(),
        course.getMinEmployee(), course.getMaxEmployee(), course.isAllotted(), course.isCancelled(),course.getRegisteredEmployees());
    }
   
    @Override
    public Course getCourse(CourseDto courseDto){
        return new Course(courseDto.getCourseId(), courseDto.getCourseName(), courseDto.getInstructor(), courseDto.getDate(),
        courseDto.getMinEmployee(), courseDto.getMaxEmployee(), courseDto.isAllotted(), courseDto.isCancelled(),courseDto.getRegisteredEmployees());
    } 
}
