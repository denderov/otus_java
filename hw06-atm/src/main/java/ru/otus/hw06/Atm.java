package ru.otus.hw06;

public interface Atm {

    Atm replenishment(Banknote faceValue, int noteCount);

    CashBundle withdraw(int amount);



}
