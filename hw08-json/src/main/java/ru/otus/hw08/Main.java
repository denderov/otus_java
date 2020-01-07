package ru.otus.hw08;

import ru.otus.hw08.traversed.object.ArraysContainer;
import ru.otus.hw08.traversed.object.Nested;
import ru.otus.hw08.traversed.object.PrimitiveTypesAndStrings;
import ru.otus.hw08.traversed.object.Root;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {

        PrimitiveTypesAndStrings primitiveTypesAndStrings
                = new PrimitiveTypesAndStrings((byte) 1, 2, 3, 4.5f, 6.7, '3', true, "currency");

        System.out.println(JsonMaker.toJson(primitiveTypesAndStrings));

        Nested nestedInNested = new Nested(789, null);
        Nested nested = new Nested(456, nestedInNested);
        Root root = new Root(123, "Root", nested);

        System.out.println(JsonMaker.toJson(root));

        int[] ints = {1, 2, 3, 4};
        char[][] chars = {{'a', 'b', 'c'}, {'d', 'e', 'f'}};
        List<String> names = new ArrayList<>();
        names.add("One");
        names.add("Two");
        names.add(null);
        ArraysContainer arraysContainer = new ArraysContainer(1, ints, chars, names);

        System.out.println(JsonMaker.toJson(arraysContainer));
    }
}
