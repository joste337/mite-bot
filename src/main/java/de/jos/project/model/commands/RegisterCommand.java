package de.jos.project.model.commands;

import de.jos.project.controller.BotMessages;
import de.jos.project.controller.MiteClient;
import de.jos.project.database.UserRepository;
import de.jos.project.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class RegisterCommand implements Command {
    @Autowired
    private MiteClient miteClient;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BotMessages botMessages;

    @Override
    public String executeCommandAndGetReply(String commandMessage, User user) {
        String apiKey = StringUtils.split(commandMessage, " ")[1];
        user.setApiKey(apiKey);
        userRepository.save(user);
        return botMessages.getSuccessfullyAuthorizedReply();
    }
}
