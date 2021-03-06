package ru.otus.hw07.moneybag;

import ru.otus.hw07.Banknote;
import ru.otus.hw07.MemoStatus;

import java.util.EnumMap;

public class FacedCashWad implements CashWad,Cloneable {

    private final Banknote banknote;
    private int count;

    private class Memento {

        private final FacedCashWad facedCashWad;

        private Memento(FacedCashWad facedCashWad) {
            this.facedCashWad = facedCashWad.clone();
        }

        FacedCashWad getSaved() {
            return facedCashWad;
        }
    }

    private final EnumMap<MemoStatus, Memento> mementoMap = new EnumMap<>(MemoStatus.class);

    public FacedCashWad(Banknote banknote, int count) {
        this.banknote = banknote;
        this.count = count;
    }

    private FacedCashWad(FacedCashWad facedCashWad) {
        this.banknote = facedCashWad.banknote;
        this.count = facedCashWad.count;
    }

    @Override
    public void save(MemoStatus status) {
        mementoMap.put(status, new Memento(this));
    }

    @Override
    public void restore(MemoStatus status) {
        this.count = mementoMap.get(status).getSaved().count;
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
            this.count += noteCount;
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

    @Override
    public FacedCashWad clone() {
        return new FacedCashWad(this);
    }

    @Override
    public String toString() {
        return String.valueOf(count);
    }
}
