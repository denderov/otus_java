package ru.otus.hw06;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class CashBundle {

    private SortedMap<Integer, Integer> moneybox = new TreeMap<>();

    public CashBundle get(int sum) {

        CashBundle outCashBundle = new CashBundle();

        SortedMap<Integer, Integer> reversedMoneybox = new TreeMap<>(Collections.reverseOrder());

        reversedMoneybox.putAll(moneybox);

        for (Map.Entry<Integer, Integer> entry : reversedMoneybox.entrySet()) {

            int faceValue = entry.getKey();
            int noteCount = entry.getValue();

            int quotient = sum / faceValue;

            int withdrawNoteCount = Math.min(noteCount, quotient);

            this.put(faceValue, -withdrawNoteCount);
            outCashBundle.put(faceValue, withdrawNoteCount);

            sum -= withdrawNoteCount * faceValue;

        }

        return outCashBundle;
    }

    public CashBundle put(int faceValue, int noteCount) {

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
