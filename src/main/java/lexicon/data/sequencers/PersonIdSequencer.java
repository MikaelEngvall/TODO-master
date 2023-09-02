package lexicon.data.sequencers;

public class PersonIdSequencer {

    //fields
    private static PersonIdSequencer instance = new PersonIdSequencer();
    private int currentId = 0;

    //constructor
    private PersonIdSequencer() {
    }

    // methods
    public int nextId() {
        return ++currentId;
    }

    public static PersonIdSequencer getInstance() {
        return instance;
    }

    public int getCurrentId() {
        return currentId;
    }

    public void setCurrentId(int id) {
        currentId = id;
    }
}
