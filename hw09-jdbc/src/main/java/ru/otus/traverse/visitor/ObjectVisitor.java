package ru.otus.traverse.visitor;

import ru.otus.traverse.type.TraversedObject;

public interface ObjectVisitor {
    void visit(TraversedObject traversedObject) throws IllegalAccessException;
}
