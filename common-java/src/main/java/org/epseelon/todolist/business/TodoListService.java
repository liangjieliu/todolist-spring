package org.epseelon.todolist.business;

import org.epseelon.todolist.dto.TodoListItem;
import org.epseelon.todolist.dto.TodoListDetail;
import org.epseelon.todolist.exceptions.TodoListNotFoundException;
import org.epseelon.todolist.exceptions.TodoNotFoundException;

import java.util.List;

/**
 * @author sarbogast
 * @version 21 mai 2009, 15:55:04
 */
public interface TodoListService {
    List<TodoListItem> getAllLists();
    TodoListDetail getList(long id) throws TodoListNotFoundException;
    void createList(TodoListDetail todoList);
    void updateList(TodoListDetail todoList) throws TodoListNotFoundException, TodoNotFoundException;
    void deleteList(long id) throws TodoListNotFoundException;
}
