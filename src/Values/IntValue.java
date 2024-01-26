package Values;
import Types.*;
import Types.IntType;

public class IntValue implements Value{
    private final int val;
    public IntValue(int v){
        val=v;
    }

    public int getValue(){
        return val;
    }

    @Override
    public String toString() {
        return val+" ";

    }

    public Type getType(){
        return new IntType();
    }

    @Override
    public Value deepCopy() {
        return new IntValue(val);
    }


    @Override
    public boolean equals(Object anotherObject){
        if(!(anotherObject instanceof IntType))
            return false;
        IntValue valueToCheck=(IntValue) anotherObject;
        return valueToCheck.val==val;
    }

}
