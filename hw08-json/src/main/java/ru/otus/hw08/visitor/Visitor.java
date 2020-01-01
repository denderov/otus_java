package ru.otus.hw08.visitor;

import ru.otus.hw08.traversed.type.*;
import ru.otus.hw08.traversed_type.*;

public interface Visitor {
    void visit(TraversedInteger traversed) throws IllegalAccessException;

    void visit(TraversedFloatNumber traversed) throws IllegalAccessException;

    void visit(TraversedString traversed) throws IllegalAccessException;

    void visit(TraversedObjectHead traversed) throws IllegalAccessException;

    void visit(TraversedNull traversed) throws IllegalAccessException;

    void visit(TraversedObjectBottom traversed) throws IllegalAccessException;
}
