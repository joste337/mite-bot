package de.jos.project.controller;

import de.jos.project.database.UserRepository;
import de.jos.project.model.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.RateLimitException;
import sx.blah.discord.util.RequestBuffer;
import sx.blah.discord.util.RequestBuilder;

@Component
public class DiscordClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscordClient.class);

    private static final String BOT_TOKEN = "NDQzNzc1OTMzNzU3Nzg0MDY1.DdSXEg.Zvkv3DHddO8iQeAHJmDmzIpBStg";
    private IDiscordClient iDiscordClient;
    private EventDispatcher dispatcher;

    @Autowired
    private MainService mainService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BotMessages botMessages;

    public DiscordClient() {
        LOGGER.debug("Connecting Discord-Bot!");
        ClientBuilder clientBuilder = new ClientBuilder();
        clientBuilder.withToken(BOT_TOKEN);
        iDiscordClient = clientBuilder.login();
        dispatcher = iDiscordClient.getDispatcher();
        dispatcher.registerListener(this);
        LOGGER.debug("ConnecedDiscord-Bot successfully!");
    }

    @EventSubscriber
    public void onMessageReceivedEvent(MessageReceivedEvent event) {
        String userId = event.getAuthor().getName().hashCode() + event.getAuthor().getDiscriminator();
        IChannel channel = event.getChannel();

        if (!userRepository.existsById(userId)) {
            User newUser = new User(userId, event.getAuthor().getName(), channel.getLongID());
            userRepository.save(newUser);
            sendMessage(botMessages.getUserWelcomereply(event.getAuthor().getName()), channel);
        } else {
            sendMessage(mainService.handleMessage(event.getMessage().getContent(), userRepository.findById(userId).get()), channel);
        }
    }

    private void sendMessage(String message, IChannel channel) {
        if (message.length() > 2000) {
            String remainingMessage = StringUtils.substring(message,  2000);
            sendMessageWithBuffer(StringUtils.substring(message, 0, 2000), channel);
            sendMessage(remainingMessage, channel);
            return;
        }
        sendMessageWithBuffer(message, channel);
    }

    private void sendMessageWithBuffer(String message, IChannel channel) {
        RequestBuffer.request(() -> {
            channel.sendMessage(message);
        });
    }
}
