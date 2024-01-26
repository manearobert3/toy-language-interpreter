package Values;

import Types.IntType;
import Types.Type;
import Types.StringType;
public class StringValue implements Value{

    private final String value;

    public StringValue(String value)
    {
        this.value=value;
    }
    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public Value deepCopy() {
        return new StringValue(value);
    }
    @Override
    public String toString(){
        return value;
    }

    @Override
    public boolean equals(Object anotherObject){
        if(!(anotherObject instanceof StringType))
            return false;
        StringValue valueToCheck=(StringValue) anotherObject;
        return valueToCheck.value.equals(this.value);
    }

}
