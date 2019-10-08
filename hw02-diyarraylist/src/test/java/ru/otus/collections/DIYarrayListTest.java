package ru.otus.collections;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class DIYarrayListTest {

    @Test
    public void checkCollectionsAddAll() {
        Integer[] integerArray = getIntegers();

        DIYarrayList<Integer> integerArrayList = new DIYarrayList<>();
        Collections.addAll(integerArrayList, integerArray);

        assertEquals(20,integerArrayList.size());

        assertThat(integerArrayList, CoreMatchers.hasItems(integerArray));
    }

    @Test
    public void checkCollectionsCopy() {
        Integer[] integerArray = getIntegers();
        Integer[] reverseIntegerArray = getReverseIntegers();

        DIYarrayList<Integer> integerArrayList = new DIYarrayList<>();
        Collections.addAll(integerArrayList, integerArray);

        DIYarrayList<Integer> copyIntegerArrayList = new DIYarrayList<>();
        Collections.addAll(copyIntegerArrayList, reverseIntegerArray);

        Collections.copy(copyIntegerArrayList,integerArrayList);

        assertThat(copyIntegerArrayList, is(equalTo(integerArrayList)));
    }

    @Test
    public void checkCollectionsSort() {
        Integer[] integerArray = getIntegers();
        Integer[] reverseIntegerArray = getReverseIntegers();

        DIYarrayList<Integer> integerArrayList = new DIYarrayList<>();
        Collections.addAll(integerArrayList, integerArray);

        DIYarrayList<Integer> reverseIntegerArrayList = new DIYarrayList<>();
        Collections.addAll(reverseIntegerArrayList, reverseIntegerArray);

        Collections.sort(integerArrayList, Comparator.reverseOrder());

        assertThat(integerArrayList, is(equalTo(reverseIntegerArrayList)));
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
