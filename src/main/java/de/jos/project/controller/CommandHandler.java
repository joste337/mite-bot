package de.jos.project.controller;

import de.jos.project.model.Commands;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandHandler {

    @Autowired
    private MiteClient miteClient;

    @Autowired
    private MessageHandler messageHandler;


    public void executeCommand(Commands command,String message) {
        String messageAfterCommand = StringUtils.split(message, " ", 2)[1];

        if (command.equals(Commands.PROJECT)) {

        } else if (command.equals(Commands.SERVICE)) {

        } else if (command.equals(Commands.NEW)) {
            handleNewCommand(messageAfterCommand);
        }
    }

    private void handleNewCommand(String messageAfterCommand) {
        String duration = StringUtils.split(messageAfterCommand, " ")[0];
        if (!messageHandler.isValidDuration(duration)) {
            return;
        }
        String comment = StringUtils.split(messageAfterCommand, " ", 2)[1];
        miteClient.createNewEntry(duration, comment);
    }
}
