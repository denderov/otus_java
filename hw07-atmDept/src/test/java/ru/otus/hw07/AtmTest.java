package ru.otus.hw07;

import org.junit.jupiter.api.*;
import ru.otus.hw07.atm.Atm;
import ru.otus.hw07.atm.ConcreteAtm;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AtmTest {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeAll
    void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @BeforeEach
    void flushOutput() {
        output.reset();
    }

    @AfterAll
    void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    @DisplayName("Test correct empty ATM status")
    void emptyAtm() {
        Atm atm = new ConcreteAtm();
        atm.getCashBalance();
        assertThat(output.toString()).contains("ATM: {Cassette=Cash holder is empty}");
    }

    @Test
    @DisplayName("Test correct non empty ATM status")
    void nonEmptyAtm() {
        Atm atm = new ConcreteAtm()
                .replenishment(Banknote.ONE_HUNDRED, 100)
                .replenishment(Banknote.ONE_THOUSAND, 100)
                .replenishment(Banknote.ONE_HUNDRED, 100)
                .replenishment(Banknote.FIVE_HUNDRED, 100);
        atm.getCashBalance();
        assertThat(output.toString()).contains("ATM: {Cassette=Cash holder: {100=200, 500=100, 1000=100}}");
    }

    @Test
    @DisplayName("Test correct withdraw ATM status")
    void atmWithdrawTest() {
        Atm atm = new ConcreteAtm()
                .replenishment(Banknote.ONE_HUNDRED, 100)
                .replenishment(Banknote.ONE_THOUSAND, 100)
                .replenishment(Banknote.ONE_HUNDRED, 100)
                .replenishment(Banknote.FIVE_HUNDRED, 100);
        atm.withdraw(100500);
        assertThat(output.toString()).contains("Withdraw cash: {Cash holder: {500=1, 1000=100}}");
        atm.getCashBalance();
        assertThat(output.toString()).contains("ATM: {Cassette=Cash holder: {100=200, 500=99, 1000=0}}");
    }

    @Test
    @DisplayName("Test correct ATM amount status")
    void atmAmountCashTest() {
        Atm atm = new ConcreteAtm()
                .replenishment(Banknote.FIVE, 20)
                .replenishment(Banknote.TEN, 30)
                .replenishment(Banknote.FIFTY, 14)
                .replenishment(Banknote.ONE_HUNDRED, 42)
                .replenishment(Banknote.TWO_HUNDRED, 21)
                .replenishment(Banknote.FIVE_HUNDRED, 32)
                .replenishment(Banknote.ONE_THOUSAND, 17)
                .replenishment(Banknote.TWO_THOUSAND, 9)
                .replenishment(Banknote.FIVE_THOUSAND, 8);
        assertThat(atm.getAmount()).isEqualTo(100500);
    }
}
