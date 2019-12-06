package ru.otus.hw06;

public class ConcreteAtm implements Atm {

    private CashBundle cassette = new CashBundle();

    @Override
    public ConcreteAtm replenishment(Banknote faceValue, int noteCount) {

        cassette.put(faceValue, noteCount);

        return this;

    }

    @Override
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
