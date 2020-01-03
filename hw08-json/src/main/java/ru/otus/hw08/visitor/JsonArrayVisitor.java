package ru.otus.hw08.visitor;

import ru.otus.hw08.traversed.type.TraversedArray;

import javax.json.Json;
import javax.json.JsonArrayBuilder;

public class JsonArrayVisitor implements ArrayVisitor {
    private final JsonArrayBuilder jsonArrayBuilder;

    JsonArrayVisitor() {
        jsonArrayBuilder = Json.createArrayBuilder();
    }

    @Override
    public void visit(TraversedArray traversedArray) {
        Class<?> type = traversedArray.getArray().getClass().getComponentType();
    }

    JsonArrayBuilder getJsonArrayBuilder() {
        return jsonArrayBuilder;
    }

    private boolean isString(Class<?> type) {
        return type == char.class
                || type == String.class;
    }

    private boolean isFloatNumber(Class<?> type) {
        return type == float.class
                || type == double.class;
    }

    private boolean isInteger(Class<?> type) {
        return type == byte.class
                || type == short.class
                || type  == int.class
                || type == long.class;
    }
}
