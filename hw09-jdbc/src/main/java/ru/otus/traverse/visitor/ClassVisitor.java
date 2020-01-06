package ru.otus.traverse.visitor;

import ru.otus.traverse.type.TraversedClass;

public interface ClassVisitor {
    void visit(TraversedClass traversedClass) throws IllegalAccessException;
}
