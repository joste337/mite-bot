package de.jos.project.model.commands;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewCommandTest {

    private NewCommand newCommand = new NewCommand();

    @Test
    public void newCommandWithoutArgumentsShouldBeInvalid() {
        assertThat(newCommand.isValidCommand("new"))
                .isFalse();
    }

    @Test
    public void newCommandWithOneArgumentShouldBeInvalid() {
        assertThat(newCommand.isValidCommand("new 3:00"))
                .isFalse();
    }

    @Test
    public void newWithInvalidDurationShouldlBeInvalid() {
        assertThat(newCommand.isValidCommand("new 3:70 abc"))
                .isFalse();
    }

    @Test
    public void newWithValidArgumentsShouldlBeValid() {
        assertThat(newCommand.isValidCommand("new 3:59 abc"))
                .isTrue();
    }

    @Test
    public void newWithValidMultipleArgumentsShouldlBeValid() {
        assertThat(newCommand.isValidCommand("new 3:59 abc def ghi"))
                .isTrue();
    }
}
