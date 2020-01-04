package ru.otus.hw08.helper;

import java.util.Collection;

public class Condition {

    private Condition() {
    }

    public static boolean isString(Class<?> type) {
        return type == char.class
                || type == Character.class
                || type == String.class;
    }

    public static boolean isFloatNumber(Class<?> type) {
        return type == float.class
                || type == double.class;
    }

    public static boolean isInteger(Class<?> type) {
        return type == byte.class
                || type == short.class
                || type  == int.class
                || type == long.class;
    }

    public static boolean isBoolean(Class<?> type) {
        return type == boolean.class;
    }

    public static boolean isCollection(Class<?> type) {
        return Collection.class.isAssignableFrom(type);
    }
}
