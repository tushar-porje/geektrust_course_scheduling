package com.geektrust.backend.repository.repositoryImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.geektrust.backend.dto.RegistrationDto;
import com.geektrust.backend.model.Registration;
import com.geektrust.backend.repository.RegistrationRepository;

public class RegistrationRepositoryImpl implements RegistrationRepository{

    private final HashMap<String,Registration> registrationMap=new HashMap<>();

    @Override
    public String save(RegistrationDto registrationDto) {
        // registrationDto.setAccepted(true);
        Registration registration=getRegistration(registrationDto);
        registrationMap.put(registration.getRegID(), registration);
        return registration.getRegID();
    }

    @Override
    public List<RegistrationDto> findAll() {
        List<Registration> allRegistration=new ArrayList<>(registrationMap.values());
        return allRegistration.stream().map((registration) -> getRegistrationDto(registration)).collect(Collectors.toList());
    }

    @Override
    public Optional<RegistrationDto> findById(String id) {
        Registration registration=registrationMap.get(id);
        RegistrationDto registrationDto=getRegistrationDto(registration);
        return Optional.ofNullable(registrationDto);
    }

    @Override
    public boolean existsById(String id) {
        Optional<RegistrationDto> registrationDto = findById(id);
        return registrationDto.isPresent();
    }

    @Override
    public void delete(RegistrationDto id) {
        if (existsById(id.getRegID()))
            registrationMap.remove(id.getRegID());        
    }

    @Override
    public void deleteById(String id) {
        if (existsById(id))
            registrationMap.remove(id);
    }

    @Override
    public long count() {
        return registrationMap.size();
    }

    @Override
    public List<RegistrationDto> findAllByCourseId(String courseId) {
        List<RegistrationDto> allRegistrationDto = findAll(); 
        List<RegistrationDto> allRegistrationByCourse = allRegistrationDto.stream().filter(registrationDto -> registrationDto.getCourseID().equals(courseId)).collect(Collectors.toList());
        
        return allRegistrationByCourse;
    }

    private Registration getRegistration(RegistrationDto registrationDto){
            return new Registration(registrationDto.getRegID(), registrationDto.getEmailAddress(), registrationDto.getCourseID(),registrationDto.isAccepted());
    }
   
    private RegistrationDto getRegistrationDto(Registration registration){
            return new RegistrationDto(registration.getRegID(), registration.getEmailAddress(), registration.getCourseID(), registration.isAccepted());
    }
}
