package lexicon.data.dao;

import lexicon.model.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class PersonDaoCollection implements PersonDao {

    private Collection<Person> persons;

    private static PersonDaoCollection instance;

    private PersonDaoCollection() {
        this.persons = new ArrayList<>();
    }

    public static PersonDaoCollection getInstance() {
        if (instance == null) {
            instance = new PersonDaoCollection();
        }
        return instance;
    }

    @Override
    public Person persist(Person person) {
        this.persons.add(person);
        return person;
    }

    @Override
    public Person findById(int id) {
        List<Person> personAll = new ArrayList<>(findAll());
        for (Person person : personAll) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }

    @Override
    public Person findByEmail(String email) {
        List<Person> personAll = new ArrayList<>(findAll());
        for (Person person : personAll) {
            if (person.getEmail().equalsIgnoreCase(email)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public Collection<Person> findAll() {
        return persons;
    }

    @Override
    public void remove(int id) {
        persons.removeIf(person -> person.getId() == id);
/*        for (Person person : persons) {
            if (person.getId() == id) {
                persons.remove(person);
            }
        }*/ // This is the same logic, different code
    }
}
