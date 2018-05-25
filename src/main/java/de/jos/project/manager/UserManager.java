package de.jos.project.manager;

import de.jos.project.controller.BotMessages;
import de.jos.project.database.UserRepository;
import de.jos.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserManager {
    @Autowired
    private BotMessages botMessages;
    @Autowired
    private UserRepository userRepository;


    public Optional<String> isNotVeriefiedUser(User user) {
        String replyMessage = "";

        if (user.getApiKey() == null) {
            replyMessage += botMessages.getNoApiKeyProvidedReply() + "\n";
        }
        if (user.getProjectID() == null) {
            replyMessage += botMessages.getNoProjectIdProvidedReply() + "\n";
        }
        if (user.getServiceID() == null) {
            replyMessage += botMessages.getNoServideIdProvidedReply();
        }

        if (replyMessage.equals("")) {
            return Optional.empty();
        } else {
            return Optional.of(replyMessage);
        }
    }

    public Optional<String> isNotRegisteredUser(User user) {
        if (user.getApiKey() == null) {
            return Optional.of(botMessages.getNoApiKeyProvidedReply());
        } else {
            return Optional.empty();
        }
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
