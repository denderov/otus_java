package ru.otus.hw06;

public enum Banknote {
    FIVE(5), TEN(10), FIFTY(50), ONE_HUNDRED(100), TWO_HUNDRED(200),
    FIVE_HUNDRED(500), ONE_THOUSAND(1000), TWO_THOUSAND(2000), FIVE_THOUSAND(5000);

    private final int faceValue;

    Banknote(int faceValue) {
        this.faceValue = faceValue;
    }

    public int getFaceValue() {
        return faceValue;
    }

    @Override
    public String toString() {
        return String.valueOf(faceValue);
    }
}
