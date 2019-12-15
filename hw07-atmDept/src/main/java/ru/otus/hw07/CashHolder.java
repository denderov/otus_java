package ru.otus.hw07;

public interface CashHolder {
    CashHolder get(int sum);

    void getBanknotes(Banknote faceValue, int noteCount);

    CashHolder putBanknotes(Banknote faceValue, int noteCount);

    int getAmount();
}
