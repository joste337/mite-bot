package de.jos.project.model.commands;

import de.jos.project.controller.MiteClient;
import de.jos.project.database.UserRepository;
import de.jos.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class ProjectCommand implements Command {
    @Autowired
    private MiteClient miteClient;
    @Autowired
    private UserRepository userRepository;

    @Override
    public String executeCommandAndGetReply(String commandMessage, User user) {
        return miteClient.getAvailableProjectsByName(user, commandMessage);
    }
}
