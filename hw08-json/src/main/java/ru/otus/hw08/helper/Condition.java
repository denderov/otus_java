package ru.otus.hw08.helper;

import java.util.Collection;
import java.util.Objects;

public class Condition {

    private Condition() {
    }

    public static boolean isInteger(Object object) {
        Class<?> type = object.getClass();
        return type == Byte.class
                || type == Short.class
                || type  == Integer.class
                || type == Long.class;
    }

    public static boolean isFloat(Object object) {
        Class<?> type = object.getClass();
        return type == Float.class
                || type == Double.class;
    }

    public static boolean isString(Object object) {
        Class<?> type = object.getClass();
        return type == Character.class
                || type == String.class;
    }

    public static boolean isArray(Object object) {
        Class<?> type = object.getClass();
        return type.isArray();
    }

    public static boolean isCollection(Object object) {
        Class<?> type = object.getClass();
        return Collection.class.isAssignableFrom(type);
    }

    public static boolean isNull(Object object) {
        return Objects.isNull(object);
    }

    public static boolean isBoolean(Object object) {
        Class<?> type = object.getClass();
        return type == Boolean.class;
    }
}
