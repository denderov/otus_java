package ru.otus.hw07.moneybag;

import ru.otus.hw07.Banknote;
import ru.otus.hw07.MemoStatus;

import java.util.*;

public class ConcreteCashHolder implements CashHolder,Cloneable {

    public SortedMap<Banknote, CashWad> getMoneybox() {
        return moneybox;
    }

    private SortedMap<Banknote, CashWad> moneybox = new TreeMap<>();

    private ConcreteCashHolder(ConcreteCashHolder concreteCashHolder) {
        for (SortedMap.Entry<Banknote, CashWad> entry :
                concreteCashHolder.moneybox.entrySet()) {
            CashWad cashWad = entry.getValue().clone();
            this.moneybox.put(entry.getKey(), cashWad);
        }
    }

    public ConcreteCashHolder() {

    }

    private final Map<MemoStatus, CashHolderMemento> mementoMap = new EnumMap<>(MemoStatus.class);

    @Override
    public CashHolder get(int sum) {
        save(MemoStatus.CURRENT);
        CashHolder outCashHolder = new ConcreteCashHolder();


        SortedMap<Banknote, CashWad> reversedMoneybox = new TreeMap<>(Collections.reverseOrder());

        reversedMoneybox.putAll(moneybox);

        for (Map.Entry<Banknote, CashWad> entry : reversedMoneybox.entrySet()) {

            Banknote key = entry.getKey();
            int faceValue = key.getFaceValue();
            int noteCount = entry.getValue().getCount();

            int quotient = sum / faceValue;

            int withdrawNoteCount = Math.min(noteCount, quotient);

            if (withdrawNoteCount > 0) {
                this.getBanknotes(key, withdrawNoteCount);
                outCashHolder.putBanknotes(key, withdrawNoteCount);
            }

            sum -= withdrawNoteCount * faceValue;

        }

        if (sum > 0) {
            throw new RuntimeException("It's impossible to collect cash!");
        }

        return outCashHolder;
    }

    @Override
    public void getBanknotes(Banknote faceValue, int noteCount) {
        CashWad cashWad;

        if (moneybox.containsKey(faceValue)) {
            cashWad = moneybox.get(faceValue);
            cashWad.getBanknotes(noteCount);
        } else {
            throw new RuntimeException("There's no cash wad with face value " + faceValue.getFaceValue() + "!");
        }
    }

    @Override
    public CashHolder putBanknotes(Banknote faceValue, int noteCount) {
        CashWad cashWad;

        if (moneybox.containsKey(faceValue)) {
            cashWad = moneybox.get(faceValue);
            cashWad.putBanknotes(noteCount);
        } else {
            cashWad = new FacedCashWad(faceValue, noteCount);
            moneybox.put(faceValue, cashWad);
        }

        return this;
    }

    @Override
    public int getAmount() {
        return moneybox.values()
                .stream()
                .mapToInt(CashWad::getAmount)
                .sum();
    }

    @Override
    public void save(MemoStatus current) {
        mementoMap.put(current, new CashHolderMemento(this));
    }

    @Override
    public void restore(MemoStatus status) {
        moneybox = mementoMap.get(status).getSaved().getMoneybox();
    }

    @Override
    public ConcreteCashHolder clone() {

        return new ConcreteCashHolder(this);
    }

    @Override
    public String toString() {

        if (moneybox.isEmpty()) {
            return "Cash holder is empty";

        } else {
            return "Cash holder: " + moneybox;
        }

    }
}
