package ru.otus.hw06;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class CashBundle {

    private SortedMap<Banknote, Integer> moneybox = new TreeMap<>();

    public CashBundle get(int sum) {

        CashBundle outCashBundle = new CashBundle();

        SortedMap<Banknote, Integer> reversedMoneybox = new TreeMap<>(Collections.reverseOrder());

        reversedMoneybox.putAll(moneybox);

        for (Map.Entry<Banknote, Integer> entry : reversedMoneybox.entrySet()) {

            Banknote key = entry.getKey();
            int faceValue = key.getFaceValue();
            int noteCount = entry.getValue();

            int quotient = sum / faceValue;

            int withdrawNoteCount = Math.min(noteCount, quotient);

            this.put(key, -withdrawNoteCount);
            if (withdrawNoteCount > 0) {
                outCashBundle.put(key, withdrawNoteCount);
            }

            sum -= withdrawNoteCount * faceValue;

        }

        if (sum > 0) {
            throw new RuntimeException("It's impossible to collect cash bundle!");
        }

        this.moneybox.values().remove(0);

        return outCashBundle;
    }

    public CashBundle put(Banknote faceValue, int noteCount) {

        moneybox.merge(faceValue, noteCount, Integer::sum);

        return this;

    }

    @Override
    public String toString() {

        if (moneybox.isEmpty()) {
            return "Cash bundle is empty";

        } else {
            return "Cash bundle: "+ moneybox;
        }

    }
}
