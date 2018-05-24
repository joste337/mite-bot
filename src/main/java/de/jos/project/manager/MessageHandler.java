package de.jos.project.manager;


import de.jos.project.controller.BotMessages;
import de.jos.project.controller.MiteClient;
import de.jos.project.database.UserRepository;
import de.jos.project.model.CommandMessage;
import de.jos.project.model.User;
import de.jos.project.model.commands.Command;
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


    private String handleServiceCommand(String message, User user) {
        String reply = miteClient.getAvailableServicesByName(user, message);
        userRepository.save(user);
        return reply;
    }




}
