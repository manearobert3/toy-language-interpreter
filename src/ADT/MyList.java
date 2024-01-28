package ADT;

import Controller.ToyLanguageException;

import java.util.LinkedList;
import java.util.List;

public class MyList<T> implements MyIList<T> {
    private List<T> output;

    public MyList() {
        output = new LinkedList<T>();
    }

    @Override
    public void add(T e) {
        output.add(e);
    }

    @Override
    public void clear() {
        output.clear();
    }

    public T get(int index) throws ToyLanguageException {
        if (index < 0 || index >= output.size())
            throw new ToyLanguageException("Index out of bounds!");
        try {
            return output.get(index);
        } catch (Exception exception) {
            throw new ToyLanguageException(exception.getMessage());
        }
    }
    @Override
    public String toString() {
        return "MyList{" +
                "output=" + output +
                '}';
    }
    @Override
    public List<T> getOutput() {
        return output;
    }

    public void setOutput(List<T> output) {
        this.output = output;
    }
    public int size() {
        return output.size();
    }

}
