package org.epseelon.todolist.exceptions;

/**
 * @author sarbogast
 * @version 21 mai 2009, 16:47:08
 */
public class TodoNotFoundException extends Throwable {
    private long todoId;

    public TodoNotFoundException(long id) {
        super();
        this.todoId = id;
    }

    public long getTodoId() {
        return todoId;
    }
}
