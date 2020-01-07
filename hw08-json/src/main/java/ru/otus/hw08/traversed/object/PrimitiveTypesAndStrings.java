package ru.otus.hw08.traversed.object;

import java.util.Objects;

public class PrimitiveTypesAndStrings {
    private final byte i;
    private final int id;
    private final int longId;
    private final float quantity;
    private final double sum;
    private final char litera;
    private final boolean checked;
    private final String currency;

    public PrimitiveTypesAndStrings(byte i, int id, int longId, float quantity, double sum, char litera, boolean checked, String currency) {
        this.i = i;
        this.id = id;
        this.longId = longId;
        this.quantity = quantity;
        this.sum = sum;
        this.litera = litera;
        this.checked = checked;
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrimitiveTypesAndStrings that = (PrimitiveTypesAndStrings) o;
        return i == that.i &&
                id == that.id &&
                longId == that.longId &&
                Float.compare(that.quantity, quantity) == 0 &&
                Double.compare(that.sum, sum) == 0 &&
                litera == that.litera &&
                checked == that.checked &&
                Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, id, longId, quantity, sum, litera, checked, currency);
    }
}
