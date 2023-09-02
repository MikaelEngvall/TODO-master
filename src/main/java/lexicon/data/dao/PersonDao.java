package lexicon.data.dao;

        import lexicon.model.Person;
        import org.springframework.stereotype.Component;

        import java.util.Collection;
@Component
public interface PersonDao extends BaseDao<Person>{

//    Person persist(Person person);
    Person findById(int id);
    Person findByEmail(String email);
    Collection<Person> findAll();

    void remove(int id);

}
