package lexicon.data.dao;

import lexicon.model.Person;
import lexicon.model.TodoItemTask;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Component
public class TodoItemTaskDaoCollection implements TodoItemTaskDao {

    private Collection<TodoItemTask> todoItemTasks;
    private static TodoItemTaskDaoCollection instance;

    private TodoItemTaskDaoCollection() {
        this.todoItemTasks = new ArrayList<>();
    }

    public static TodoItemTaskDaoCollection getInstance() {
        if (instance == null) {
            instance = new TodoItemTaskDaoCollection();
        }
        return instance;
    }

    @Override
    public TodoItemTask persist(TodoItemTask todoItemTask) {
        this.todoItemTasks.add(todoItemTask);
        return todoItemTask;
    }

    @Override
    public TodoItemTask findById(int id) {
        for (TodoItemTask todoItemTaskById : findAll()) {
            if (todoItemTaskById.getId() == id) {
                return todoItemTaskById;
            }
        }
        return null;
    }

    @Override
    public Collection<TodoItemTask> findAll() {
        return todoItemTasks;
    }

    @Override
    public Collection<TodoItemTask> findByAssignedStatus(boolean status) {
        List<TodoItemTask> todoItemTasksByAssigned = new ArrayList<>();
        for (TodoItemTask todoItemTask : findAll()) {
            if (todoItemTask.isAssigned()) {
                todoItemTasksByAssigned.add(todoItemTask);
            }
        }
        return todoItemTasksByAssigned;
    }

    @Override
    public Collection<TodoItemTask> findByPersonId(Person personId) {
        List<TodoItemTask> todoItemTasksByPersonId = new ArrayList<>();
        for (TodoItemTask todoItemTask : findAll()) {
            if (todoItemTask.getAssignee().getId() == personId.getId()) {
                todoItemTasksByPersonId.add(todoItemTask);
            }
        }
        return todoItemTasksByPersonId;
    }

    @Override
    public void remove(int id) {
        todoItemTasks.removeIf(item -> item.getId() == id);
    }
}
