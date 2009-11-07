package org.epseelon.todolist.dto;

import org.epseelon.todolist.enums.Priority;

/**
 * @author sarbogast
 * @version 21 mai 2009, 16:17:50
 */
public class TodoDetail {
    private long id;
    private String title;
    private Priority priority;
    private String description;

    public TodoDetail() {
    }

    public TodoDetail(long id, String title, Priority priority, String description) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.description = description;
    }

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

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
