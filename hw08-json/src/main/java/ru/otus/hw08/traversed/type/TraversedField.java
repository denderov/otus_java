package ru.otus.hw08.traversed.type;

import java.lang.reflect.Field;

public abstract class TraversedField implements TraversedElement{

    private final Object parentObject;
    private final Field field;

    TraversedField(Object parentObject, Field field) {
        this.parentObject = parentObject;
        this.field = field;
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
}
