package de.jos.project.model.commands;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectCommandTest {

    private ProjectCommand projectCommand = new ProjectCommand();

    @Test
    public void projectCommandWithoutArgumentsShouldBeInvalid() {
        assertThat(projectCommand.isValidCommand("project"))
                .isFalse();
    }

    @Test
    public void projectCommandWithManyArgumentsShouldBeValid() {
        assertThat(projectCommand.isValidCommand("project abc def"))
                .isTrue();
    }

    @Test
    public void projectCommandWithOneArgumentShouldBeValid() {
        assertThat(projectCommand.isValidCommand("project abc"))
                .isTrue();
    }
}
