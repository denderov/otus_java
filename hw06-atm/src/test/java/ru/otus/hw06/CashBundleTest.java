package ru.otus.hw06;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class CashBundleTest {

    @Test
    void emptyCashBundleCheck() {
        CashBundle cashBundle = new CashBundle();
        assertThat(cashBundle.toString(), equalTo("Cash bundle is empty"));
    }

    @Test
    void nonEmptyCashBundleCheck() {
        CashBundle cashBundle = new CashBundle()
                .put(200, 100)
                .put(100, 1000)
                .put(200, 100);
        assertThat(cashBundle.toString(), equalTo("Cash bundle: {100=1000, 200=200}"));
    }

    @Test
    void getCashBundleCheck() {
        CashBundle cashBundle = new CashBundle()
                .put(200, 100)
                .put(100, 1000)
                .put(200, 100);
        CashBundle cashBundleReceived = cashBundle.get(500);
        assertThat(cashBundle.toString(), equalTo("Cash bundle: {100=999, 200=198}"));
        assertThat(cashBundleReceived.toString(), equalTo("Cash bundle: {100=1, 200=2}"));
    }

}
