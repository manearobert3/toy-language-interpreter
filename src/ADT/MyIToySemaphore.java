package ADT;
import java.util.List;

import Controller.ToyLanguageException;
import javafx.util.Pair;



import java.util.List;
import java.util.Map;

public interface MyIToySemaphore<T>  {
    int allocate(T value);

    T lookup(int address);
    void update(int address, T value);
    boolean exists(int address);

    Map<Integer, T> getContent();
}
