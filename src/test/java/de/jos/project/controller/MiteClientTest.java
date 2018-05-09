package de.jos.project.controller;

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
        miteClient.getAvailableProjects();
    }

    @Test
    public void createNewEntry() {
        miteClient.createNewEntry("100", "mite bot test");
    }
}
