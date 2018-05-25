package de.jos.project.model.commands;

import de.jos.project.model.User;
import org.springframework.stereotype.Component;

@Component
public interface Command {
    String executeCommandAndGetReply(String userMessage, User user);

    boolean isValidCommand(String userMessage);
}
