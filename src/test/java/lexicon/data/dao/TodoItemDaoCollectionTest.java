package lexicon.data.dao;

import lexicon.model.Person;
import lexicon.model.TodoItem;
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
class TodoItemDaoCollectionTest {

    private TodoItem testObject;
    private TodoItem testObject2;

    @Autowired
    private TodoItemDao todoItemDao;

    @BeforeEach
    public void setup() {
        testObject = new TodoItem("Change tires", "task description", LocalDate.of(2023, 9, 15), false, new Person("Mikael", "Engvall", "mikael@engvall.org"));
    }

    @Test
    public void testFindAllByDone() {
        boolean done = true;
        assertEquals(0, todoItemDao.findAllByDone(done).size());
    }

    @Test
    public void testFindByTitleContains() {
        testObject2 = new TodoItem("Buy tires", "task description", LocalDate.of(2023, 9, 15), false, new Person("Mikael", "Engvall", "mikael@engvall.org"));
        todoItemDao.persist(testObject);
        todoItemDao.persist(testObject2);

        String titleContains = "tires";

        assertEquals(2, todoItemDao.findByTitleContains(titleContains).size());
    }

    @Test
    public void testFindByPersonId() {
        todoItemDao.persist(testObject);
        List<TodoItem> personItems = new ArrayList<>(todoItemDao.findByPersonId(testObject.getId()));
        assertEquals(1, personItems.get(0).getId());
    }

    @Test
    public void testFindByDeadlineBefore() {
        LocalDate date = LocalDate.of(2023, 9, 20);
        todoItemDao.persist(testObject);
        List<TodoItem> itemsBeforeDeadline = new ArrayList<>(todoItemDao.findByDeadlineBefore(date));
        assertEquals(1, itemsBeforeDeadline.size());

    }

    @Test
    public void testFindByDeadlineAfter() {
        LocalDate date = LocalDate.of(2023, 9, 20);
        todoItemDao.persist(testObject);
        List<TodoItem> itemsAfterDeadline = new ArrayList<>(todoItemDao.findByDeadlineAfter(date));
        assertEquals(0, itemsAfterDeadline.size());
    }
}