package de.jos.project.controller;

import de.jos.project.model.Commands;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CommandHandlerTest {

    @Autowired
    private CommandHandler commandHandler;

    @Test
    public void test() {
        commandHandler.executeCommand(Commands.PROJECT, "projekt abc def ghi");
    }
}
