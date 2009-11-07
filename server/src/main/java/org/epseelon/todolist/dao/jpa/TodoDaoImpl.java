package org.epseelon.todolist.dao.jpa;

import org.epseelon.todolist.dao.TodoDao;
import org.epseelon.todolist.domain.Todo;
import org.epseelon.utils.dao.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * @author sarbogast
 * @version 21 mai 2009, 16:23:48
 */
@Repository
public class TodoDaoImpl extends BaseDaoImpl<Todo, Long> implements TodoDao {
}
