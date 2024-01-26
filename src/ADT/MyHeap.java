package ADT;

import Controller.KeyNotFoundInDictionary;
import Controller.ToyLanguageException;
import Values.Value;

import java.util.HashMap;
import java.util.Map;

public class MyHeap implements MyIHeap{
    private Map<Integer, Value> map;
    private Integer firstFree;

    public MyHeap(Map<Integer,Value> map){
        this.map=map;
        this.firstFree=1;
    }

    public MyHeap(){
        this.map=new HashMap<>();
        this.firstFree=1;
    }

    public void newFirstFree(){
        this.firstFree=this.firstFree+1;
        while(firstFree==0||map.containsKey(firstFree))
            firstFree+=1;
    }
    
    @Override
    public Integer add(Value value) {
        map.put(firstFree,value);
        Integer location = firstFree;
        firstFree=this.returnFirstFree();
        return location;
    }

    @Override
    public Value getValue(Integer pos) throws ToyLanguageException {
        if(!map.containsKey(pos))
            throw new KeyNotFoundInDictionary(String.format("No such key: %s in the heap",pos));
        return map.get(pos);
    }

    @Override
    public Integer returnFirstFree(){
        this.newFirstFree();
        return this.firstFree;
    }
    @Override
    public Map<Integer,Value> getHeap(){
        return this.map;
    }

    @Override
    public void update(Integer position, Value value) throws ToyLanguageException {
        if(!map.containsKey(position))
            throw new ToyLanguageException(String.format("The position: %d is not in the Heap",position));
        map.put(position,value);


    }

    @Override
    public Map<Integer, Value> getContent() {
        return map;
    }

    @Override
    public void setContent(Map<Integer, Value> map) {
        this.map.clear();
        this.map=map;
    }

    @Override
    public void remove(Integer key)throws ToyLanguageException{
            if(!map.containsKey(key))
                throw new KeyNotFoundInDictionary(String.format("Could not remove key: %s as it is not in the heap",key));
            firstFree=key;
            this.map.remove(key);
    }

}
