package io.nikita.states;

import io.nikita.Task;

public class ResolvedState implements ITaskState{
    private Task task;

    public ResolvedState(Task task) {
        this.task = task;
    }

    @Override
    public void resolve() {
        System.out.println("// Hmm. Can't resolve myself from resolveState.");
    }

    @Override
    public void reopen() {
        System.out.println("Bad work. Reopen the task.");
        task.changeState(new OpenedState(task));
    }

    @Override
    public void close() {
        System.out.println("Closing the task.");
        task.changeState(new ClosedState(task));
    }

    @Override
    public void assign() {
        System.out.println("// Can't assign. For assigning task should be opened. " +
                "It's resolved now.");
    }
}
