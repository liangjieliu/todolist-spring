package org.epseelon.todolist.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;

/**
 * @author sarbogast
 * @version 21 mai 2009, 16:04:48
 */
@Entity
public class TodoList {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @OneToMany(mappedBy = "parentList", cascade = CascadeType.REMOVE)
    private Collection<Todo> todos = new ArrayList<Todo>();

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

    public Collection<Todo> getTodos() {
        return Collections.unmodifiableCollection(todos);
    }

    public void addToTodos(Todo todo){
        if(todos.add(todo)){
            todo.setParentList(this);
        }
    }

    public void removeFromTodos(Todo todo){
        if(todos.remove(todo)){
            todo.setParentList(null);
        }
    }
}
