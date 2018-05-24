package de.jos.project.model;

import de.jos.project.model.commands.*;
import org.apache.commons.lang3.StringUtils;

public class CommandMessage {
    private Command command;
    private String messageAfterCommand = "";
    private boolean valid = true;
    private boolean newEntryCommand = false;

    public CommandMessage(String userMessage) {
        String[] splitUsermessage = StringUtils.split(userMessage, " ", 2);
        int wordCount = splitUsermessage.length;
        String commandString = splitUsermessage[0];

        if (wordCount < 2) {
            if (commandString.equals("projects")) {
                command = new AllProjectsCommand();
            } else if (commandString.equals("services")) {
                command = new AllServicesCommand();
            } else if (commandString.equals("start")) {
                command = new StartCommand();
            } else if (commandString.equals("help")) {
                command = new HelpCommand();
            }
        } else if (wordCount < 3) {
            this.messageAfterCommand = splitUsermessage[1];
            if (commandString.equals("project")) {
                command = new ProjectCommand();
            } else if (commandString.equals("service")) {
                command = new ServiceCommand();
            }
        } else {
            this.messageAfterCommand = splitUsermessage[1];
            if (commandString.equals("new")) {
                command = new NewCommand();
                newEntryCommand = true;
            }
        }

        if (this.command == null) {
            valid = false;
            command = new EmptyCommand();
        }
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public String getMessageAfterCommand() {
        return messageAfterCommand;
    }

    public void setMessageAfterCommand(String messageAfterCommand) {
        this.messageAfterCommand = messageAfterCommand;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isNewEntryCommand() {
        return newEntryCommand;
    }

    public void setNewEntryCommand(boolean newEntryCommand) {
        this.newEntryCommand = newEntryCommand;
    }
}
