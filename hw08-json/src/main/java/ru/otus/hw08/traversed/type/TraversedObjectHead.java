package ru.otus.hw08.traversed.type;

import ru.otus.hw08.visitor.Visitor;

import java.lang.reflect.Field;

public class TraversedObjectHead extends TraversedField {

    private Object object;

    public TraversedObjectHead(Object parentObject, Field field, Object object) {
        super(parentObject,field);
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    @Override
    public void accept(Visitor visitor) throws IllegalAccessException {
        visitor.visit(this);
    }
}
