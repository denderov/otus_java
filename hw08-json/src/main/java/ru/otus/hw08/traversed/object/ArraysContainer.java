package ru.otus.hw08.traversed.object;

import java.util.Arrays;
import java.util.Collection;

public class ArraysContainer {
    private final long id;
    private int[] quantityChain;
    private char[][] chars;
    private Collection<String> names;

    public ArraysContainer(long id, int[] quantityChain, char[][] chars, Collection<String> names) {
        this.id = id;
        this.quantityChain = quantityChain;
        this.chars = chars;
        this.names = names;
    }

    @Override
    public String toString() {
        return "ArraysContainer{" +
                "id=" + id +
                ", quantityChain=" + Arrays.toString(quantityChain) +
                ", chars=" + Arrays.toString(chars) +
                ", names=" + names +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArraysContainer that = (ArraysContainer) o;

        if (id != that.id) return false;
        if (!Arrays.equals(quantityChain, that.quantityChain)) return false;
        if (!Arrays.deepEquals(chars, that.chars)) return false;
        return names != null ? names.equals(that.names) : that.names == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + Arrays.hashCode(quantityChain);
        result = 31 * result + Arrays.deepHashCode(chars);
        result = 31 * result + (names != null ? names.hashCode() : 0);
        return result;
    }
}
