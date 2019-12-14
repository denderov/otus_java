package ru.otus.hw06.atm.cassette_bundle;

import ru.otus.hw06.Banknote;
import ru.otus.hw06.cash_bundle.CashBundle;
import ru.otus.hw06.cash_bundle.ConcreteCashBundle;

public class ConcreteCassette implements Cassette {

    private final Banknote banknote;
    private int count;

    public ConcreteCassette(Banknote banknote, int count) {
        this.banknote = banknote;
        this.count = count;
    }

    @Override
    public CashBundle getCash(int count) {

        CashBundle outCashBundle = new ConcreteCashBundle();

        if (count <= this.count) {
            outCashBundle.put(banknote, count);
        } else {
            throw new RuntimeException("The count requested is greater than the count available!");
        }

        return outCashBundle;
    }

    @Override
    public Cassette put(int count) {

        if (count + this.count >= 0) {
            this.count += count;
        } else {
            throw new RuntimeException("The count of the deposit is less than the existing!");
        }

        return this;
    }

    @Override
    public int getFaceValue() {
        return this.banknote.getFaceValue();
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public int getAmount() {
        return this.count * this.banknote.getFaceValue();
    }

    @Override
    public String toString() {
        return Integer.toString(count);
    }
}
