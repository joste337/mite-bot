package de.jos.project.controller;

import de.jos.project.model.Commands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MainService {
    @Autowired
    private MiteClient miteClient;
    @Autowired
    private DiscordClient discordClient;
    @Autowired
    private MessageHandler messageHandler;
    @Autowired
    private CommandHandler commandHandler;

    public void handleMessage(String message) {
        Commands command = messageHandler.getCommand(message);
        commandHandler.executeCommand(command, message);
    }
}
