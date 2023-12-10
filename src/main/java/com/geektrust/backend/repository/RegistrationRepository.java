package com.geektrust.backend.repository;

import java.util.List;
import com.geektrust.backend.dto.RegistrationDto;
import com.geektrust.backend.model.Registration;

public interface RegistrationRepository extends CRUDRepository<RegistrationDto,String>{
    List<RegistrationDto> findAllByCourseId(String courseId);
    Registration getRegistration(RegistrationDto registrationDto);
    RegistrationDto getRegistrationDto(Registration registration);
}
