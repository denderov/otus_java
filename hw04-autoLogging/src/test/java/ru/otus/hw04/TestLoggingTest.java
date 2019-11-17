package ru.otus.hw04;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/*
It is necessary to specify the path to the agent in VM options before run tests. Like this:
-ea -javaagent:/home/homework/IdeaProjects/otus_java/hw04-autoLogging/target/AutomagicAgent.jar
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestLoggingTest {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeAll
    void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @Test
    void checkIfAgentForSingleParameterMethodWorking() {
        new TestLogging().calculation(6);
        assertThat(output.toString(), containsString("executed method: calculation, param: 6"));
    }


    @AfterAll
    void cleanUpStreams() {
        System.setOut(null);
    }
}
