package de.jos.project.model.commands;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CommandBeanInitializer {
    @Bean("projects")
    public Command getProjectsCommand() {
        return new ProjectCommand();
    }

    @Bean("services")
    public Command getServicesCommand() {
        return new AllServicesCommand();
    }

    @Bean("help")
    public Command getHelpCommand() {
        return new HelpCommand();
    }

    @Bean("new")
    public Command getNewCommand() {
        return new NewCommand();
    }

    @Bean("project")
    public Command getProjectCommand() {
        return new ProjectCommand();
    }

    @Bean("register")
    public Command getRegisterCommand() {
        return new RegisterCommand();
    }

    @Bean("service")
    public Command getServiceCommand() {
        return new ServiceCommand();
    }

    @Bean("start")
    public Command getStartCommand() {
        return new StartCommand();
    }
}
