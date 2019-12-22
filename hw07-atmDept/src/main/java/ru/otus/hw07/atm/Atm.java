package ru.otus.hw07.atm;

import ru.otus.hw07.Banknote;

public interface Atm {

    Atm replenishment(Banknote faceValue, int noteCount);

    void withdraw(int amount);

    void getAmountBalance();

    void getCashBalance();

}
