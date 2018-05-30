package de.jos.project.model.commands;

import de.jos.project.model.User;

public class StatusCommand implements Command {
    @Override
    public String executeCommandAndGetReply(String userMessage, User user) {
        return user.toReplyString();
    }

    @Override
    public boolean isValidCommand(String userMessage) {
        return false;
    }
}
