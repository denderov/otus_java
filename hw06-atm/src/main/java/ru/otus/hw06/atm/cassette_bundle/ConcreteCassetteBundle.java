package ru.otus.hw06.atm.cassette_bundle;

import ru.otus.hw06.Banknote;
import ru.otus.hw06.cash_bundle.CashBundle;
import ru.otus.hw06.cash_bundle.ConcreteCashBundle;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConcreteCassetteBundle implements CassetteBundle {

    private final SortedMap<Banknote, Cassette> bundle = new TreeMap<>();

    @Override
    public CashBundle get(int sum) {
        CashBundle outCashBundle = new ConcreteCashBundle();

        SortedMap<Banknote, Cassette> reversedBundle = new TreeMap<>(Collections.reverseOrder());

        reversedBundle.putAll(bundle);

        for (Map.Entry<Banknote, Cassette> entry : reversedBundle.entrySet()) {

            Banknote key = entry.getKey();
            int faceValue = key.getFaceValue();
            int noteCount = entry.getValue().getCount();

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

        return outCashBundle;
    }

    @Override
    public CassetteBundle put(Banknote banknote, int noteCount) {

        Cassette cassette;

        if (bundle.containsKey(banknote)) {
            cassette = bundle.get(banknote);
            cassette.put(noteCount);
        } else {
            cassette = new ConcreteCassette(banknote, noteCount);
            bundle.put(banknote, cassette);
        }

        return this;
    }

    @Override
    public int getAmount() {
        return bundle.values()
                .stream()
                .mapToInt(Cassette::getAmount)
                .sum();
    }

    @Override
    public String toString() {

        if (bundle.isEmpty()) {
            return "Cassette bundle is empty";

        } else {
            return "Cassette bundle: " +
                    bundle;
        }
    }
}
