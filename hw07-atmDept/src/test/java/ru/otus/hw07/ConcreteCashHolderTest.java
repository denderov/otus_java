package ru.otus.hw07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw07.moneybag.CashHolder;
import ru.otus.hw07.moneybag.ConcreteCashHolder;

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

    @Test
    @DisplayName("Test correct withdraw cash holder")
    void shouldCorrectGetBanknotes() {
        CashHolder withdrawCash =
        cashHolder.putBanknotes(Banknote.ONE_HUNDRED, 5)
                .putBanknotes(Banknote.FIVE_HUNDRED, 100)
                .putBanknotes(Banknote.ONE_THOUSAND, 50)
                .get(42000);
        assertThat(cashHolder.getAmount()).isEqualTo(58500);
        assertThat(withdrawCash.getAmount()).isEqualTo(42000);
    }

    @Test
    @DisplayName("Test correct undo cash holder")
    void shouldCorrectUndoWhileException() {
        try {
            cashHolder.save(MemoStatus.CURRENT);
            CashHolder withdrawCash =
                    cashHolder.putBanknotes(Banknote.ONE_HUNDRED, 5)
                            .putBanknotes(Banknote.FIVE_HUNDRED, 100)
                            .putBanknotes(Banknote.ONE_THOUSAND, 50)
                            .get(777000);
        } catch (RuntimeException e) {
            cashHolder.restore(MemoStatus.CURRENT);
        }

        assertThat(cashHolder.getAmount()).isEqualTo(100500);
    }
}