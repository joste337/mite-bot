package de.jos.project.controller;


import de.jos.project.database.UserRepository;
import de.jos.project.model.BotCommands;
import de.jos.project.model.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Pattern;

@Component
public class MessageHandler {

    @Autowired
    private MiteClient miteClient;
    @Autowired
    private BotMessages botMessages;
    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(MessageHandler.class);

    public BotCommands getCommand(String message) {
        String command = StringUtils.split(message, " ")[0];

        switch(command) {
            case("project"): return BotCommands.PROJECT;
            case("new"): return BotCommands.NEW;
            case("service"): return BotCommands.SERVICE;
            case("help"): return BotCommands.HELP;
            case("auth"): return BotCommands.AUTHORIZE;
            case("start"): return BotCommands.START;
            default: return BotCommands.EMPTY;
        }
    }

    public String executeCommand(BotCommands command, String message, User user) {
        int wordCount = StringUtils.split(message, " ").length;

        if (command.equals(BotCommands.EMPTY)) {
            return botMessages.getInvalidCommandReply();
        } else if (command.equals(BotCommands.START)) {
            return botMessages.getStartReply();
        } else if (command.equals(BotCommands.PROJECT)) {
            return handleProjectCommand(message, user, wordCount);
        } else if (command.equals(BotCommands.SERVICE)) {
            return handleServiceCommand(message, user, wordCount);
        } else if (command.equals(BotCommands.HELP)) {
            return botMessages.getHelpReply();
        } else if (command.equals(BotCommands.AUTHORIZE)) {
            if (wordCount < 2) {
                return botMessages.getInvalidCommandReply();
            } else {
                return handleAuthorizeCommand(message, user);
            }
        } else if (command.equals(BotCommands.NEW)) {
            if (wordCount < 3) {
                return botMessages.getInvalidCommandReply();
            } else {
                return handleNewCommand(message, user);
            }
        } else {
            return botMessages.getInvalidCommandReply();
        }
    }

    private String handleServiceCommand(String message, User user, int wordCount) {
        if (wordCount == 2) {
            user.setServiceID(StringUtils.split(message, " ")[1]);
            userRepository.save(user);
            return botMessages.getSuccessfullySetServideIdReply();
        } else {
            return miteClient.getAvailableServices(user);
        }
    }

    private String handleProjectCommand(String message, User user, int wordCount) {
        if (wordCount == 2) {
            user.setProjectID(StringUtils.split(message, " ")[1]);
            userRepository.save(user);
            return botMessages.getSuccessfullySetProjectIdReply();
        } else {
            return miteClient.getAvailableProjects(user);
        }
    }

    private String handleAuthorizeCommand(String message, User user) {
        String apiKey = StringUtils.split(message, " ")[1];
        user.setApiKey(apiKey);
        userRepository.save(user);
        return botMessages.getSuccessfullyAuthorizedReply();
    }

    private String handleNewCommand(String message, User user) {
        String duration = StringUtils.split(message, " ")[1];
        Optional<String> validDuration = isValidDuration(duration);

        if (validDuration.isPresent()) {
            String comment = StringUtils.split(message, " ", 3)[2];
            miteClient.createNewEntry(validDuration.get(), comment, user);
            return botMessages.getSuccessfullEntryReply(duration, comment);
        } else {
            return botMessages.getInvalidDurationReply(duration);
        }
    }

    private Optional<String> isValidDuration(String duration) {
        if (!Pattern.compile("[0-9]:[0-5][0-9]").matcher(duration).matches()) {
            return Optional.empty();
        } else {
            String[] split = StringUtils.split(duration, ":");
            String time = String.valueOf(Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]));
            return Optional.of(time);
        }
    }

}
