package ADT;

import Controller.KeyNotFoundInDictionary;
import Controller.ToyLanguageException;
import View.Command;

import java.security.Key;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K,V> implements MyIDictionary<K,V>{
    Map<K,V> map;

    public MyDictionary() {
        map = new HashMap<K,V>();
    }

    @Override
    public void remove(K k) throws KeyNotFoundInDictionary {
        if(!isDefined(k))
            throw new KeyNotFoundInDictionary("Key not found in dictionary.");
        map.remove(k);
    }
    @Override
    public void put(K k, V v) throws ToyLanguageException {
        map.put(k,v);
    }

    @Override
    public boolean isDefined(K k) {
        return map.get(k) != null;
    }

    @Override
    public void update(K key, V value) throws ToyLanguageException {
        if (!isDefined(key))
            throw new ToyLanguageException(key + " is not defined.");
        this.map.put(key, value);
    }

    @Override
    public V lookUp(K k) throws KeyNotFoundInDictionary {
        if(!map.containsKey(k))
            throw new KeyNotFoundInDictionary(k.toString()+" does not exist.");
        return map.get(k);
    }

    @Override
    public String toString() {
        return "MyDictionary{" +
                "map=" + map +
                '}';
    }
    @Override
    public Map<K, V> getMap() {
        return this.map;
    }

    @Override
    public MyIDictionary<K,V> deepCopy() throws ToyLanguageException {
        MyIDictionary<K,V> newMap=  new MyDictionary<>();
        for(K key: map.keySet())
            newMap.put(key,lookUp(key));
        return newMap;
    }

    public void setMap(Map<K, V> map) {
        this.map = map;
    }


}
