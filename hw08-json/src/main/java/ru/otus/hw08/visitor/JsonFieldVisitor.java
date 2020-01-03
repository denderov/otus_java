package ru.otus.hw08.visitor;

import ru.otus.hw08.traversed.type.TraversedField;

import javax.json.JsonObjectBuilder;
import java.lang.reflect.Field;
import java.util.Objects;

public class JsonFieldVisitor implements FieldVisitor {

    private JsonObjectVisitor jsonObjectVisitor;

    JsonFieldVisitor(JsonObjectVisitor jsonObjectVisitor) {
        this.jsonObjectVisitor = jsonObjectVisitor;
    }

    @Override
    public void visit(TraversedField traversed) throws IllegalAccessException {
        JsonObjectBuilder jsonObjectBuilder = jsonObjectVisitor.getJsonObjectBuilder();
        Field field = traversed.getField();
        String fieldName = traversed.getFieldName();
        Object parentObject = traversed.getParentObject();
        field.setAccessible(true);
        if (isInteger(field.getType())) {
            jsonObjectBuilder.add(fieldName,field.getLong(parentObject));
        } else if (isFloatNumber(field.getType())) {
            jsonObjectBuilder.add(fieldName,field.getDouble(parentObject));
        } else if (isString(field.getType())) {
            jsonObjectBuilder.add(fieldName,field.get(parentObject).toString());
        } else if (Objects.isNull(field.get(parentObject))) {
            jsonObjectBuilder.addNull(field.getName());
        } else {

//            jsonObjectBuilder.add(fieldName,field.get(parentObject).toString();
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
}
