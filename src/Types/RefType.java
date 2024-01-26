package Types;

import Values.RefValue;
import Values.Value;
import Types.Type;
public class RefType implements Type{
    Type inner;
    public RefType(Type inner){
        this.inner=inner;
    }

    public Type getInner(){
        return inner;
    }

    @Override
    public Value defaultValue() {
        return new RefValue(0,inner);
    }

    @Override
    public Type deepCopy() {
        return new RefType(inner.deepCopy());
    }

    @Override
    public boolean equals(Type secondType) {
        if(secondType instanceof RefType) {
            return inner.equals(((RefType) secondType).getInner());
        }
        else
                return false;

        }

        @Override
    public String toString(){
        return "Reference Type("+inner.toString()+") ";
        }
    }

