package ru.otus.traverse.visitor;

import ru.otus.traverse.type.TraversedField;

public interface FieldVisitor {
    void visit(TraversedField traversedField) throws IllegalAccessException;
}
