package lexicon.model;

import lexicon.utils.StringValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private Person person;
//    @BeforeEach
//    public void setup() {
//        person = new Person();
//    }

    @ParameterizedTest
    @DisplayName("")
    @CsvSource(value = {
            "null, Loren, a@l.org",
            " , Loren, a@l.org",
            "Anders, Loren, a@l.org"
    }, nullValues = ("null"))
    void createPerson(String firstName, String lastName, String email) {
        person.setFirstName(firstName);
        person.setFirstName(lastName);
        person.setFirstName(email);
        // todo: fix this later
    }
}