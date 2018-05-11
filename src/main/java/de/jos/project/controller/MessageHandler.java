package de.jos.project.controller;


import de.jos.project.model.Commands;
import de.jos.project.exception.MessageHandlerException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(ErrorControllerAdvice.class);

    public Commands getCommand(String message) {

        String command = StringUtils.split(message, " ")[0];


        switch(command) {
            case("projekt"): return Commands.PROJECT;
            case("new"): return Commands.NEW;
            case("leistung"): return Commands.SERVICE;
        }

        return Commands.EMPTY;
    }

    public boolean isValidDuration(String duration) throws MessageHandlerException {
        Pattern validDuration = Pattern.compile("[0-9]:[0-9]{2}");
        Matcher validDurationMatcher = validDuration.matcher(duration);
        if (validDurationMatcher.matches()) {
            return true;
        } else {
            logger.error("Duration '" + duration + "' is not valid");
            return false;
        }
    }

}
