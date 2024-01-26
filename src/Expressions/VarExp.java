package Expressions;

import ADT.MyIHeap;
import Controller.ToyLanguageException;
import Types.Type;
import Values.Value;
import ADT.MyIDictionary;

public class VarExp implements Expression {
    public final String var;
    public VarExp(String var){
        this.var=var;
    }
    @Override
    public Value evaluate(MyIDictionary<String,Value> dic, MyIHeap heap) throws ToyLanguageException{
        return dic.lookUp(var);
    }
    @Override
    public Expression deepCopy(){
        return new VarExp(var);
    }
    @Override
    public String toString(){
        return var;
    }
    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws ToyLanguageException{
        return typeEnv.lookUp(var);
    }
}
