package de.jos.project.model.commands;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceCommandTest {

    private ServiceCommand serviceCommand = new ServiceCommand();

    @Test
    public void serviceCommandWithoutArgumentsShouldBeInvalid() {
        assertThat(serviceCommand.isValidCommand("service"))
                .isFalse();
    }

    @Test
    public void serviceCommandWithManyArgumentsShouldBeValid() {
        assertThat(serviceCommand.isValidCommand("service abc def"))
                .isTrue();
    }

    @Test
    public void serviceCommandWithOneArgumentShouldBeValid() {
        assertThat(serviceCommand.isValidCommand("service abc"))
                .isTrue();
    }
}
