package Types;

import Values.Value;
import Values.StringValue;
public class StringType implements Type{
    @Override
    public Value defaultValue() {
        return new StringValue("");
    }

    @Override
    public Type deepCopy() {
        return new StringType();
    }

    @Override
    public boolean equals(Type secondType) {
        if(secondType instanceof StringType)
            return true;
            else
                return false;

    }

    @Override
    public String toString()
    {
        return "string";
    }
}
