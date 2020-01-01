package ru.otus.hw08.traversed.type;

import ru.otus.hw08.visitor.Visitor;

import java.lang.reflect.Field;

public class TraversedPrimitive extends TraversedField {

    public TraversedPrimitive(Object parentObject, Field field) {
        super(parentObject,field);
    }

    @Override
    public void accept(Visitor visitor) throws IllegalAccessException {
//        visitor.visit(this);
    }
}
