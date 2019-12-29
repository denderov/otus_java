package ru.otus.hw07.moneybag;

import ru.otus.hw07.Banknote;
import ru.otus.hw07.MemoStatus;

import java.util.SortedMap;

public interface CashHolder {
    void save(MemoStatus status);

    void restore(MemoStatus status);

    CashHolder get(int sum);

    void getBanknotes(Banknote faceValue, int noteCount);

    CashHolder putBanknotes(Banknote faceValue, int noteCount);

    int getAmount();

    CashHolder clone();

    SortedMap<Banknote, CashWad> getMoneybox();
}
