package Expressions;

import ADT.MyIDictionary;
import ADT.MyIHeap;
import Types.Type;
import Values.Value;
import Controller.ToyLanguageException;

public class ValueExp implements Expression{
    public final Value val;

    public ValueExp(Value val) {
        this.val=val;
    }

    @Override
    public Expression deepCopy() {
        return new ValueExp(val);
    }

    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws ToyLanguageException{
        return val.getType();
    }
    @Override
    public Value evaluate(MyIDictionary<String, Values.Value> tbl, MyIHeap heap) throws ToyLanguageException {
        return val;
    }
    @Override
    public String toString(){
        return this.val.toString();
    }
}
