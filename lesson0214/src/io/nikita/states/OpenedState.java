package io.nikita.states;

import io.nikita.Task;

public class OpenedState implements ITaskState{
    private Task task;

    public OpenedState(Task task) {
        this.task = task;
    }

    @Override
    public void resolve() {
        System.out.println("// Can't resolve. Firstly, assign somebody to the task.");
    }

    @Override
    public void reopen() {
        System.out.println("// Hmm, can't reopen. The task is already opened.");
    }

    @Override
    public void close() {
        System.out.println("// Firstly resolve!");
    }

    @Override
    public void assign() {
        System.out.println("Assigning somebody to the task.");
        task.changeState(new AssignedState(task));
    }
}
