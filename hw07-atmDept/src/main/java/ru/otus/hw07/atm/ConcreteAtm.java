package ru.otus.hw07.atm;

import ru.otus.hw07.Banknote;
import ru.otus.hw07.MemoStatus;
import ru.otus.hw07.moneybag.CashHolder;
import ru.otus.hw07.moneybag.ConcreteCashHolder;

import java.util.EnumMap;
import java.util.Map;

public class ConcreteAtm implements Atm {

    private CashHolder cassette = new ConcreteCashHolder();

    private ConcreteAtm(ConcreteAtm concreteAtm) {
        this.cassette = concreteAtm.cassette.clone();
    }

    public ConcreteAtm() {
    }

    private class Memento {

        private ConcreteAtm savedAtm;

        private Memento(ConcreteAtm concreteAtm) {
            this.savedAtm = concreteAtm.clone();
        }

        ConcreteAtm getSaved() {
            return savedAtm;
        }
    }

    private final Map<MemoStatus, Memento> mementoMap = new EnumMap<>(MemoStatus.class);

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
    public int getAmount() {

        return cassette.getAmount();

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

    @Override
    public void save(MemoStatus status) {
        mementoMap.put(status, new Memento(this));
    }

    @Override
    public void restore(MemoStatus status) {
        this.cassette = mementoMap.get(status).getSaved().cassette;
    }

    @Override
    public void save() {
        save(MemoStatus.INIT);
    }

    @Override
    public void restore() {
        restore(MemoStatus.INIT);
    }

    @Override
    public ConcreteAtm clone() {
        return new ConcreteAtm(this);
    }

}
