package ru.otus.traverse.type;

import ru.otus.traverse.visitor.ArrayVisitor;

public class TraversedArray {
    private final Object array;

    public TraversedArray(Object array) {
        this.array = array;
    }

    public void accept(ArrayVisitor arrayVisitor) throws IllegalAccessException {
        arrayVisitor.visit(this);
    }

    public Object getArray() {
        return array;
    }
}
