package ru.otus.hw08.visitor;

import ru.otus.hw08.traversed.type.TraversedArray;
import ru.otus.hw08.traversed.type.TraversedField;
import ru.otus.hw08.traversed.type.TraversedObject;

import javax.json.JsonObjectBuilder;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Objects;

public class JsonFieldVisitor implements FieldVisitor {

    private JsonObjectVisitor jsonObjectVisitor;

    JsonFieldVisitor(JsonObjectVisitor jsonObjectVisitor) {
        this.jsonObjectVisitor = jsonObjectVisitor;
    }

    @Override
    public void visit(TraversedField traversedField) throws IllegalAccessException {
        JsonObjectBuilder jsonObjectBuilder = jsonObjectVisitor.getJsonObjectBuilder();
        Field field = traversedField.getField();
        String fieldName = traversedField.getFieldName();
        Object parentObject = traversedField.getParentObject();
        field.setAccessible(true);
        if (isInteger(field.getType())) {
            jsonObjectBuilder.add(fieldName,field.getLong(parentObject));
        } else if (isFloatNumber(field.getType())) {
            jsonObjectBuilder.add(fieldName,field.getDouble(parentObject));
        } else if (isBoolean(field.getType())) {
            jsonObjectBuilder.add(fieldName,field.getBoolean(parentObject));
        } else if (isString(field.getType())) {
            jsonObjectBuilder.add(fieldName,field.get(parentObject).toString());
        } else if (Objects.isNull(field.get(parentObject))) {
            jsonObjectBuilder.addNull(field.getName());
        } else if (field.getType().isArray()) {
            JsonArrayVisitor jsonArrayVisitor = new JsonArrayVisitor();
            new TraversedArray(field.get(parentObject)).accept(jsonArrayVisitor);
            jsonObjectBuilder.add(fieldName, jsonArrayVisitor.getJsonArrayBuilder());
        } else if (isCollection(field.getType())) {
            JsonArrayVisitor jsonArrayVisitor = new JsonArrayVisitor();
            Collection<Object> currentCollection = (Collection<Object>) field.get(parentObject);
            new TraversedArray(currentCollection.toArray()).accept(jsonArrayVisitor);
            jsonObjectBuilder.add(fieldName, jsonArrayVisitor.getJsonArrayBuilder());
        } else {
            JsonObjectVisitor fieldObjectVisitor = new JsonObjectVisitor();
            new TraversedObject(field.get(parentObject)).accept(fieldObjectVisitor);
            jsonObjectBuilder.add(fieldName, fieldObjectVisitor.getJsonObjectBuilder());
        }

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

    private boolean isBoolean(Class<?> type) {
        return type == boolean.class;
    }

    private boolean isCollection(Class<?> type) {
        return Collection.class.isAssignableFrom(type);
    }
}
