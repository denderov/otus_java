package ru.otus.hw08;

import ru.otus.hw08.traversed.object.Nested;
import ru.otus.hw08.traversed.object.PrimitiveTypesAndStrings;
import ru.otus.hw08.traversed.object.Root;
import ru.otus.hw08.traversed.type.TraversedObject;
import ru.otus.hw08.visitor.JsonObjectVisitor;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {
        JsonObjectVisitor jsonObjectVisitor = new JsonObjectVisitor();

        PrimitiveTypesAndStrings primitiveTypesAndStrings = new PrimitiveTypesAndStrings((byte) 1, 2, 3, 4.5f,6.7, '3', "currency");

        new TraversedObject(primitiveTypesAndStrings).accept(jsonObjectVisitor);

        System.out.println(jsonObjectVisitor.getJsonObject());

        jsonObjectVisitor = new JsonObjectVisitor();

        Nested nestedInNested = new Nested(789, null);
        Nested nested = new Nested(456, nestedInNested);
        Root root = new Root(123, "Root", nested);

        new TraversedObject(root).accept(jsonObjectVisitor);
        System.out.println(jsonObjectVisitor.getJsonObject());
    }

}
