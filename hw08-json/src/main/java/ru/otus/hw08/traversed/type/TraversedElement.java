package ru.otus.hw08.traversed.type;

import ru.otus.hw08.visitor.ElementVisitor;

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