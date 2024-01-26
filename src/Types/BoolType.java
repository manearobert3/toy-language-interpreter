package Types;

import Values.BoolValue;

import javax.swing.text.StyledEditorKit;
import Values.BoolValue;
import Values.Value;
public class BoolType implements Type{
    @Override
    public boolean equals(Type another){
        if (another instanceof BoolType)
            return true;
        else
            return false;
    }
    @Override
    public String toString(){
        return "boolean";
    }

    @Override
    public Value defaultValue(){
        return new BoolValue(false);
    }

    @Override
    public Type deepCopy() {
        return new BoolType();
    }



}
