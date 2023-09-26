package lexicon.data.dao;

import lexicon.model.Person;
import org.springframework.stereotype.Component;
import lexicon.db.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class PersonDaoCollection implements PersonDao {

    private final Collection<Person> persons;

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
    public void create(Person person) {
        // Check if a person with the same firstName and lastName already exists
        if (!personExists(person.getId(), person.getFirstName(), person.getLastName())) {
            try (Connection connection = MySQLConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO person (first_name, last_name) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setString(1, person.getFirstName());
                preparedStatement.setString(2, person.getLastName());
//                preparedStatement.setString(3, person.getEmail());

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int generatedId = generatedKeys.getInt(1);
                            person.setId(generatedId); // Set the generated ID in the Person object
                        }
                    }
                    persons.add(person); // Add the new person to the collection
                    System.out.println("Person created successfully!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Person with the same first name and last name already exists.");
        }
    }


    @Override
    public void findById(int id) {
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("select * from person where person_id = ? ")
        ) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int personId = resultSet.getInt("person_id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    // Retrieve other columns as needed

                    // Create a Person object with the retrieved data
                    Person person = new Person(personId, firstName, lastName);//, email);
                    persons.add(person); // Add the new person to the collection
                    System.out.println("Found Person: " + person);
                } else {
                    System.out.println("Person with ID " + id + " not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Person> findAll() {
        List<Person> allPersons = new ArrayList<>();

        try (Connection connection = MySQLConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM person")) {

            while (resultSet.next()) {
                int personId = resultSet.getInt("person_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                // Create a Person object with the retrieved data
                Person person = new Person(personId, firstName, lastName);
                allPersons.add(person);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Print out the found persons
        if (!allPersons.isEmpty()) {
            for (Person person : allPersons) {
                System.out.println("Found Person: " + person.getFirstName() + " " + person.getLastName() + " with ID: " + person.getId());
            }
        } else {
            System.out.println("No persons found in the database.");
        }

        return allPersons;
    }


    @Override
    public void delete(int id) {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM person WHERE person_id = ?")) {

            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Person with ID " + id + " deleted successfully from the database.");
            } else {
                System.out.println("Person with ID " + id + " not found in the database.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update(Person person) {
        // Check if the person with the provided ID exists in the database
        if (personExists(person.getId(), person.getFirstName(), person.getLastName())) {
            try (Connection connection = MySQLConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("UPDATE person SET first_name = ?, last_name = ? WHERE person_id = ?")) {

                preparedStatement.setString(1, person.getFirstName());
                preparedStatement.setString(2, person.getLastName());
                preparedStatement.setInt(3, person.getId());

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Person with ID " + person.getId() + " updated successfully in the database.");
                } else {
                    System.out.println("Person with ID " + person.getId() + " not found in the database.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Person with ID " + person.getId() + " not found in the database.");
        }
    }
    public Collection<Person> findByName(String name) {
        List<Person> matchingPersons = new ArrayList<>();

        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE first_name = ? OR last_name = ?")) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int personId = resultSet.getInt("person_id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");

                    // Create a Person object with the retrieved data
                    Person person = new Person(personId, firstName, lastName);
                    matchingPersons.add(person);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Print out the found persons
        if (!matchingPersons.isEmpty()) {
            for (Person person : matchingPersons) {
                System.out.println("Found Person: " + person.getFirstName() + " " + person.getLastName() + " with ID: " + person.getId());
            }
        } else {
            System.out.println("No persons found with the name '" + name + "'.");
        }

        return matchingPersons;
    }


    private boolean personExists(int id, String firstName, String lastName) {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE person_id = ? OR (first_name = ? AND last_name = ?)")) {

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Returns true if a matching person exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Default to false if there's an error or no match found
    }



}
