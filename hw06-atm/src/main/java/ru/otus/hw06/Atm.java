package ru.otus.hw06;

public class Atm {

    private CashBundle cassette = new CashBundle();

    public Atm replenishment(Banknote faceValue, int noteCount) {

        cassette.put(faceValue, noteCount);

        return this;

    }

    public CashBundle withdraw(int amount) {

        return cassette.get(amount);

    }

    @Override
    public String toString() {
        return "ATM{" +
                "Cassette=" + cassette +
                '}';
    }
}
