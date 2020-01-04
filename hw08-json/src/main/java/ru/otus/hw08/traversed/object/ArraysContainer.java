package ru.otus.hw08.traversed.object;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class ArraysContainer {
    private final long id;
    private int[] quantityChain;
    private char[][] chars;
    private Collection<Object> objects;

    public ArraysContainer(long id, int[] quantityChain, char[][] chars, Collection<Object> objects) {
        this.id = id;
        this.quantityChain = quantityChain;
        this.chars = chars;
        this.objects = objects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArraysContainer that = (ArraysContainer) o;
        return id == that.id &&
                Arrays.equals(quantityChain, that.quantityChain) &&
                Arrays.equals(chars, that.chars) &&
                Objects.equals(objects, that.objects);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, objects);
        result = 31 * result + Arrays.hashCode(quantityChain);
        result = 31 * result + Arrays.hashCode(chars);
        return result;
    }
}
