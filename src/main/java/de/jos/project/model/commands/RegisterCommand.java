package de.jos.project.model.commands;

import de.jos.project.controller.BotMessages;
import de.jos.project.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class RegisterCommand implements Command {
    @Autowired
    private BotMessages botMessages;

    @Override
    public String executeCommandAndGetReply(String userMessage, User user) {
        if (!isValidCommand(userMessage)) {
            return botMessages.getCommandFailedReply();
        }

        String apiKey = StringUtils.split(userMessage, " ")[1];
        user.setApiKey(apiKey);
        return botMessages.getSuccessfullyRegisteredReply();
    }

    @Override
    public boolean isValidCommand(String userMessage) {
        return StringUtils.split(userMessage, " ").length != 2;
    }
}
