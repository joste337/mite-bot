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
    private final String BOT_TOKEN = "NDQzNzc1OTMzNzU3Nzg0MDY1.DdSXEg.Zvkv3DHddO8iQeAHJmDmzIpBStg";
    private IDiscordClient iDiscordClient;
    private EventDispatcher dispatcher;

    @Autowired
    private MainService mainService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BotMessages botMessages;

    public DiscordClient() {
        ClientBuilder clientBuilder = new ClientBuilder();
        clientBuilder.withToken(BOT_TOKEN);
        iDiscordClient = clientBuilder.login();
        dispatcher = iDiscordClient.getDispatcher();
        dispatcher.registerListener(this);
    }

    @EventSubscriber
    public void onMessageReceivedEvent(MessageReceivedEvent event) {
        String userId = event.getAuthor().getName().hashCode() + event.getAuthor().getDiscriminator();
        IChannel channel = event.getChannel();

        if (!userRepository.existsById(userId)) {
            User newUser = new User(userId, event.getAuthor().getName(), channel.getLongID());
            userRepository.save(newUser);
            sendMessage(botMessages.getNewUserReply(event.getAuthor().getName()), channel);
        } else {
            sendMessage(mainService.handleMessage(event.getMessage().getContent(), userRepository.findById(userId).get()), channel);
        }
    }

    private void sendMessage(String message, IChannel channel) {
        System.out.println("response message: " + message);
        channel.sendMessage(message);
    }
}
