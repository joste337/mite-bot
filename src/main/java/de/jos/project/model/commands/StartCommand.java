package de.jos.project.model.commands;

import de.jos.project.controller.BotMessages;
import de.jos.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartCommand implements Command {
    @Autowired
    private BotMessages botMessages;

    @Override
    public String executeCommandAndGetReply(String commandMessage, User user) {
        return botMessages.getStartReply();
    }

    @Override
    public boolean isValidCommand(String userMessage) {
        return false;
    }
}
