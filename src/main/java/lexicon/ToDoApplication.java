package lexicon;

import lexicon.model.Person;
import lexicon.model.TodoItem;
import lexicon.model.TodoItemTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class ToDoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ToDoApplication.class, args);
        Person testPerson = new Person("Mikael", "Engvall", "mikael@engvall.org");
        TodoItem testItem = new TodoItem("Change tires", "task description",LocalDate.of(2023, 9, 15), false, testPerson);
        TodoItemTask testObject = new TodoItemTask(true, testItem, testPerson);
        System.out.println(testPerson);
        System.out.println(testItem);
        System.out.println(testObject);
    }





}

