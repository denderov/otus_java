package ru.otus.hw08.visitor;

import ru.otus.hw08.traversed.type.TraversedObject;

public interface ObjectVisitor {
    void visit(TraversedObject traversedObject) throws IllegalAccessException;
}
