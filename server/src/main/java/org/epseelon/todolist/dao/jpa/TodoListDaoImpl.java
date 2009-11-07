package org.epseelon.todolist.dao.jpa;

import org.epseelon.todolist.dao.TodoListDao;
import org.epseelon.todolist.domain.TodoList;
import org.epseelon.utils.dao.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * @author sarbogast
 * @version 21 mai 2009, 16:22:51
 */
@Repository
public class TodoListDaoImpl extends BaseDaoImpl<TodoList,Long> implements TodoListDao {
}
