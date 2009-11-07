package org.epseelon.todolist.exceptions;

/**
 * @author sarbogast
 * @version 21 mai 2009, 16:25:50
 */
public class TodoListNotFoundException extends Exception {
    private long todoListId;

    public TodoListNotFoundException(long id) {
        super();
        this.todoListId = id;
    }

    public long getTodoListId() {
        return todoListId;
    }
}
