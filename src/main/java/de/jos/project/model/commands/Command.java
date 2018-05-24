package de.jos.project.model.commands;

import de.jos.project.controller.MiteClient;
import de.jos.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public interface Command {
    String executeCommandAndGetReply(String commandMessage, User user);
}
