package ADT;

import Controller.ToyLanguageException;
import Values.Value;

import java.util.Map;

public interface MyIHeap {
    Integer add(Value value);
    Value getValue(Integer pos) throws ToyLanguageException;
    Integer returnFirstFree();

    Map<Integer,Value> getHeap();
    void update(Integer position, Value value) throws ToyLanguageException;

    Map<Integer,Value> getContent();

    void setContent(Map<Integer,Value> map);
    void remove(Integer key)throws ToyLanguageException;
}
