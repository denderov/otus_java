package ru.otus.hw08.visitor;

import ru.otus.hw08.helper.Condition;
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
            if (Condition.isInteger(type)) {
                jsonArrayBuilder.add(Array.getLong(array,i));
            }
            else if (Condition.isFloatNumber(type)) {
                jsonArrayBuilder.add(Array.getDouble(array,i));
            } else if (Condition.isBoolean(type)) {
                jsonArrayBuilder.add(Array.getBoolean(array, i));
            } else {
                Object currentObject = Array.get(array, i);
                if (Objects.isNull(currentObject)) {
                    jsonArrayBuilder.addNull();
                } else {
                    Class<?> objectType = currentObject.getClass();

                    if (Condition.isString(objectType)) {
                        jsonArrayBuilder.add(currentObject.toString());
                    } else if (objectType.isArray()) {
                        JsonArrayVisitor jsonArrayVisitor = new JsonArrayVisitor();
                        new TraversedArray(currentObject).accept(jsonArrayVisitor);
                        jsonArrayBuilder.add(jsonArrayVisitor.getJsonArrayBuilder());
                    } else if (Condition.isCollection(type)) {
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

    }

    JsonArrayBuilder getJsonArrayBuilder() {
        return jsonArrayBuilder;
    }
}
