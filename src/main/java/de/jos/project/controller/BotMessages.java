package de.jos.project.controller;

import org.springframework.stereotype.Component;

@Component
public class BotMessages {
    private static final String USER_WELCOME_REPLY = "Hello %s! To help you get started, send me 'start'. :)";
    private static final String SUCCESSFUL_ENTRY_REPLY = "A new entry with the duration '%s' and the comment '%s' was created!";
    private static final String INVALID_COMMAND_ARGUMENTS_REPLY = "You didn't provide valid arguments for your command. Write 'help' to see how to use the available commands!'";
    private static final String NO_API_KEY_PROVIDED_REPLY = "You didn't provide an api-key yet. To do this, send me 'register (api-key)'.";
    private static final String NO_PROJECT_ID_PROVIDED_REPLY = "You didn't provide an project-id yet. To do this, send me 'project (project-id)'. To get a list of all project-ids, just send me 'project'. (the MTR3-id is '2351287' ;))";
    private static final String NO_SERVICE_ID_PROVIDED_REPLY = "You didn't provide an service-id yet. To do this, send me 'service (service-id)'. To get a list of all project-ids, just send me 'service'.";
    private static final String HELP_REPLY = "Help reply";
    private static final String COMMAND_FAILED_REPLY = "Failed to execute your command :(";
    private static final String INVALID_API_KEY_REPLY = "Your provided api-key was invalid";
    private static final String INVALID_COMMAND_REPLY = "You didn't provide a valid command. To see a list of available commands, send me 'help'.";


    public String getUserWelcomereply(String userName) {
        return String.format(USER_WELCOME_REPLY, userName);
    }

    public String getSuccessfullEntryReply(String duration, String comment) {
        return String.format(SUCCESSFUL_ENTRY_REPLY, duration, comment);
    }

    public String getInvalidCommandArgumentsReply() {
        return INVALID_COMMAND_ARGUMENTS_REPLY;
    }

    public String getNoApiKeyProvidedReply() {
        return NO_API_KEY_PROVIDED_REPLY;
    }

    public String getNoProjectIdProvidedReply() {
        return NO_PROJECT_ID_PROVIDED_REPLY;
    }

    public String getNoServideIdProvidedReply() {
        return NO_SERVICE_ID_PROVIDED_REPLY;
    }

    public String getStartReply() {
        return "start reply";
    }

    public String getSuccessfullyRegisteredReply() {
        return "Your api-key has been set successfully!";
    }

    public String getHelpReply() {
        return HELP_REPLY;
    }

    public String getSuccessfullySetProjectIdByNameReply(String projectName) {
        return "I set '" + projectName + "' as your default project!";
    }

    public String getCommandFailedReply() {
        return COMMAND_FAILED_REPLY;
    }

    public String getInvalidApiKeyReply() {
        return INVALID_API_KEY_REPLY;
    }

    public String getInvalidCommandReply() {
        return INVALID_COMMAND_REPLY;
    }

    public String getSuccessfullySetServiceIdByNameReply(String serviceName) {
        return "I set '" + serviceName + "' as your default project!";
    }
}
