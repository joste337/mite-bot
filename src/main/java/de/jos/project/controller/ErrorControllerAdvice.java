package de.jos.project.controller;

import de.jos.project.exception.MessageHandlerException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = { MessageHandler.class })
public class ErrorControllerAdvice {

    @ExceptionHandler({ MessageHandlerException.class })
    public void clientErrorHandler(String message) {
    }
}
