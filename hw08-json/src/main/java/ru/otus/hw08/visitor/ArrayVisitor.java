package ru.otus.hw08.visitor;

import ru.otus.hw08.traversed.type.TraversedArray;

public interface ArrayVisitor {
    void visit(TraversedArray traversedArray) throws IllegalAccessException;
}
