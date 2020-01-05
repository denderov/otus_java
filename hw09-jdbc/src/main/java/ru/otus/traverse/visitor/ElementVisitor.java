package ru.otus.traverse.visitor;

import ru.otus.traverse.type.TraversedElement;

public interface ElementVisitor {
    void visit(TraversedElement traversedElement) throws IllegalAccessException;
}
