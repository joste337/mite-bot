package de.jos.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

@Component
public class DiscordClient {
    private final String TOKEN = "NDQzNzc1OTMzNzU3Nzg0MDY1.DdSXEg.Zvkv3DHddO8iQeAHJmDmzIpBStg";
    private IDiscordClient iDiscordClient;
    private EventDispatcher dispatcher;

    @Autowired
    private MainService mainService;

    public DiscordClient() {
        ClientBuilder clientBuilder = new ClientBuilder();
        clientBuilder.withToken(TOKEN);
        iDiscordClient = clientBuilder.login();
        dispatcher = iDiscordClient.getDispatcher();
        dispatcher.registerListener(this);
    }

    @EventSubscriber
    public void onMessageReceivedEvent(MessageReceivedEvent event) {
        System.out.println("received event " + event.getMessage().toString());
        event.getChannel().sendMessage("hallo, ich lebe");
        mainService.handleMessage(event.getMessage().toString());
    }

    public void sendMessage(String message) {

    }

}
