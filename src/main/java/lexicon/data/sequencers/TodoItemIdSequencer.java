package lexicon.data.sequencers;

public class TodoItemIdSequencer {

    //fields
    private static TodoItemIdSequencer instance = new TodoItemIdSequencer();
    private int currentId = 0;

    //constructor
    private TodoItemIdSequencer() {
    }

    //methods
    public int nextId() {
        return ++currentId;
    }

    public static TodoItemIdSequencer getInstance() {
        return instance;
    }

    public int getCurrentId() {
        return currentId;
    }

    public void setCurrentId(int id) {
        currentId = id;
    }
}
