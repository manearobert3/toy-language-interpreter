package ADT;

import Controller.ToyLanguageException;

public class MyLatchTable extends MyDictionary<Integer,Integer> implements MyILatchTable{
    private int nextFreeLocation;
    public MyLatchTable(){
        super();
        this.nextFreeLocation=1;
    }
    @Override
    public void put(Integer key,Integer value) throws ToyLanguageException {
        if(!key.equals(nextFreeLocation))
            throw new ToyLanguageException("Invalid Latch Table location!");
        super.put(key,value);
        synchronized (this){
            nextFreeLocation++;
        }
        }

    @Override
    public int put(Integer value) throws ToyLanguageException {
        super.put(nextFreeLocation,value);
        synchronized (this){
            nextFreeLocation++;
        }
        return nextFreeLocation-1;
    }

    @Override
    public int get(int position) throws ToyLanguageException {
        synchronized(this){
            if(!this.map.containsKey(position))
                throw new ToyLanguageException(String.format("%d is not in the table",position));
            return this.map.get(position);
        }
        }

    @Override
    public int getFirstFreeLocation() {
        int locationAdress=1;
        while(this.map.get(locationAdress) != null)
            locationAdress++;
        return locationAdress;
    }
}
