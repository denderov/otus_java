package ru.otus.hw08.visitor;

import ru.otus.hw08.helper.Condition;
import ru.otus.hw08.traversed.type.TraversedArray;
import ru.otus.hw08.traversed.type.TraversedField;
import ru.otus.hw08.traversed.type.TraversedObject;

import javax.json.JsonObjectBuilder;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Objects;

public class JsonFieldVisitor implements FieldVisitor {

    private final JsonObjectVisitor jsonObjectVisitor;

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
        if (Condition.isInteger(field.getType())) {
            jsonObjectBuilder.add(fieldName,field.getLong(parentObject));
        } else if (Condition.isFloatNumber(field.getType())) {
            jsonObjectBuilder.add(fieldName,field.getDouble(parentObject));
        } else if (Condition.isBoolean(field.getType())) {
            jsonObjectBuilder.add(fieldName,field.getBoolean(parentObject));
        } else if (Condition.isString(field.getType())) {
            jsonObjectBuilder.add(fieldName,field.get(parentObject).toString());
        } else if (Objects.isNull(field.get(parentObject))) {
            jsonObjectBuilder.addNull(field.getName());
        } else if (field.getType().isArray()) {
            JsonArrayVisitor jsonArrayVisitor = new JsonArrayVisitor();
            new TraversedArray(field.get(parentObject)).accept(jsonArrayVisitor);
            jsonObjectBuilder.add(fieldName, jsonArrayVisitor.getJsonArrayBuilder());
        } else if (Condition.isCollection(field.getType())) {
            JsonArrayVisitor jsonArrayVisitor = new JsonArrayVisitor();
            Collection<Object> currentCollection = (Collection<Object>) field.get(parentObject);
            Object[] array = currentCollection.toArray();
            new TraversedArray(array).accept(jsonArrayVisitor);
            jsonObjectBuilder.add(fieldName, jsonArrayVisitor.getJsonArrayBuilder());
        } else {
            JsonObjectVisitor fieldObjectVisitor = new JsonObjectVisitor();
            new TraversedObject(field.get(parentObject)).accept(fieldObjectVisitor);
            jsonObjectBuilder.add(fieldName, fieldObjectVisitor.getJsonObjectBuilder());
        }

    }
}
