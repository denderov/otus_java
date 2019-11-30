package ru.otus.hw05;

public class TestRunner {

    // в качестве параметра передаем имя класса, например, "ru.otus.hw05.ClassForTesting"
    public static void main(String[] args) {
        for (String clazz:args) {
            TestingClass test = new TestingClass(clazz);
            test.run();
        }
    }

}
