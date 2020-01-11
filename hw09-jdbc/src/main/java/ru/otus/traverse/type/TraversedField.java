package ru.otus.traverse.type;

import ru.otus.traverse.visitor.FieldVisitor;

import java.lang.reflect.Field;

public class TraversedField {

    private final Object parentObject;
    private final Field field;

    public TraversedField(Object parentObject, Field field) {
        this.parentObject = parentObject;
        this.field = field;
    }

    public TraversedField(Field field) {
        this.field = field;
        parentObject = null;
    }

    public String getFieldName() {
        return field.getName();
    }

    public Field getField() {
        return field;
    }

    public Object getParentObject() {
        return parentObject;
    }

    public void accept(FieldVisitor fieldVisitor) throws IllegalAccessException {
        fieldVisitor.visit(this);
    }

}
