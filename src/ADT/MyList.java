package ADT;

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
}
