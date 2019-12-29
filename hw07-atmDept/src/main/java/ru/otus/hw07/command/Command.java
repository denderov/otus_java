package ru.otus.hw07.command;

public abstract class Command {

    private Command next;

    public void setNext(Command next) {
        this.next = next;
    }

    public abstract boolean execute();

    boolean checkNext() {
        if (next == null) {
            return true;
        }
        return next.execute();
    }
}
