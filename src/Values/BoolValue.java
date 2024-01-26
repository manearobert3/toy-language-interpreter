package Values;

import Types.BoolType;
import Types.Type;
import com.sun.jdi.BooleanType;

public class BoolValue implements Value {
    private final boolean val;

    public BoolValue(boolean v) {
        val = v;
    }


    @Override
    public Type getType() {
        return new BoolType();
    }


    @Override
    public Value deepCopy() {
        return new BoolValue(val);
    }

    public boolean getValue() {
        return val;
    }

    @Override
    public String toString() {
        if (val) {
            return "true";
        } else {
            return "false";
        }
    }
    @Override
    public boolean equals(Object anotherObject){
        if(!(anotherObject instanceof BoolType))
            return false;
        BoolValue valueToCheck=(BoolValue) anotherObject;
        return valueToCheck.val==val;
    }
}

