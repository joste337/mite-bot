package de.jos.project.controller;


import de.jos.project.manager.MessageHandler;
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

        BotCommands command = messageHandler.getCommandMessage("projekt");

        assertEquals(BotCommands.PROJECT, command);
    }
}
