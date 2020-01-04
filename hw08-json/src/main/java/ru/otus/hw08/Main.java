package ru.otus.hw08;

import com.google.gson.Gson;
import ru.otus.hw08.traversed.object.ArraysContainer;
import ru.otus.hw08.traversed.object.Nested;
import ru.otus.hw08.traversed.object.PrimitiveTypesAndStrings;
import ru.otus.hw08.traversed.object.Root;
import ru.otus.hw08.traversed.type.TraversedObject;
import ru.otus.hw08.visitor.JsonObjectVisitor;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {
        JsonObjectVisitor jsonObjectVisitor = new JsonObjectVisitor();

        PrimitiveTypesAndStrings primitiveTypesAndStrings = new PrimitiveTypesAndStrings((byte) 1, 2, 3, 4.5f,6.7, '3', true, "currency");

        new TraversedObject(primitiveTypesAndStrings).accept(jsonObjectVisitor);

        System.out.println(jsonObjectVisitor.getJsonObject());

        jsonObjectVisitor = new JsonObjectVisitor();

        Nested nestedInNested = new Nested(789, null);
        Nested nested = new Nested(456, nestedInNested);
        Root root = new Root(123, "Root", nested);

        new TraversedObject(root).accept(jsonObjectVisitor);
        System.out.println(jsonObjectVisitor.getJsonObject());

        jsonObjectVisitor = new JsonObjectVisitor();
        int[] ints = {1, 2, 3, 4};
        char[][] chars = {{'a', 'b', 'c'}, {'d', 'e', 'f'}};
        List<Object> objects = new ArrayList<>();
        objects.add(primitiveTypesAndStrings);
        objects.add(root);
        ArraysContainer arraysContainer = new ArraysContainer(1, ints, chars, objects);

        new TraversedObject(arraysContainer).accept(jsonObjectVisitor);
        System.out.println(jsonObjectVisitor.getJsonObject());

        Gson gson = new Gson();
        System.out.println(gson.toJson(arraysContainer));
    }

}
