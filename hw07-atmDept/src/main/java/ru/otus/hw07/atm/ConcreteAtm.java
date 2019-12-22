package ru.otus.hw07.atm;

import ru.otus.hw07.Banknote;
import ru.otus.hw07.moneybag.CashHolder;
import ru.otus.hw07.moneybag.ConcreteCashHolder;

public class ConcreteAtm implements Atm {

    private final CashHolder cassette = new ConcreteCashHolder();

    @Override
    public Atm replenishment(Banknote faceValue, int noteCount) {

        cassette.putBanknotes(faceValue, noteCount);

        return this;

    }

    @Override
    public void withdraw(int amount) {

        try {

            CashHolder withdrawCash = cassette.get(amount);
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
