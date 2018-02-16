package io.nikita.states;

import io.nikita.Task;

public class ReopenedState implements ITaskState{
    private Task task;

    public ReopenedState(Task task) {
        this.task = task;
    }

    @Override
    public void resolve() {
        System.out.println("// Can't resolve. Firstly, assign somebody for the task.");
    }

    @Override
    public void reopen() {
        System.out.println("// Hmm, can't reopen myself from reopenedState!");
    }

    @Override
    public void close() {
        System.out.println("// Oops. Firstly, resolve the task.");
    }

    @Override
    public void assign() {
        System.out.println("Assigning somebody to the task.");
        task.changeState(new AssignedState(task));
    }
}
