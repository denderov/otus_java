package ru.otus.hw08.traversed.type;

import ru.otus.hw08.visitor.ArrayVisitor;

public class TraversedArray {
    private Object array;

    public TraversedArray(Object array) {
        this.array = array;
    }

    public void accept(ArrayVisitor arrayVisitor) {
        arrayVisitor.visit(this);
    }

    public Object getArray() {
        return array;
    }
}
