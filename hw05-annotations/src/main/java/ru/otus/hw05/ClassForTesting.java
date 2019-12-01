package ru.otus.hw05;

import ru.otus.hw05.annotation.After;
import ru.otus.hw05.annotation.Before;
import ru.otus.hw05.annotation.Test;

public class ClassForTesting {

    @Before
    void beforeMethod1() {
        System.out.println("Before method number 1");
    }

    @Before
    void beforeMethod2() {
        System.out.println("Before method number 2");
    }

    @After
    void afterMethod1() {
        System.out.println("After method number 1");
    }

    @After
    void afterMethod2() {
        System.out.println("After method number 2");
    }

    @Test
    void testMethod1() {
        System.out.println("Test method number 1");
    }

    @Test
    void testMethod2() {
        throw new RuntimeException("Test method number 2 must failed");
    }

    @Test
    void testMethod3() {
        System.out.println("Test method number 3");
    }

}
