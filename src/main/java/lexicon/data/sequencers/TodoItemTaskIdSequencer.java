package lexicon.data.sequencers;

public class TodoItemTaskIdSequencer {

    //fields
    private static TodoItemTaskIdSequencer instance = new TodoItemTaskIdSequencer();
    private static int currentId = 0;

    //constructor
    private TodoItemTaskIdSequencer() {
    }

    //methods
    public  int nextId() {
        return ++currentId;
    }

    public static TodoItemTaskIdSequencer getInstance() {
        return instance;
    }
    public  int getCurrentId() {
        return currentId;
    }

    public void setCurrentId(int id) {
        currentId = id;
    }
}
