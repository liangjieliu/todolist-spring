package org.epseelon.todolist.domain;

import org.epseelon.todolist.enums.Priority;

import javax.persistence.*;

/**
 * @author sarbogast
 * @version 21 mai 2009, 16:06:48
 */
@Entity
public class Todo {
    @Id @GeneratedValue
    private long id;
    private String title;
    private String description;
    private Priority priority;
    @ManyToOne
    private TodoList parentList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public TodoList getParentList() {
        return parentList;
    }

    public void setParentList(TodoList parentList) {
        this.parentList = parentList;
    }
}
