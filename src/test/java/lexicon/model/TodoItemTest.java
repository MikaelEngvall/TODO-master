package lexicon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TodoItemTest {

    private TodoItem testObject;

    @BeforeEach
    public void setup() {
        testObject = new TodoItem();
        testObject.setDeadLine(LocalDate.of(2023, 9, 1));
        //todo: fill in more fields if needed
    }
    @Test
    @DisplayName("TodoItem object deadline cannot be null")
    public void testSetDeadline() {
        TodoItem todoItem = new TodoItem();
        assertThrows(NullPointerException.class,
                () -> todoItem.setDeadLine(null));
    }

    @Test
    @DisplayName("Checks if task is overdue")
    public void testIsOverdue() {
        LocalDate date = LocalDate.of(2023, 9, 2);
        assertTrue(testObject.isOverdue(date));
    }



}