package de.jos.project.controller;

import org.springframework.stereotype.Component;

@Component
public class BotMessages {
    public String getNewUserReply(String userName) {
        return "Hello " + userName + "! To help you get started, send me 'start'. :)";
    }

    public String getInvalidDurationReply(String duration) {
        return "'" + duration + "' is not a valid duration.";
    }

    public String getSuccessfullEntryReply(String duration, String comment) {
        return "A new entry with the duration '" + duration + "' and the comment '" + comment + "' was created!";
    }

    public String getInvalidCommandReply() {
        return "You didn't provide a valid command. Write 'help' to see a list of available commands!'";
    }

    public String getNoApiKeyProvidedReply() {
        return "You didn't provide an api-key yet. To do this, send me \"register 'api-key'\".";
    }

    public String getNoProjectIdProvidedReply() {
        return "You didn't provide an project-id yet. To do this, send me \"project 'project-id'\". To get a list of all project-ids, just send me \"project\" (the MTR3-id is '2351287' ;))";
    }

    public String getNoServideIdProvidedReply() {
        return "You didn't provide an service-id yet. To do this, send me \"service 'service-id'\". To get a list of all service-ids, just send me \"service\"";
    }

    public String getStartReply() {
        return "start reply";
    }

    public String getSuccessfullyAuthorizedReply() {
        return "Your api-key has been set successfully!";
    }

    public String getHelpReply() {
        return "help reply";
    }

    public String getSuccessfullySetProjectIdReply() {
        return "Your project-ID has been set successfully!";
    }

    public String getSuccessfullySetServideIdReply() {
        return "Your service-ID has been set successfully!";
    }
}
