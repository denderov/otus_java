package ru.otus.hw08.traversed.object;

public class Root {

    private final int id;
    private final String string;
    private final Nested nestedObject;

    public Root(int id, String string, Nested nestedObject) {
        this.id = id;
        this.string = string;
        this.nestedObject = nestedObject;
    }
}
