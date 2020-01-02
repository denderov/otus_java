package ru.otus.hw08.traversed.object;

import java.util.Objects;

public class Root {

    private final int id;
    private final String string;
    private final Nested nestedObject;

    public Root(int id, String string, Nested nestedObject) {
        this.id = id;
        this.string = string;
        this.nestedObject = nestedObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Root root = (Root) o;
        return id == root.id &&
                Objects.equals(string, root.string) &&
                Objects.equals(nestedObject, root.nestedObject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, string, nestedObject);
    }
}
