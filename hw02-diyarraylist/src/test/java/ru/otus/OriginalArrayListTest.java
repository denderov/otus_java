package ru.otus;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class OriginalArrayListTest {

    @Test
    public void checkAddMethod() {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.add(1);
        assertEquals(1,integerArrayList.size());
        assertThat(integerArrayList, CoreMatchers.hasItem(1));
    }
}
