package ru.otus.hw08.visitor;

import ru.otus.hw08.traversed.type.TraversedArray;
import ru.otus.hw08.traversed.type.TraversedElement;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import java.lang.reflect.Array;

public class JsonArrayVisitor implements ArrayVisitor {
    private final JsonArrayBuilder jsonArrayBuilder;

    JsonArrayVisitor() {
        jsonArrayBuilder = Json.createArrayBuilder();
    }

    @Override
    public void visit(TraversedArray traversedArray) throws IllegalAccessException {
        Object array = traversedArray.getArray();
        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            JsonElementVisitor jsonElementVisitor = new JsonElementVisitor();
            TraversedElement traversedElement = new TraversedElement(Array.get(array,i));
            traversedElement.accept(jsonElementVisitor);
            jsonArrayBuilder.add(jsonElementVisitor.getJsonValue());
        }

    }

    JsonArrayBuilder getJsonArrayBuilder() {
        return jsonArrayBuilder;
    }
}
