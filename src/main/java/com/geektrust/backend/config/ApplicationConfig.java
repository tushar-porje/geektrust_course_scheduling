package com.geektrust.backend.config;

import com.geektrust.backend.command.AddCourseCommand;
import com.geektrust.backend.command.AllotCommand;
import com.geektrust.backend.command.CancelCommand;
import com.geektrust.backend.command.CommandInvoker;
import com.geektrust.backend.command.RegisterCommand;
import com.geektrust.backend.repository.CourseRepository;
import com.geektrust.backend.repository.EmployeeRepository;
import com.geektrust.backend.repository.RegistrationRepository;
import com.geektrust.backend.repository.repositoryImpl.CourseRepositoryImpl;
import com.geektrust.backend.repository.repositoryImpl.EmployeeRepositoryImpl;
import com.geektrust.backend.repository.repositoryImpl.RegistrationRepositoryImpl;
import com.geektrust.backend.service.CourseService;
import com.geektrust.backend.service.RegistrationSevice;
import com.geektrust.backend.service.serviceImpl.CourseServiceImpl;
import com.geektrust.backend.service.serviceImpl.RegistrationServiceImpl;

public class ApplicationConfig {

    private final CourseRepository courseRepository=new CourseRepositoryImpl();
    private final EmployeeRepository employeeRepository=new EmployeeRepositoryImpl();
    private final RegistrationRepository registrationRepository=new RegistrationRepositoryImpl();

    private final CourseService courseService=new CourseServiceImpl(courseRepository,registrationRepository,employeeRepository);
    private final RegistrationSevice registrationSevice=new RegistrationServiceImpl(courseRepository, employeeRepository, registrationRepository);

    private final AddCourseCommand addCourseCommand=new AddCourseCommand(courseService);
    private final RegisterCommand registerCommand=new RegisterCommand(registrationSevice);
    private final CancelCommand cancelCommand=new CancelCommand(registrationSevice);
    private final AllotCommand allotCommand=new AllotCommand(courseService);
    
    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("ADD-COURSE-OFFERING", addCourseCommand);
        commandInvoker.register("REGISTER", registerCommand);
        commandInvoker.register("ALLOT", allotCommand);
        commandInvoker.register("CANCEL", cancelCommand);

        return commandInvoker;
    }
}
