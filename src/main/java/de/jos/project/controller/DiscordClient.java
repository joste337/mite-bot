package de.jos.project.controller;

import de.jos.project.database.UserRepository;
import de.jos.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;

@Component
public class DiscordClient {
    private final String TOKEN = "NDQzNzc1OTMzNzU3Nzg0MDY1.DdSXEg.Zvkv3DHddO8iQeAHJmDmzIpBStg";
    private IDiscordClient iDiscordClient;
    private EventDispatcher dispatcher;

    @Autowired
    private MainService mainService;
    @Autowired
    private UserRepository userRepository;

    public DiscordClient() {
        ClientBuilder clientBuilder = new ClientBuilder();
        clientBuilder.withToken(TOKEN);
        iDiscordClient = clientBuilder.login();
        dispatcher = iDiscordClient.getDispatcher();
        dispatcher.registerListener(this);
    }

    @EventSubscriber
    public void onMessageReceivedEvent(MessageReceivedEvent event) {
        String userId = event.getAuthor().getName().hashCode() + event.getAuthor().getDiscriminator();
        if (isNewUser(userId)) {
            userRepository.insertNewUser(new User(userId));
            sendMessage("Hello " + event.getAuthor().toString() + "! To help you get started, send me 'start'. :)", event.getChannel());
        } else {
            mainService.handleMessage(event.getMessage().getContent(), event.getChannel());
        }

    }

    public void sendMessage(String message, IChannel channel) {
        channel.sendMessage(message);
    }


    private boolean isNewUser(String id) {
        if (userRepository.findUserByID(id) == null) {
            return true;
        } else {
            return false;
        }
    }
}
