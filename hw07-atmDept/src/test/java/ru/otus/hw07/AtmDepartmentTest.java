package ru.otus.hw07;

import org.junit.jupiter.api.*;
import ru.otus.hw07.atm.Atm;
import ru.otus.hw07.atm.ConcreteAtm;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AtmDepartmentTest {

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
    @DisplayName("Test correct empty ATM department sum")
    void emptyAtmDeptSum() {
        AtmDepartment atmDepartment = new AtmDepartment();
        atmDepartment.printAmount();
        assertThat(output.toString()).contains("ATM department {Amount=0}");
    }

    @Test
    @DisplayName("Test correct filling ATM department and sum")
    void fillingAtmDeptAndSum() {
        AtmDepartment atmDepartment = new AtmDepartment();
        Atm atm1 = new ConcreteAtm()
                .replenishment(Banknote.ONE_HUNDRED, 100)
                .replenishment(Banknote.ONE_THOUSAND, 100)
                .replenishment(Banknote.ONE_HUNDRED, 100)
                .replenishment(Banknote.FIVE_HUNDRED, 100);
        Atm atm2 = new ConcreteAtm()
                .replenishment(Banknote.FIVE, 1)
                .replenishment(Banknote.TEN, 1);
        atmDepartment
                .addAtm(atm1)
                .addAtm(atm2);
        atmDepartment.printAmount();
        assertThat(output.toString()).contains("ATM department {Amount=170015}");
    }

    @Test
    @DisplayName("Test correct ATM department restore")
    void atmDepartmentRestore() {
        AtmDepartment atmDepartment = new AtmDepartment();
        Atm atm1 = new ConcreteAtm()
                .replenishment(Banknote.ONE_HUNDRED, 100)
                .replenishment(Banknote.ONE_THOUSAND, 100)
                .replenishment(Banknote.ONE_HUNDRED, 100)
                .replenishment(Banknote.FIVE_HUNDRED, 100);
        Atm atm2 = new ConcreteAtm()
                .replenishment(Banknote.FIVE, 1)
                .replenishment(Banknote.TEN, 1);
        atmDepartment
                .addAtm(atm1)
                .addAtm(atm2);

        atm1.withdraw(150000);
        atm2.withdraw(15);

        atmDepartment.restore(MemoStatus.INIT);

        atmDepartment.printAmount();
        assertThat(output.toString()).contains("ATM department {Amount=170015}");
    }
}
