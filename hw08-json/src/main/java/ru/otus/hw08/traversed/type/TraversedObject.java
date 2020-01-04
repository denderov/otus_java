package ru.otus.hw08.traversed.type;

import ru.otus.hw08.visitor.ObjectVisitor;

public class TraversedObject {
    private final Object object;

    public TraversedObject(Object object) {
        this.object = object;
    }

    public void accept(ObjectVisitor objectVisitor) throws IllegalAccessException {
        objectVisitor.visit(this);
    }

    public Object getObject() {
        return object;
    }
}
