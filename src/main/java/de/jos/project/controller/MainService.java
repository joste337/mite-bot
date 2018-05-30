package de.jos.project.controller;

import de.jos.project.manager.UserManager;
import de.jos.project.model.User;
import de.jos.project.model.commands.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component
public class MainService implements ApplicationContextAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainService.class);

    private ApplicationContext appContext;
    @Autowired
    private BotMessages botMessages;
    @Autowired
    private UserManager userManager;


    public String handleMessage(String message, User user) {
        Command command;

        try {
            command = appContext.getBean(StringUtils.split(message, " ")[0], Command.class);
        } catch (NoSuchBeanDefinitionException e) {
            return botMessages.getInvalidCommandReply();
        }
        String botReply;

        if (command instanceof NewCommand) {
            botReply = userManager.isNotVeriefiedUser(user).orElse(command.executeCommandAndGetReply(message, user));
        } else if (!(command instanceof HelpCommand) && !(command instanceof StartCommand) && !(command instanceof RegisterCommand)){
            botReply = userManager.isNotRegisteredUser(user).orElse(command.executeCommandAndGetReply(message, user));
        } else {
            botReply = command.executeCommandAndGetReply(message, user);
        }

        userManager.saveUser(user);
        return botReply;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.appContext = applicationContext;
    }
}
