package de.jos.project.model.commands;

import de.jos.project.controller.BotMessages;
import de.jos.project.controller.MiteClient;
import de.jos.project.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class RegisterCommand implements Command {
    @Autowired
    private BotMessages botMessages;
    @Autowired
    private MiteClient miteClient;

    @Override
    public String executeCommandAndGetReply(String userMessage, User user) {
        if (!isValidCommand(userMessage)) {
            return botMessages.getInvalidCommandArgumentsReply();
        }
        return miteClient.verifyApiKey(user, StringUtils.split(userMessage, " ")[1]);
    }

    @Override
    public boolean isValidCommand(String userMessage) {
        return StringUtils.split(userMessage, " ").length == 2;
    }
}
