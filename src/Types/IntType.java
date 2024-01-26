package Types;
import Values.Value;
import Values.IntValue;
    public class IntType implements Type{
    @Override
    public boolean equals(Type secondType){
        if (secondType instanceof IntType)
            return true;
        else
            return false;
    }
    @Override
    public String toString(){
        return "int";
    }

    @Override
    public Type deepCopy(){
        return new IntType();
    }


        @Override
    public Value defaultValue(){
        return new IntValue(0);
    }

}
