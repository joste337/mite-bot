package de.jos.project.model.commands;

import de.jos.project.controller.BotMessages;
import de.jos.project.controller.MiteClient;
import de.jos.project.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Pattern;

public class NewCommand implements Command {
    @Autowired
    private BotMessages botMessages;
    @Autowired
    private MiteClient miteClient;

    @Override
    public String executeCommandAndGetReply(String userMessage, User user) {
        if (!isValidCommand(userMessage)) {
            return botMessages.getCommandFailedReply();
        }
        String[] splitMessage = StringUtils.split(userMessage, " ");
        String durationInMinutes = getDurationInMinutes(splitMessage[1]);
        String comment = splitMessage[2];

        miteClient.createNewEntry(durationInMinutes, comment, user);
        return botMessages.getSuccessfullEntryReply(durationInMinutes, comment);
    }

    @Override
    public boolean isValidCommand(String userMessage) {
        String[] splitMessage = StringUtils.split(userMessage, " ");
        if (splitMessage.length > 3) {
            return false;
        }
        return isValidDuration(splitMessage[2]);
    }

    private boolean isValidDuration(String duration) {
        return !Pattern.compile("[0-9]:[0-5][0-9]").matcher(duration).matches();
    }

    private String getDurationInMinutes(String duration) {
        String[] split = StringUtils.split(duration, ":");
        return String.valueOf(Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]));
    }

}
