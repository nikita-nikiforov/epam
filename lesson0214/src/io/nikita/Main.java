package io.nikita;

public class Main {

    public static void main(String[] args) {
        Task task1 = new Task("The First"); // OK -- Task The First is created!
        task1.reopen(); // NOT OK -- Hmm, can't reopen. The task is already opened.
        task1.assign(); // OK -- Assigning somebody to the task.
        task1.close(); // NOT OK -- Can't close, 'cause it's not resolved.
        task1.resolve(); // OK -- Resolving the task.
        task1.assign(); // NOT OK -- Can't assign. For assigning task should be opened.
                        // It's resolved now.
        task1.close(); // OK -- Closing the task.

        Task task2 = new Task("The Second"); // OK -- Task The Second is created!
        task2.assign(); // OK -- Assigning somebody to the task.
        task2.resolve(); // OK -- Resolving the task.
        task2.close(); // OK -- Closing the task.
    }
}
