package ru.otus.hw08.traversed.object;

public class Nested {

    private final long id;
    private final Nested nestedInNested;

    public Nested(long id, Nested nestedInNested) {
        this.id = id;
        this.nestedInNested = nestedInNested;
    }
}
