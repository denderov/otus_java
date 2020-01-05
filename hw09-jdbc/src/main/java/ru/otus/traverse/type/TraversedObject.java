package ru.otus.traverse.type;

import ru.otus.traverse.visitor.ObjectVisitor;

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
