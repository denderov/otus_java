package ru.otus.hw06;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class AtmTest {

    @Test
    void emptyAtm() {
        Atm atm = new Atm();
        assertThat(atm.toString(), equalTo("ATM{Cassette=Cash bundle is empty}"));
    }

    @Test
    void nonEmptyAtm() {
        Atm atm = new Atm()
                .replenishment(100, 100)
                .replenishment(1000, 100)
                .replenishment(100, 100)
                .replenishment(500, 100);
        assertThat(atm.toString(),equalTo("ATM{Cassette=Cash bundle: {100=200, 500=100, 1000=100}}"));
    }

    @Test
    void atmWithdrawTest() {
        Atm atm = new Atm()
                .replenishment(100, 100)
                .replenishment(1000, 100)
                .replenishment(100, 100)
                .replenishment(500, 100);
        CashBundle cashBundleReceived = atm.withdraw(100500);
        assertThat(atm.toString(),equalTo("ATM{Cassette=Cash bundle: {100=200, 500=99, 1000=0}}"));
        assertThat(cashBundleReceived.toString(),equalTo("Cash bundle: {100=0, 500=1, 1000=100}"));
    }
}
