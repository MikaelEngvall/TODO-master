package lexicon.data.dao;

import lexicon.model.TodoItem;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class TodoItemDaoCollection implements TodoItemDao{

    private Collection<TodoItem> todoItems;
    private static TodoItemDaoCollection instance;

    private TodoItemDaoCollection() {
        this.todoItems = new ArrayList<>();
    }

    public static TodoItemDaoCollection getInstance() {
        if (instance == null) {
            instance = new TodoItemDaoCollection();
        }
        return instance;
    }

    @Override
    public TodoItem persist(TodoItem todoItem) {
        this.todoItems.add(todoItem);
        return todoItem;
    }

    @Override
    public TodoItem findById(int id) {
        List<TodoItem> itemsAll = new ArrayList<>(findAll());
        for (TodoItem item : itemsAll) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    @Override
    public Collection<TodoItem> findAll() {
        return todoItems;
    }

    @Override
    public Collection<TodoItem> findAllByDone(boolean done) {
        List<TodoItem> todoItemsNotDone = new ArrayList<>();
        List<TodoItem> todoItemsDone = new ArrayList<>();
        for (TodoItem item : findAll()) {
            if (item.isDone() == done) {
                todoItemsDone.add(item);
            } else {
                todoItemsNotDone.add(item);
            }
        }
        if (done) {
            return todoItemsDone;
        } else {
            return todoItemsNotDone;
        }
    }

    @Override
    public Collection<TodoItem> findByTitleContains(String title) {
        List<TodoItem> listOfItems = new ArrayList<>();
        for (TodoItem item : findAll()) {
            if (item.getTitle().toLowerCase().contains(title)) {
                listOfItems.add(item);
            }
        }
        return listOfItems;
    }

    @Override
    public Collection<TodoItem> findByPersonId(int personId) {
        List<TodoItem> personItems = new ArrayList<>();
        for (TodoItem item : findAll()) {
            if (item.getId() == personId) {
                personItems.add(item);
            }
        }
        return personItems;
    }

    @Override
    public Collection<TodoItem> findByDeadlineBefore(LocalDate date) {
        List<TodoItem> itemsBeforeDeadline = new ArrayList<>();
        for (TodoItem item : findAll()) {
            if (item.getDeadLine().isBefore(date)) {
                itemsBeforeDeadline.add(item);
            }
        }
        return itemsBeforeDeadline;
    }

    @Override
    public Collection<TodoItem> findByDeadlineAfter(LocalDate date) {
        List<TodoItem> itemsAfterDeadline = new ArrayList<>();
        for (TodoItem item : findAll()) {
            if (item.getDeadLine().isAfter(date)) {
                itemsAfterDeadline.add(item);
            }
        }
        return itemsAfterDeadline;
    }

    @Override
    public void remove(int id) {
        todoItems.removeIf(item -> item.getId() == id);
    }
}
