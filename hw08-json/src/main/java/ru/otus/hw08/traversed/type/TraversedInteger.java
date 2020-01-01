package ru.otus.hw08.traversed.type;

import ru.otus.hw08.visitor.Visitor;

import java.lang.reflect.Field;

public class TraversedInteger extends TraversedField {

    private long value;

    public TraversedInteger(Object parentObject, Field field) throws IllegalAccessException {
        super(parentObject,field);
        value = this.getValue();
    }

    public long getValue() throws IllegalAccessException {
        return getField().getLong(getParentObject());
    }

    @Override
    public void accept(Visitor visitor) throws IllegalAccessException {
        visitor.visit(this);
    }
}
