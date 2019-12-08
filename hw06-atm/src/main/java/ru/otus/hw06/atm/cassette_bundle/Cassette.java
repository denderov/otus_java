package ru.otus.hw06.atm.cassette_bundle;

import ru.otus.hw06.cash_bundle.CashBundle;

public interface Cassette {
    CashBundle getCash(int count);

    Cassette put(int count);

    int getFaceValue();

    int getCount();

    int getAmount();
}
