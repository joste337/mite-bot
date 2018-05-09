package de.jos.project.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainServiceTest {

    @Autowired
    private MainService mainService;

    @Mock
    private MiteClient miteClient;

    @Test
    public void shouldNotCreateNewEntryForInvalidDuration() {
        ReflectionTestUtils.setField(mainService, "miteClient", miteClient);

        mainService.handleMessage("new 90:00 abcd");

        verifyZeroInteractions(miteClient);
    }
}
