package ru.otus.hw08.traversed.type;

import ru.otus.hw08.visitor.Visitor;

import java.lang.reflect.Field;

public class TraversedString extends TraversedField {

    private String value;

    public TraversedString(Object parentObject, Field field) throws IllegalAccessException {
        super(parentObject,field);
        value = this.getValue();
    }

    public String getValue() throws IllegalAccessException {
        return getField().get(getParentObject()).toString();
    }

    @Override
    public void accept(Visitor visitor) throws IllegalAccessException {
        visitor.visit(this);
    }
}
