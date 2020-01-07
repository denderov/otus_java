package ru.otus.hw08.visitor;

import ru.otus.hw08.traversed.type.TraversedElement;

public interface ElementVisitor {
    void visit(TraversedElement traversedElement) throws IllegalAccessException;
}
