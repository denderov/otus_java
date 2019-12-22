package ru.otus.hw07.atm;

import ru.otus.hw07.Banknote;
import ru.otus.hw07.MemoStatus;

public interface Atm {

    Atm replenishment(Banknote faceValue, int noteCount);

    void withdraw(int amount);

    int getAmount();

    void getCashBalance();

    void save(MemoStatus status);

    void restore(MemoStatus status);

    Atm clone();
}
