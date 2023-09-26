package lexicon.data.dao;

import lexicon.model.Person;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public interface PersonDao extends BaseDao<Person> {

    Person persist(Person person);

    void findById(int id);

    Collection<Person> findByName(String name);

    Collection<Person> findAll();

    void create(Person person);

    void delete(int id);

    void update(Person person);
}
