package ru.otus.hw07;

import java.util.HashMap;
import java.util.Map;

public class FacedCashWad implements CashWad {

    private final Banknote banknote;
    private int count;

    private class Memento {
        private final int memCount;

        private Memento() {
            this.memCount = count;
        }

        int getCount() {
            return memCount;
        }
    }

    private final Map<MemoStatus, Memento> mementoMap = new HashMap<>();

    public FacedCashWad(Banknote banknote, int count) {
        this.banknote = banknote;
        this.count = count;
    }

    @Override
    public void save(MemoStatus status) {
        mementoMap.put(status, new Memento());
    }

    @Override
    public void restore(MemoStatus status) {
        this.count = mementoMap.get(status).getCount();
    }

    @Override
    public CashWad getBanknotes(int noteCount) {

        CashWad outCashWad;

        if (noteCount < 0) {
            throw new RuntimeException("The count of the withdraw cannot be less than zero!");
        } else if (noteCount > this.count) {
            throw new RuntimeException("The count of the withdraw cannot be more than the existing!");
        } else {
            outCashWad = new FacedCashWad(this.banknote, noteCount);
            this.count -= noteCount;
        }

        return outCashWad;
    }

    @Override
    public CashWad putBanknotes(int noteCount) {
        if (noteCount > 0) {
            this.count = noteCount;
        } else {
            throw new RuntimeException("The count of the deposit cannot be less than the existing!");
        }

        return this;
    }

    @Override
    public int getFaceValue() {
        return this.banknote.getFaceValue();
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public int getAmount() {
        return this.getFaceValue() * this.getCount();
    }
}
