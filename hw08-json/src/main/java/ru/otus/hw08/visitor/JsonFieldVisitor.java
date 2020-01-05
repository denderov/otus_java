package ru.otus.hw08.visitor;

import ru.otus.hw08.traversed.type.TraversedElement;
import ru.otus.hw08.traversed.type.TraversedField;

import javax.json.JsonObjectBuilder;
import java.lang.reflect.Field;

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

        JsonElementVisitor jsonElementVisitor = new JsonElementVisitor();
        TraversedElement traversedElement = new TraversedElement(field.get(parentObject));
        traversedElement.accept(jsonElementVisitor);
        jsonObjectBuilder.add(fieldName,jsonElementVisitor.getJsonValue());

    }
}
