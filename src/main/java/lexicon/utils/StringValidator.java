package lexicon.utils;

public class StringValidator {

    public static void validateStringNotEmpty(String string) {
        if (string == null || string.isEmpty() || string.trim().isEmpty())
            throw new IllegalArgumentException("String is null, empty or blank.");
    }
}
