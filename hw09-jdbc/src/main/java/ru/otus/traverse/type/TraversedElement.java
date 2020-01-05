package ru.otus.traverse.type;

import ru.otus.traverse.visitor.ElementVisitor;

public class TraversedElement {
    private final Object object;

    public TraversedElement(Object object) {
        this.object = object;
    }

    public void accept(ElementVisitor elementVisitor) throws IllegalAccessException {
        elementVisitor.visit(this);
    }

    public Object getElement() {
        return object;
    }
}