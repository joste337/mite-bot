package de.jos.project.controller;

import de.jos.project.model.Commands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sx.blah.discord.handle.obj.IChannel;


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

    public void handleMessage(String message, IChannel channel) {
        Commands command = messageHandler.getCommand(message);
        String returnMessage = commandHandler.executeCommand(command, message);
    }
}
