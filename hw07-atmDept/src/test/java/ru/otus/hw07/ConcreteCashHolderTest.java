package ru.otus.hw07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConcreteCashHolderTest {

    private CashHolder cashHolder = new ConcreteCashHolder();

    @Test
    @DisplayName("Test correct filling cash holder")
    void shouldCorrectPutBanknotes() {
        cashHolder.putBanknotes(Banknote.ONE_HUNDRED, 5);
        cashHolder.putBanknotes(Banknote.FIVE_HUNDRED, 100);
        cashHolder.putBanknotes(Banknote.ONE_THOUSAND, 50);
        assertThat(cashHolder.getAmount()).isEqualTo(100500);
    }
}