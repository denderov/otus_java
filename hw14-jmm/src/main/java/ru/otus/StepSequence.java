package ru.otus;

public class StepSequence {
    private boolean isCurrentStepRight = true;
    private boolean isForwardDirection = true;
    private int counter = 0;

    public static void main(String[] args) {
        StepSequence walk = new StepSequence();
        walk.go();
    }

    private synchronized void step(boolean isCurrentHandRight) {
        for (int i = 0; i < 100; i++) {

            waitIfNotOurTurn(isCurrentHandRight);

            changeCounter();

            System.out.println(String.format("Current step is %s. Counter: %d"
                    , isCurrentStepRight ? "right" : "left"
                    , counter));

            isCurrentStepRight = !isCurrentStepRight;

            notify();
        }
    }

    private void changeCounter() {
        if (isCurrentStepRight) {
            if (isForwardDirection) {
                counter++;
            } else {
                counter--;
            }
        }

        if (counter > 9) {
            isForwardDirection = false;
        } else if (counter < 2) {
            isForwardDirection = true;
        }
    }

    private void waitIfNotOurTurn(boolean isCurrentHandRight) {
        if (isCurrentHandRight != isCurrentStepRight) {
            try{
                wait();
            } catch(InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }
    }

    public void go() {
        Thread thread1 = new Thread(()->step(true));
        Thread thread2 = new Thread(()->step(false));

        thread1.start();
        thread2.start();
    }
}

