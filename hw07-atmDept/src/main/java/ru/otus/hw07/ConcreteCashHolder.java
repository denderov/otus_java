package ru.otus.hw07;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConcreteCashHolder implements CashHolder {
    private final SortedMap<Banknote, CashWad> moneybox = new TreeMap<>();

    @Override
    public CashHolder get(int sum) {
        saveMoneybox(MemoStatus.CURRENT);
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

//        this.moneybox.values().remove(0);

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

    private void saveMoneybox(MemoStatus current) {
    }
}
