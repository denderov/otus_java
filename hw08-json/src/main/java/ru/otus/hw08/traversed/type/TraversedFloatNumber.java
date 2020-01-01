package ru.otus.hw08.traversed.type;

import ru.otus.hw08.visitor.Visitor;

import java.lang.reflect.Field;

public class TraversedFloatNumber extends TraversedField {
    private double value;

    public TraversedFloatNumber(Object parentObject, Field field) throws IllegalAccessException {
        super(parentObject,field);
        value = this.getValue();
    }

    public double getValue() throws IllegalAccessException {
        return getField().getDouble(getParentObject());
    }

    @Override
    public void accept(Visitor visitor) throws IllegalAccessException {
        visitor.visit(this);
    }
}
