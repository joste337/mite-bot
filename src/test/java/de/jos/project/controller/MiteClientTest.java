package de.jos.project.controller;

import de.jos.project.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MiteClientTest {

    @Autowired
    MiteClient miteClient;

    @Test
    public void test() {
        User user = new User("","",2);
        miteClient.getAvailableProjects(user);
    }

    @Test
    public void createNewEntry() {
        miteClient.createNewEntry("100", "mite bot test");
    }
}
