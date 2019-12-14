package ru.otus.hw06.atm;

import ru.otus.hw06.Banknote;

public interface Atm {

    Atm replenishment(Banknote faceValue, int noteCount);

    void withdraw(int amount);

    void getAmountBalance();

    void getCashBalance();

}
