package de.jos.project.controller;


import de.jos.project.model.Commands;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageHandlerTest {

    @Autowired
    private MessageHandler messageHandler;


    @Test
    public void shouldReturnProjectCommand() {

        Commands command = messageHandler.getCommand("projekt");

        assertEquals(Commands.PROJECT, command);
    }
}
