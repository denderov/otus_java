package ru.otus.hw08.traversed.object;

import java.util.Objects;

public class Nested {

    private final long id;
    private final Nested nestedInNested;

    public Nested(long id, Nested nestedInNested) {
        this.id = id;
        this.nestedInNested = nestedInNested;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nested nested = (Nested) o;
        return id == nested.id &&
                Objects.equals(nestedInNested, nested.nestedInNested);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nestedInNested);
    }
}
