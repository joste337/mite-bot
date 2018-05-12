package de.jos.project.controller;

import de.jos.project.model.BotCommands;
import de.jos.project.model.User;
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
        return isVerifiedUser(user).orElse(messageHandler.executeCommand(command, message, user));
    }

    private Optional<String> isVerifiedUser(User user) {
        String replyMessage = "";

        if (user.getApiKey() == null) {
            replyMessage += botMessages.getNoApiKeyProvidedReply() + "\n";
        }
        if (user.getProjectID() == null) {
            replyMessage += botMessages.getNoProjectIdProvidedReply() + "\n";
        }
        if (user.getServiceID() == null) {
            replyMessage += botMessages.getNoServideIdProvidedReply();
        }

        if (replyMessage.equals("")) {
            return Optional.empty();
        } else {
            return Optional.of(replyMessage);
        }
    }
}
