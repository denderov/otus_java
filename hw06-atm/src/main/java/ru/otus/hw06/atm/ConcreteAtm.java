package ru.otus.hw06.atm;

import ru.otus.hw06.Banknote;
import ru.otus.hw06.atm.cassette_bundle.CassetteBundle;
import ru.otus.hw06.atm.cassette_bundle.ConcreteCassetteBundle;
import ru.otus.hw06.cash_bundle.CashBundle;

public class ConcreteAtm implements Atm {

    private final CassetteBundle cassette = new ConcreteCassetteBundle();

    @Override
    public Atm replenishment(Banknote faceValue, int noteCount) {

        cassette.put(faceValue, noteCount);

        return this;

    }

    @Override
    public void withdraw(int amount) {

        try {

            CashBundle withdrawCash = cassette.get(amount);
            System.out.println("Withdraw cash: {" + withdrawCash.toString() + "}");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @Override
    public void getAmountBalance() {

        int amountBalance = cassette.getAmount();
        System.out.println("ATM: {Amount=" + amountBalance + '}');

    }

    @Override
    public void getCashBalance() {

        System.out.println(this.toString());

    }

    @Override
    public String toString() {
        return "ATM: {" +
                "Cassette=" + cassette +
                '}';
    }
}
