package com.geektrust.backend.repository;

import java.util.List;
import com.geektrust.backend.dto.RegistrationDto;

public interface RegistrationRepository extends CRUDRepository<RegistrationDto,String>{
    List<RegistrationDto> findAllByCourseId(String courseId);
}
