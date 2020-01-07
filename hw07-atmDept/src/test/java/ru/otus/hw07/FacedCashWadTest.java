package ru.otus.hw07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw07.moneybag.CashWad;
import ru.otus.hw07.moneybag.FacedCashWad;

import static org.assertj.core.api.Assertions.assertThat;

class FacedCashWadTest {

    private CashWad facedCashWad;

    @BeforeEach
    void setUp() {
        facedCashWad = new FacedCashWad(Banknote.ONE_HUNDRED, 50);
    }

    @Test
    @DisplayName("Test correct withdraw")
    void shouldCorrectGetBanknotes() {
        CashWad outCashWad = facedCashWad.getBanknotes(10);
        assertThat(outCashWad.getAmount()).isEqualTo(1000);
        assertThat(facedCashWad.getAmount()).isEqualTo(4000);
    }

    @Test
    @DisplayName("Test correct undo")
    void shouldCorrectUndoWhileException() {
        try {
            facedCashWad.save(MemoStatus.CURRENT);
            facedCashWad.getBanknotes(10);
            facedCashWad.getBanknotes(20);
            facedCashWad.getBanknotes(40);
        } catch (RuntimeException e) {
            facedCashWad.restore(MemoStatus.CURRENT);
        }

        assertThat(facedCashWad.getAmount()).isEqualTo(5000);
    }
}