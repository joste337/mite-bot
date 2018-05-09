//package de.jos.project.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {
//
//    @Value("${server.error.path:${error.path:/error}}")
//    protected String errorPath;
//
//    @Autowired
//    protected ErrorControllerAdvice controllerAdvice;
//
//    @Override
//    public String getErrorPath() {
//        return errorPath;
//    }
//}
