package Expressions;

import ADT.MyIDictionary;
import ADT.MyIHeap;
import Controller.ToyLanguageException;
import Controller.TypeDoesNotMatch;
import Types.RefType;
import Types.Type;
import Values.RefValue;
import Values.Value;

import java.sql.Ref;

public class ReadHeapExp implements Expression{
    private final Expression expression;

    public ReadHeapExp(Expression exp){
        expression=exp;
    }

    @Override
    public Expression deepCopy() {
        return new ReadHeapExp(expression.deepCopy());
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> tbl, MyIHeap heap) throws ToyLanguageException {
        Value eval = expression.evaluate(tbl,heap);
        if(!(eval instanceof RefValue)){
            throw new ToyLanguageException(String.format("%s not of Ref Value",eval));
        }
        RefValue refValue = (RefValue) eval;
        return heap.getValue(refValue.getAdress());
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        Type typ=expression.typecheck(typeEnv);
        if (typ instanceof RefType) {
            RefType reft =(RefType) typ;
            return reft.getInner();
        } else
            throw new TypeDoesNotMatch("the rH argument is not a Ref Type");
    }

    @Override
    public String toString(){
        return String.format("ReadHeapExp(%s)",expression);
    }
}
