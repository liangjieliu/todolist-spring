package org.epseelon.todolist.business.impl;

import org.epseelon.todolist.business.TodoListService;
import org.epseelon.todolist.dto.TodoListItem;
import org.epseelon.todolist.dto.TodoListDetail;
import org.epseelon.todolist.dto.TodoDetail;
import org.epseelon.todolist.exceptions.TodoListNotFoundException;
import org.epseelon.todolist.exceptions.TodoNotFoundException;
import org.epseelon.todolist.domain.TodoList;
import org.epseelon.todolist.domain.Todo;
import org.epseelon.todolist.dao.TodoListDao;
import org.epseelon.todolist.dao.TodoDao;
import org.springframework.security.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;

/**
 * @author sarbogast
 * @version 21 mai 2009, 16:27:38
 */
@Transactional @Service("todoListService")
@RemotingDestination
@Secured("ROLE_USER")
public class TodoListServiceImpl implements TodoListService {
    @Autowired
    private TodoListDao todoListDao;
    @Autowired
    private TodoDao todoDao;

    public List<TodoListItem> getAllLists() {
        Collection<TodoList> lists = todoListDao.findAll();
        List<TodoListItem> items = new ArrayList<TodoListItem>(lists.size());
        for (TodoList list : lists) {
            items.add(new TodoListItem(list.getId(), list.getName()));
        }
        return items;
    }

    public TodoListDetail getList(long id) throws TodoListNotFoundException {
        try{
            TodoList list = todoListDao.getById(id);
            List<TodoDetail> todos = new ArrayList<TodoDetail>(list.getTodos().size());
            for (Todo todo : list.getTodos()) {
                todos.add(new TodoDetail(todo.getId(), todo.getTitle(), todo.getPriority(), todo.getDescription()));
            }
            return new TodoListDetail(list.getId(), list.getName(), todos);
        } catch(EntityNotFoundException e){
            throw new TodoListNotFoundException(id);
        }
    }

    public void createList(TodoListDetail todoList) {
        TodoList list = new TodoList();
        list.setName(todoList.getName());
        todoListDao.persist(list);
        for (TodoDetail todoItem : todoList.getTodos()) {
            Todo todo = new Todo();
            todo.setTitle(todoItem.getTitle());
            todo.setDescription(todoItem.getDescription());
            todo.setPriority(todoItem.getPriority());
            todoDao.persist(todo);
            list.addToTodos(todo);
        }
    }

    public void updateList(TodoListDetail todoList) throws TodoListNotFoundException, TodoNotFoundException {
        try {
            TodoList list = todoListDao.getById(todoList.getId());
            list.setName(todoList.getName());
            todoListDao.merge(list);
            for (TodoDetail todoDetail : todoList.getTodos()) {
                if(todoDetail.getId() < 0){
                    Todo todo = new Todo();
                    todo.setTitle(todoDetail.getTitle());
                    todo.setDescription(todoDetail.getDescription());
                    todo.setPriority(todoDetail.getPriority());
                    todoDao.persist(todo);
                    list.addToTodos(todo);
                } else {
                    try {
                        Todo todo = todoDao.getById(todoDetail.getId());
                        todo.setTitle(todoDetail.getTitle());
                        todo.setDescription(todoDetail.getDescription());
                        todo.setPriority(todoDetail.getPriority());
                        todoDao.merge(todo);
                    } catch (EntityNotFoundException e) {
                        throw new TodoNotFoundException(todoDetail.getId());
                    }
                }
            }

            List<Todo> toRemove = new ArrayList<Todo>();
            for (Todo todo : list.getTodos()) {
                boolean found = false;
                for (TodoDetail todoDetail : todoList.getTodos()) {
                    if(todoDetail.getId() < 0 || todo.getId() == todoDetail.getId()){
                        found = true;
                        break;
                    }
                }
                if(!found){
                    toRemove.add(todo);
                }
            }
            for (Todo todo : toRemove) {
                list.removeFromTodos(todo);
                todoDao.remove(todo);
            }
        } catch (EntityNotFoundException e) {
            throw new TodoListNotFoundException(todoList.getId());
        } 
    }

    public void deleteList(long id) throws TodoListNotFoundException {
        try {
            TodoList list = todoListDao.getById(id);
            todoListDao.remove(list);
        } catch (EntityNotFoundException e) {
            throw new TodoListNotFoundException(id);
        }
    }
}
