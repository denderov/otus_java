package ru.otus.hw06;

public interface CashBundle {
    ConcreteCashBundle get(int sum);

    ConcreteCashBundle put(Banknote faceValue, int noteCount);

    int getAmount();
}
