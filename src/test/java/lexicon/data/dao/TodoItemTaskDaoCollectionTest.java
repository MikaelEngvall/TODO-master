package lexicon.data.dao;

import lexicon.model.Person;
import lexicon.model.TodoItem;
import lexicon.model.TodoItemTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class TodoItemTaskDaoCollectionTest{

    private Person testPerson;
    @Autowired
    private PersonDao personDao;

    private TodoItem testItem;
    @Autowired
    private TodoItemDao todoItemDao;

    private TodoItemTask testObject;
    @Autowired
    private TodoItemTaskDao todoItemTaskDao;


    @BeforeEach
    public void setup() {
        testPerson = new Person("Mikael", "Engvall", "mikael@engvall.org");
        testItem = new TodoItem("Change tires", "task description", LocalDate.of(2023, 9, 15), false, testPerson);
        testObject = new TodoItemTask(true, testItem, testPerson);
    }

    @Test
    public void testPersist() {
        assertEquals(testObject.toString(), todoItemTaskDao.persist(testObject).toString());
    }

    @Test
    public void testFindById() {
        todoItemTaskDao.persist(testObject);
        assertEquals(testObject.getId(), todoItemTaskDao.findById(1).getId());
    }

    @Test
    public void testFindAll() {
        todoItemTaskDao.persist(testObject);
        assertEquals(1, todoItemTaskDao.findAll().size());
    }

    @Test
    public void testFindByAssignedStatus() {
        boolean isAssigned = true;
        todoItemTaskDao.persist(testObject);
        assertEquals(isAssigned, todoItemTaskDao.findById(1).isAssigned());
    }

    @Test
    public void testFindByPersonId() {
        todoItemTaskDao.persist(testObject);
        System.out.println(testObject.toString());
        assertEquals(testObject.getId(), todoItemTaskDao.findById(1).getId());
    }

    @Test
    public void testRemove() {
        todoItemTaskDao.persist(testObject);
        todoItemTaskDao.remove(1);
        List<TodoItemTask> todoItemTaskAll = new ArrayList<>(todoItemTaskDao.findAll());
        assertTrue(todoItemTaskAll.isEmpty());
    }
}