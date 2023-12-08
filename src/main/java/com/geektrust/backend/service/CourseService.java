package com.geektrust.backend.service;

import java.util.List;
import com.geektrust.backend.dto.AllotResponse;
import com.geektrust.backend.dto.CourseDto;

public interface CourseService {
    String createCourse(CourseDto course);

    List<AllotResponse> allot(String string);
}
