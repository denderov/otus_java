package ru.otus.hw06.atm.cassette_bundle;

import ru.otus.hw06.Banknote;
import ru.otus.hw06.cash_bundle.CashBundle;

public interface CassetteBundle {
    CashBundle get(int sum);

    CassetteBundle put(Banknote banknote, int noteCount);

    int getAmount();
}
