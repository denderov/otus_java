package ru.otus;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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

    @Test
    public void checkCollectionsAddAll() {
        Integer[] integerArray = getIntegers();
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        Collections.addAll(integerArrayList, integerArray);
        assertEquals(20,integerArrayList.size());
        assertThat(integerArrayList, CoreMatchers.hasItems(integerArray));
    }

    @Test
    public void checkCollectionsCopy() {
        Integer[] integerArray = getIntegers();
        Integer[] reverseIntegerArray = getReverseIntegers();
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        Collections.addAll(integerArrayList, integerArray);
        ArrayList<Integer> copyIntegerArrayList = new ArrayList<>();
        Collections.addAll(copyIntegerArrayList, reverseIntegerArray);
        Collections.copy(copyIntegerArrayList,integerArrayList);
        assertEquals(20,integerArrayList.size());
        assertThat(integerArrayList, CoreMatchers.hasItems(integerArray));
        assertEquals(20, copyIntegerArrayList.size());
        assertThat(copyIntegerArrayList, CoreMatchers.hasItems(integerArray));
    }

    private Integer[] getReverseIntegers() {
        Integer[] reverseIntegerArray = new Integer[20];
        for (int i = 0; i <20; i++) {
            reverseIntegerArray[i] = 19-i;
        }
        return reverseIntegerArray;
    }

    private Integer[] getIntegers() {
        Integer[] integerArray = new Integer[20];
        for (int i = 0; i <20; i++) {
            integerArray[i] = i;
        }
        return integerArray;
    }
}
