package de.jos.project.model.commands;

import de.jos.project.controller.BotMessages;
import de.jos.project.controller.MiteClient;
import de.jos.project.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceCommand implements Command {
    @Autowired
    private BotMessages botMessages;
    @Autowired
    private MiteClient miteClient;

    @Override
    public String executeCommandAndGetReply(String userMessage, User user) {
        if (!isValidCommand(userMessage)) {
            return botMessages.getInvalidCommandArgumentsReply();
        }

        return miteClient.getAvailableServicesByName(user, StringUtils.split(userMessage, " ")[1]);
    }

    @Override
    public boolean isValidCommand(String userMessage) {
        String[] splitMessage = StringUtils.split(userMessage, " ");
        return splitMessage.length > 1 && splitMessage[1].length() > 2;
    }
}
