package ru.otus.hw08;

import ru.otus.hw08.traversed.object.Nested;
import ru.otus.hw08.traversed.object.PrimitiveTypesAndStrings;
import ru.otus.hw08.traversed.object.Root;
import ru.otus.hw08.traversed.type.*;
import ru.otus.hw08.visitor.JsonVisitor;
import ru.otus.hw08.visitor.Visitor;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {
        JsonVisitor jsonVisitor = new JsonVisitor();

        PrimitiveTypesAndStrings primitiveTypesAndStrings = new PrimitiveTypesAndStrings((byte) 1, 2, 3, 4.5f,6.7, '3', "currency");

        new Main().traverse(null, null, primitiveTypesAndStrings, jsonVisitor);
        System.out.println(jsonVisitor.getJson());

        jsonVisitor = new JsonVisitor();

        Nested nestedInNested = new Nested(789, null);
        Nested nested = new Nested(456, nestedInNested);
        Root root = new Root(123, "Root", nested);

        new Main().traverse(null, null, root, jsonVisitor);
        System.out.println(jsonVisitor.getJson());
    }

    private void traverse(Object parentObject, Field objectField, Object object, Visitor visitor) throws IllegalAccessException {
        new TraversedObjectHead(parentObject, objectField, object).accept(visitor);
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field :
                fields) {
            field.setAccessible(true);
            if (isNotStatic(field)) {
                if (Objects.isNull(field.get(object))) {
                    new TraversedNull(object, field).accept(visitor);
                } else if (isInteger(field.getType())) {
                    new TraversedInteger(object, field).accept(visitor);
                } else if (isFloatNumber(field.getType())) {
                    new TraversedFloatNumber(object, field).accept(visitor);
                } else if (isString(field.getType())) {
                    new TraversedString(object, field).accept(visitor);
                } else {
                    traverse(object, field, field.get(object), visitor);
                }
            }
        }
        new TraversedObjectBottom(parentObject, objectField, object).accept(visitor);
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

    private boolean isNotStatic(Field field) {
        return !Modifier.isStatic(field.getModifiers());
    }
}
