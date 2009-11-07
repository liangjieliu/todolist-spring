package org.epseelon.todolist.dto;

import java.util.Collection;
import java.util.ArrayList;

/**
 * @author sarbogast
 * @version 21 mai 2009, 16:17:35
 */
public class TodoListDetail {
    private long id;
    private String name;
    private Collection<TodoDetail> todos = new ArrayList<TodoDetail>();

    public TodoListDetail() {
    }

    public TodoListDetail(long id, String name, Collection<TodoDetail> todos) {
        this.id = id;
        this.name = name;
        this.todos = todos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<TodoDetail> getTodos() {
        return todos;
    }

    public void setTodos(Collection<TodoDetail> todos) {
        this.todos = todos;
    }
}
