package ADT;

import Controller.StackIsEmptyException;
import Controller.ToyLanguageException;

import java.util.List;

public interface MyIStack<T> {
    T pop() throws StackIsEmptyException;
    void push(T e);
    MyStack<T>  deepCopy();
    boolean isEmpty();

    List<T> getReversed();
}
