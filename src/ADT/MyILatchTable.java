package ADT;

import Controller.ToyLanguageException;

public interface MyILatchTable extends MyIDictionary<Integer,Integer>{

    int put(Integer value) throws ToyLanguageException;
    int get(int position) throws ToyLanguageException;
    int getFirstFreeLocation();

}
