package com.geektrust.backend.repository;

import com.geektrust.backend.dto.CourseDto;

public interface CourseRepository extends CRUDRepository<CourseDto,String>{
    CourseDto findByCourseId(String courseId);
}
