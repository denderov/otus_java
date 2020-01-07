package ru.otus.hw08;

import ru.otus.hw08.traversed.type.TraversedElement;
import ru.otus.hw08.visitor.JsonElementVisitor;

public class ElementTraverser {

    private Object o;
    private JsonElementVisitor visitor;

    ElementTraverser(Object o, JsonElementVisitor visitor) {
        this.o = o;
        this.visitor = visitor;
    }

    public ElementTraverser traverse() throws IllegalAccessException {
        new TraversedElement(o).accept(visitor);
        return this;
    }

    public JsonElementVisitor getVisitor() {
        return visitor;
    }
}
