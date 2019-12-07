package ru.otus.hw06;

public interface CashBundle {
    CashBundle get(int sum);

    CashBundle put(Banknote faceValue, int noteCount);

    int getAmount();
}
