package ru.otus.hw08.visitor;

import ru.otus.hw08.traversed.type.TraversedField;

public interface FieldVisitor {
    void visit(TraversedField traversed) throws IllegalAccessException;
}
