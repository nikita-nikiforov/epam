package io.nikita.states;

import io.nikita.Task;

public interface ITaskState {

    void resolve();
    void reopen();
    void close();
    void assign();
}
