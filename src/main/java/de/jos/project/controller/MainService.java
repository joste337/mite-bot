package de.jos.project.controller;

import de.jos.project.manager.MessageHandler;
import de.jos.project.manager.UserManager;
import de.jos.project.model.CommandMessage;
import de.jos.project.model.User;
import de.jos.project.model.commands.Command;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class MainService {
    @Autowired
    private BotMessages botMessages;
    @Autowired
    private UserManager userManager;

    public String handleMessage(String message, User user) {
        CommandMessage commandMessage = getCommandMessage(message);
        if (commandMessage.isNewEntryCommand()) {
            return userManager.isNotVeriefiedUser(user).orElse(commandMessage.getCommand().executeCommandAndGetReply(commandMessage.getMessageAfterCommand(), user));
        } else {
            return commandMessage.getCommand().executeCommandAndGetReply(commandMessage.getMessageAfterCommand(), user);
        }
    }

    private CommandMessage getCommandMessage(String message) {
        return new CommandMessage(message);
    }
}
