package ru.otus.hw08.traversed.type;

import ru.otus.hw08.visitor.Visitor;

public interface TraversedElement {
    void accept(Visitor visitor) throws IllegalAccessException;
}
