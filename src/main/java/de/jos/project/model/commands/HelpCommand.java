package de.jos.project.model.commands;

import de.jos.project.controller.BotMessages;
import de.jos.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class HelpCommand implements Command {
    @Autowired
    private BotMessages botMessages;

    @Override
    public String executeCommandAndGetReply(String commandMessage, User user) {
        return botMessages.getHelpReply();
    }

    @Override
    public boolean isValidCommand(String userMessage) {
        return false;
    }
}
