package ru.otus.hw06.cash_bundle;

import ru.otus.hw06.Banknote;

public interface CashBundle {
    CashBundle get(int sum);

    CashBundle put(Banknote faceValue, int noteCount);

    int getAmount();
}
