package ru.otus;

import com.google.common.collect.Iterables;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class GuavaTest {

    @Test
    public void isNullsRemovedFromList() {
        List<String> strings = new ArrayList<>();
        strings.add("one");
        strings.add("two");
        strings.add(null);
        strings.add("three");
        Iterables.removeIf(strings, Objects::isNull);//Predicates.isNull()
        assertEquals(3,strings.size());

    }
}
