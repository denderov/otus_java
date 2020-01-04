package ru.otus.hw08.visitor;

import ru.otus.hw08.traversed.type.TraversedArray;
import ru.otus.hw08.traversed.type.TraversedObject;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Objects;

public class JsonArrayVisitor implements ArrayVisitor {
    private final JsonArrayBuilder jsonArrayBuilder;

    JsonArrayVisitor() {
        jsonArrayBuilder = Json.createArrayBuilder();
    }

    @Override
    public void visit(TraversedArray traversedArray) throws IllegalAccessException {
        Object array = traversedArray.getArray();
        int length = Array.getLength(array);
        Class<?> type = array.getClass().getComponentType();
        for (int i = 0; i < length; i++) {
            if (isInteger(type)) {
                jsonArrayBuilder.add(Array.getLong(array,i));
            }
            else if (isFloatNumber(type)) {
                jsonArrayBuilder.add(Array.getDouble(array,i));
            } else if (isBoolean(type)) {
                jsonArrayBuilder.add(Array.getBoolean(array, i));
            } else {
                Object currentObject = Array.get(array, i);
                Class<?> objectType = currentObject.getClass();
                if (Objects.isNull(currentObject)) {
                    jsonArrayBuilder.addNull();
                } else if (isString(objectType)) {
                    jsonArrayBuilder.add(currentObject.toString());
                } else if (objectType.isArray()) {
                    JsonArrayVisitor jsonArrayVisitor = new JsonArrayVisitor();
                    new TraversedArray(currentObject).accept(jsonArrayVisitor);
                    jsonArrayBuilder.add(jsonArrayVisitor.getJsonArrayBuilder());
                } else if (isCollection(type)) {
                    JsonArrayVisitor jsonArrayVisitor = new JsonArrayVisitor();
                    Collection<Object> currentCollection = (Collection<Object>) currentObject;
                    new TraversedArray(currentCollection.toArray()).accept(jsonArrayVisitor);
                    jsonArrayBuilder.add(jsonArrayVisitor.getJsonArrayBuilder());
                } else {
                    JsonObjectVisitor fieldObjectVisitor = new JsonObjectVisitor();
                    new TraversedObject(currentObject).accept(fieldObjectVisitor);
                    jsonArrayBuilder.add(fieldObjectVisitor.getJsonObjectBuilder());
                }
            }
        }

    }

    JsonArrayBuilder getJsonArrayBuilder() {
        return jsonArrayBuilder;
    }

    private boolean isString(Class<?> type) {
        return type == char.class
                || type == Character.class
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

    private boolean isBoolean(Class<?> type) {
        return type == boolean.class;
    }

    public boolean isCollection(Class<?> type) {
        return Collection.class.isAssignableFrom(type);
    }
}
