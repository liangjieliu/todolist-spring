package org.epseelon.todolist.dto;

import java.util.Collection;
import java.util.ArrayList;

/**
 * @author sarbogast
 * @version 21 mai 2009, 16:17:28
 */
public class TodoListItem {
    private long id;
    private String name;

    public TodoListItem() {
    }

    public TodoListItem(long id, String name) {
        this.id = id;
        this.name = name;
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
}
