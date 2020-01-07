package ru.otus.hw07.moneybag;

public class CashHolderMemento {
    private CashHolder savedCashHolder;

    public CashHolderMemento(CashHolder cashHolder) {
        this.savedCashHolder = cashHolder.clone();
    }

    public CashHolder getSaved() {
        return savedCashHolder;
    }
}

