package ru.otus;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GuavaTest {

    @Test
    public void checkListOfWordsFromSentence() {

        String sentence = "  Test of split   sentence ";

        List<String> wordsFromSentence = ru.otus.Main.getListOfWordsFromSentence(sentence);

        assertEquals(4,wordsFromSentence.size());
        assertThat(wordsFromSentence, contains("Test", "of", "split","sentence"));

    }
}
