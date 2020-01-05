package ru.otus.traverse.visitor;

import ru.otus.traverse.type.TraversedArray;

public interface ArrayVisitor {
    void visit(TraversedArray traversedArray) throws IllegalAccessException;
}
