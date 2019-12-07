package ru.otus.hw06;

public class ConcreteAtm implements Atm {

    private final CashBundle cassette = new ConcreteCashBundle();

    @Override
    public ConcreteAtm replenishment(Banknote faceValue, int noteCount) {

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
        System.out.println("ATM: {Amount=" +amountBalance+'}');

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
