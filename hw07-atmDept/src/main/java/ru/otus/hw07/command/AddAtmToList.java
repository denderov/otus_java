package ru.otus.hw07.command;

import ru.otus.hw07.atm.Atm;

import java.util.List;

public class AddAtmToList extends Command {

    private List<Atm> atmList;
    private Atm atm;

    public AddAtmToList(List<Atm> atmList, Atm atm) {
        this.atmList = atmList;
        this.atm = atm;
    }


    @Override
    public boolean execute() {
        atmList.add(atm);
        return checkNext();
    }
}
