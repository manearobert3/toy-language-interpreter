package Expressions;

import ADT.MyIDictionary;
import ADT.MyIHeap;
import Controller.ToyLanguageException;
import Types.Type;
import Values.Value;

import Types.IntType;
public class MulExp implements Expression{
    private final Expression expression1;
    private final Expression expression2;

    public MulExp(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }





    @Override
    public Expression deepCopy() {
        return new MulExp(expression1.deepCopy(), expression2.deepCopy());
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> tbl, MyIHeap heap) throws ToyLanguageException {
        Value v1;
        Value v2;
        Value v3;
        v1=new ArithExp('*', expression1, expression2).evaluate(tbl,heap);
        v2=new ArithExp('+', expression1, expression2).evaluate(tbl,heap);
        v3=new ArithExp('-',new ValueExp(v1),new ValueExp(v2)).evaluate(tbl,heap);
        return v3;
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        Type type1 = expression1.typecheck(typeEnv);
        Type type2 = expression2.typecheck(typeEnv);
        if (type1.equals(new IntType()) && type2.equals(new IntType()))
            return new IntType();
        else
            throw new ToyLanguageException("Expressions in the Mul should be int!");
    }

    @Override
    public String toString() {
        return String.format("MUL(%s, %s)", expression1, expression2);
    }
}