package de.jos.project.model.commands;

import de.jos.project.controller.BotMessages;
import de.jos.project.controller.MiteClient;
import de.jos.project.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.regex.Pattern;

public class NewCommand implements Command {
    @Autowired
    private BotMessages botMessages;
    @Autowired
    private MiteClient miteClient;

    @Override
    public String executeCommandAndGetReply(String commandMessage, User user) {
        String duration = StringUtils.split(commandMessage, " ")[0];
        Optional<String> validDuration = isValidDuration(duration);

        if (validDuration.isPresent()) {
            String comment = StringUtils.split(commandMessage, " ", 2)[1];
            miteClient.createNewEntry(validDuration.get(), comment, user);
            return botMessages.getSuccessfullEntryReply(duration, comment);
        } else {
            return botMessages.getInvalidDurationReply(duration);
        }
    }

    private Optional<String> isValidDuration(String duration) {
        if (!Pattern.compile("[0-9]:[0-5][0-9]").matcher(duration).matches()) {
            return Optional.empty();
        } else {
            String[] split = StringUtils.split(duration, ":");
            String time = String.valueOf(Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]));
            return Optional.of(time);
        }
    }
}
