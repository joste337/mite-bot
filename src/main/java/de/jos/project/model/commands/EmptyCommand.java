package de.jos.project.model.commands;

import de.jos.project.model.User;

public class EmptyCommand implements Command {
    @Override
    public String executeCommandAndGetReply(String commandMessage, User user) {
        return null;
    }
}
