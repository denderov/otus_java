package ru.otus.hw07.moneybag;

import ru.otus.hw07.MemoStatus;

public interface CashWad {
    void save(MemoStatus status);

    void restore(MemoStatus status);

    CashWad getBanknotes(int noteCount);

    CashWad putBanknotes(int noteCount);

    int getFaceValue();

    int getCount();

    int getAmount();

    CashWad clone();
}
