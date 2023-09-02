package lexicon.utils;

public class ObjectValidator {

    public static void validateObject(Object object) {
        if (object == null)
            throw new NullPointerException("Date is null.");
    }
}
