package de.jos.project.controller;

import de.jos.project.model.BotCommands;
import de.jos.project.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class MainService {
    @Autowired
    private MessageHandler messageHandler;
    @Autowired
    private BotMessages botMessages;

    public String handleMessage(String message, User user) {
        BotCommands command = messageHandler.getCommand(message);
        return isNotVeriefiedUser(message, user, command).orElse(messageHandler.executeCommand(command, StringUtils.split(message, " ", 2)[1], user));
    }

    private Optional<String> isNotVeriefiedUser(String message, User user, BotCommands command) {
        String replyMessage = "";

        if (user.getApiKey() == null) {
            if (!command.equals(BotCommands.AUTHORIZE)) {
                replyMessage += botMessages.getNoApiKeyProvidedReply() + "\n";
            }
        } else {
            if (user.getProjectID() == null) {
                if (!command.equals(BotCommands.PROJECT)) {
                    replyMessage += botMessages.getNoProjectIdProvidedReply() + "\n";
                }
            }
            if (user.getServiceID() == null) {
                if (command.equals(BotCommands.SERVICE)) {
                    replyMessage = "";
                } else {
                    replyMessage += botMessages.getNoServideIdProvidedReply();
                }
            }
        }

        if (replyMessage.equals("")) {
            return Optional.empty();
        } else {
            return Optional.of(replyMessage);
        }
    }
}
