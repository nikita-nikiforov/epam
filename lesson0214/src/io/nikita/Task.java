package io.nikita;

import io.nikita.states.ITaskState;
import io.nikita.states.OpenedState;

public class Task {
    private String name;
    private ITaskState taskState;

    public Task(String name) {
        this.name = name;
        this.taskState = new OpenedState(this);
        System.out.println("Task " + name + " is created!");
    }

    public Task(){}

    public void resolve(){
        taskState.resolve();
    }

    public void reopen(){
        taskState.reopen();
    }

    public void close(){
        taskState.close();
    }

    public void assign(){
        taskState.assign();
    }

    public void changeState(ITaskState taskState){
        this.taskState = taskState;
    }


}
