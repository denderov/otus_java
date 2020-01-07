package ru.otus.hw07.command;

import ru.otus.hw07.atm.Atm;

public class SaveAtm extends Command {

    private Atm atm;

    public SaveAtm(Atm atm) {
        this.atm = atm;
    }

    @Override
    public boolean execute() {
        atm.save();
        return checkNext();
    }
}
