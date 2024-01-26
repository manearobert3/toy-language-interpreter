package Controller;

public class KeyNotFoundInDictionary extends ToyLanguageException {
    public KeyNotFoundInDictionary(String message) {
        super(message);
    }
    public KeyNotFoundInDictionary() {
        super();
    }
}