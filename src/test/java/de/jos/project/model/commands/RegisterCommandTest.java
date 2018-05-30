package de.jos.project.model.commands;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterCommandTest {

    private RegisterCommand registerCommand = new RegisterCommand();

    @Test
    public void registerCommandWithoutArgumentsShouldBeInvalid() {
        assertThat(registerCommand.isValidCommand("register"))
                .isFalse();
    }

    @Test
    public void registerCommandWithTwoArgumentsShouldBeInvalid() {
        assertThat(registerCommand.isValidCommand("register abc def"))
                .isFalse();
    }

    @Test
    public void registerCommandWithMultipleArgumentsShouldBeInvalid() {
        assertThat(registerCommand.isValidCommand("register abc def ghi jkl"))
                .isFalse();
    }

    @Test
    public void registerCommandWithOneArgumentShouldBeValid() {
        assertThat(registerCommand.isValidCommand("register abc"))
                .isTrue();
    }
}
