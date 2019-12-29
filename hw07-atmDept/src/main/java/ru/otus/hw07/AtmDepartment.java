package ru.otus.hw07;

import ru.otus.hw07.atm.Atm;
import ru.otus.hw07.command.AddAtmToList;
import ru.otus.hw07.command.Command;
import ru.otus.hw07.command.SaveAtm;

import java.util.ArrayList;
import java.util.List;

public class AtmDepartment {
    private List<Atm> atmList = new ArrayList<>();

    public void printAmount() {

        int deptSum = atmList.stream()
                .mapToInt(Atm::getAmount)
                .sum();
        System.out.println("ATM department {Amount=" +deptSum+"}");
    }

    public AtmDepartment addAtm(Atm atm) {
        Command firstSave = new SaveAtm(atm);
        Command whenAdd = new AddAtmToList(atmList,atm);
        firstSave.setNext(whenAdd);
        firstSave.execute();
        return this;
    }

    public void restore() {
        atmList.forEach(atm -> atm.restore());
    }
}
