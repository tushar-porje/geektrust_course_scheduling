package com.geektrust.backend.repository.repositoryImpl;

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
        Registration registration=getRegistration(registrationDto);
        registrationMap.put(registration.getRegID(), registration);
        return registration.getRegID();
    }

    @Override
    public List<RegistrationDto> findAll() {
        return this.registrationMap.values().stream().map(registration->getRegistrationDto(registration)).collect(Collectors.toList());
    }

    @Override
    public Optional<RegistrationDto> findById(String id) {
        return Optional.of(getRegistrationDto(registrationMap.get(id)));
    }

    @Override
    public boolean existsById(String id) {
        return registrationMap.containsKey(id);
    }

    @Override
    public void delete(RegistrationDto registrationDto) {
        registrationMap.entrySet().removeIf(a -> a.getValue().equals(getRegistration(registrationDto)));        
    }

    @Override
    public void deleteById(String id) {
        registrationMap.remove(id);
    }

    @Override
    public long count() {
        return registrationMap.size();
    }

    @Override
    public List<RegistrationDto> findAllByCourseId(String courseId) {
        List<RegistrationDto> allRegistrationDto = findAll(); 
        return allRegistrationDto.stream().filter(registrationDto -> registrationDto.getCourseID().equals(courseId)).collect(Collectors.toList());
    }

    @Override
    public Registration getRegistration(RegistrationDto registrationDto){
        return new Registration(registrationDto.getRegID(), registrationDto.getEmailAddress(), registrationDto.getCourseID(),registrationDto.isAccepted());
    }
 
    @Override
    public RegistrationDto getRegistrationDto(Registration registration){
        return new RegistrationDto(registration.getRegID(), registration.getEmailAddress(), registration.getCourseID(), registration.isAccepted());
    }
}
