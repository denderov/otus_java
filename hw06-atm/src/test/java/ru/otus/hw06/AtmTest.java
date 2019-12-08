package ru.otus.hw06;

import org.junit.jupiter.api.*;
import ru.otus.hw06.atm.Atm;
import ru.otus.hw06.atm.ConcreteAtm;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

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
    void emptyAtm() {
        Atm atm = new ConcreteAtm();
        atm.getCashBalance();
        assertThat(output.toString(), containsString("ATM: {Cassette=Cash bundle is empty}"));
    }

    @Test
    void nonEmptyAtm() {
        Atm atm = new ConcreteAtm()
                .replenishment(Banknote.ONE_HUNDRED, 100)
                .replenishment(Banknote.ONE_THOUSAND, 100)
                .replenishment(Banknote.ONE_HUNDRED, 100)
                .replenishment(Banknote.FIVE_HUNDRED, 100);
        atm.getCashBalance();
        assertThat(output.toString(), containsString("ATM: {Cassette=Cash bundle: {100=200, 500=100, 1000=100}}"));
    }

    @Test
    void atmWithdrawTest() {
        Atm atm = new ConcreteAtm()
                .replenishment(Banknote.ONE_HUNDRED, 100)
                .replenishment(Banknote.ONE_THOUSAND, 100)
                .replenishment(Banknote.ONE_HUNDRED, 100)
                .replenishment(Banknote.FIVE_HUNDRED, 100);
        atm.withdraw(100500);
        assertThat(output.toString(), containsString("Withdraw cash: {Cash bundle: {500=1, 1000=100}}"));
        atm.getCashBalance();
        assertThat(output.toString(), containsString("ATM: {Cassette=Cash bundle: {100=200, 500=99}}"));
    }

    @Test
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
        atm.getAmountBalance();
        assertThat(output.toString(), containsString("ATM: {Amount=100500}"));
    }
}
