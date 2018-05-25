package de.jos.project.model.commands;

import de.jos.project.controller.MiteClient;
import de.jos.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class AllServicesCommand implements Command {
    @Autowired
    private MiteClient miteClient;

    @Override
    public String executeCommandAndGetReply(String commandMessage, User user) {
        return miteClient.getAvailableServices(user);
    }

    @Override
    public boolean isValidCommand(String userMessage) {
        return false;
    }
}
