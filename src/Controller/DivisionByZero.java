package Controller;

public class DivisionByZero extends ToyLanguageException {
    public DivisionByZero(String message) {
        super(message);
    }
    public DivisionByZero() {
        super();
    }
}