package ru.otus.hw4;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestLoggingTest {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeAll
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @Test
    public void checkIfAgentWorking() {
        new TestLogging().calculation(6);
        assertThat(output.toString(), containsString("executed method: calculation, param: 6"));
    }


    @AfterAll
    public void cleanUpStreams() {
        System.setOut(null);
    }
}
