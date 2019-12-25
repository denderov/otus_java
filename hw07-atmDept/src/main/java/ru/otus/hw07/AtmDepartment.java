package ru.otus.hw07;

import ru.otus.hw07.atm.Atm;

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
        atm.save();
        atmList.add(atm);
        return this;
    }

    public void restore() {
        atmList.forEach(atm -> atm.restore());
    }
}
