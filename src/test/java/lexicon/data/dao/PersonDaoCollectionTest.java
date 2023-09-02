package lexicon.data.dao;

import lexicon.data.sequencers.PersonIdSequencer;
import lexicon.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PersonDaoCollectionTest {

    private Person testObject;

    @Autowired
    private PersonDao personDao;

    @BeforeEach
    public void setup() {
        testObject = new Person("Mikael", "Engvall", "mikael@engvall.org");
    }

    @Test
    public void testSingleton() {
        PersonIdSequencer instance1 = PersonIdSequencer.getInstance();
        PersonIdSequencer instance2 = PersonIdSequencer.getInstance();
        assertEquals(instance1, instance2);
    }

    @Test
    public void testPersist() {
        personDao.persist(testObject);
        personDao.findAll();
    }

    @Test
    public void testFindById() {
        personDao.persist(testObject);
        assertEquals(testObject.getId(), personDao.findById(1).getId());
    }

    @Test
    public void testFindByEmail() {
        personDao.persist(testObject);
        assertEquals(testObject.getEmail(), personDao.findByEmail("Mikael@engvall.org").getEmail());
    }

    @Test
    public void testFindAll() {
        personDao.persist(testObject);
        List<Person> personAll = new ArrayList<>(personDao.findAll());

        assertEquals(testObject.getId(), personAll.get(0).getId());
    }

    @Test
    public void testRemove() {
        personDao.persist(testObject);
        personDao.remove(testObject.getId());
        assertNull(personDao.findById(testObject.getId()));
    }

    @AfterEach
    void tearDown() {
        PersonIdSequencer.getInstance().setCurrentId(0);
    }
}