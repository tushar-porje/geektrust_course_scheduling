package com.geektrust.backend.repository;

import com.geektrust.backend.dto.CourseDto;
import com.geektrust.backend.model.Course;

public interface CourseRepository extends CRUDRepository<CourseDto,String>{
    CourseDto getCourseDto(Course course);
    Course getCourse(CourseDto courseDto);
}
