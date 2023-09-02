package lexicon.data.dao;

import lexicon.model.Person;
import lexicon.model.TodoItemTask;

import java.util.Collection;

public interface TodoItemTaskDao extends BaseDao<TodoItemTask>{
    //TodoItemTask persist(TodoItemTask todoItemTask);
    TodoItemTask findById(int id);
    Collection<TodoItemTask> findAll();
    Collection<TodoItemTask> findByAssignedStatus(boolean status);
    Collection<TodoItemTask> findByPersonId(Person personId);
    void remove(int id);

}
