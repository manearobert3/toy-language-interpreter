package Controller;

public class StackIsEmptyException extends ToyLanguageException {
    public StackIsEmptyException(String message) {
        super(message);
    }
    public StackIsEmptyException() {
        super();
    }
}