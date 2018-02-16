package io.nikita.states;

import io.nikita.Task;

public class ClosedState implements ITaskState{
    private Task task;

    public ClosedState(Task task) {
        this.task = task;
    }

    @Override
    public void resolve() {
        System.out.println("There's nothing to resolve. Task is closed.");
    }

    @Override
    public void reopen() {
        System.out.println("Extra danger bug! Reopen immediatly.");
        task.changeState(new ReopenedState(task));
    }

    @Override
    public void close() {
        System.out.println("// Close already closed? Something wrong.");
    }

    @Override
    public void assign() {
        System.out.println("// Can't assign. For assigning task should be opened. " +
                "It's closed now.");
    }
}
