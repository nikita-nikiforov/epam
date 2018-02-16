package io.nikita.states;

import io.nikita.Task;

public class AssignedState implements ITaskState{
    private Task task;

    public AssignedState(Task task){
        this.task = task;
    }

    @Override
    public void resolve() {
        System.out.println("Resolving the task.");
        task.changeState(new ResolvedState(task));
    }

    @Override
    public void reopen() {
        System.out.println("// Oops. Task isn't resolved, so there is nothing to reopen!");
    }

    @Override
    public void close() {
        System.out.println("// Can't close, 'cause it's not resolved.");
    }

    @Override
    public void assign() {
        System.out.println("// Assign the assigned? He-he.");
    }
}
