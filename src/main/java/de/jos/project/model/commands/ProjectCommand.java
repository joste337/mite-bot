package de.jos.project.model.commands;

import de.jos.project.controller.BotMessages;
import de.jos.project.controller.MiteClient;
import de.jos.project.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class ProjectCommand implements Command {
    @Autowired
    private BotMessages botMessages;
    @Autowired
    private MiteClient miteClient;

    @Override
    public String executeCommandAndGetReply(String userMessage, User user) {
        if (isValidCommand(userMessage)) {
            return botMessages.getCommandFailedReply();
        }

        return miteClient.getAvailableProjectsByName(user, userMessage);
    }

    @Override
    public boolean isValidCommand(String userMessage) {
        return StringUtils.split(userMessage, " ").length != 2;
    }
}
