package ru.otus.hw04;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

/*
It is necessary to specify the path to the agent in VM options for JUnit configuration before run tests.
Like this:
-ea -javaagent:/home/homework/IdeaProjects/otus_java/hw04-autoLogging/target/AutomagicAgent.jar
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestLoggingTest {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeAll
    void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @BeforeEach
    void flushOutput() {
        output.reset();
    }

    @Test
    void checkIfAgentForSingleParameterMethodWorking() {
        new TestLogging().calculation(6);
        assertThat(output.toString(), containsString("executed method: calculation, param: 6"));
    }

    @Test
    void checkIfAgentForBothParametersMethodWorking() {
        new TestLogging().calculation(6,"second");
        assertThat(output.toString(), containsString("executed method: calculation, params: 6, second"));
    }

    @Test
    void checkIfAgentForNoneParameterMethodWorking() {
        new TestLogging().calculation();
        assertThat(output.toString(), containsString("executed method: calculation, no params"));
    }

    @Test
    void checkIfAgentWithoutAnnotationNotWorking() {
        new TestLogging().calculation("another case");
        assertThat(output.toString(), not(containsString("executed method")));
    }

    @AfterAll
    void cleanUpStreams() {
        System.setOut(null);
    }
}
