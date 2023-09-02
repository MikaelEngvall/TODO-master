package lexicon.model;

import lexicon.data.impl.AppUser;
import lexicon.data.sequencers.PersonIdSequencer;
import lexicon.utils.StringValidator;

import java.util.Objects;

public class Person {

    // Fields
    private final int id;
    private String firstName;
    private String lastName;
    private AppUser credentials;

    // Constructor(s)


    public Person() {
        id = PersonIdSequencer.getInstance().nextId();
    }

    public Person(String firstName, String lastName, String email) {
        this();
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }

    // Methods

    // Overridden from Object


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        StringValidator.validateStringNotEmpty(firstName);
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        StringValidator.validateStringNotEmpty(lastName);
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        StringValidator.validateStringNotEmpty(email);
        this.email = email;
    }

    private String email;


}
