package Expressions;

import ADT.MyIDictionary;
import ADT.MyIHeap;
import Controller.DivisionByZero;
import Controller.ToyLanguageException;
import Controller.TypeDoesNotMatch;
import Types.BoolType;
import Types.IntType;
import Types.Type;
import Values.BoolValue;
import Values.IntValue;
import Values.Value;

public class RelationalExp implements Expression {
    Expression exp1;
    Expression exp2;
    String sign;

    @Override
    public Expression deepCopy() {
        return new RelationalExp(sign, exp1.deepCopy(), exp2.deepCopy());
    }

    public RelationalExp(String sign, Expression exp1, Expression exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.sign = sign;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> tbl, MyIHeap heap) throws ToyLanguageException {
        Value v1, v2;
        v1 = this.exp1.evaluate(tbl,heap);
        if (v1.getType().equals(new IntType())) {
            v2 = this.exp2.evaluate(tbl,heap);
            if (v2.getType().equals(new IntType())) {
                IntValue nr1 = (IntValue) v1;
                IntValue nr2 = (IntValue) v2;
                int op1, op2;
                op1 = nr1.getValue();
                op2 = nr2.getValue();
                if (this.sign.equals("<"))
                    return new BoolValue(op1 < op2);
                if (this.sign.equals("<="))
                    return new BoolValue(op1 <= op2);
                if (this.sign.equals(">"))
                    return new BoolValue(op1 > op2);
                if (this.sign.equals(">="))
                    return new BoolValue(op1 > op2);
                if (this.sign.equals("=="))
                    return new BoolValue(op1 == op2);
                if (this.sign.equals("!="))
                    return new BoolValue(op1 != op2);
            } else throw new TypeDoesNotMatch("One of the operands is not integer");
        } else throw new TypeDoesNotMatch("One of the operands is not integer");
        throw new ToyLanguageException("Not a valid sign inputted");
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        Type type1, type2;
        type1=exp1.typecheck(typeEnv);
        type2=exp2.typecheck(typeEnv);
        if (type1.equals(new IntType())) {
            if (type2.equals(new IntType())) {
                return new BoolType();
            } else
                throw new TypeDoesNotMatch("second operand is not an integer");
        }else
            throw new TypeDoesNotMatch("first operand is not an integer");
    }

    @Override
    public String toString(){
        return String.format("RelationalExpression(%s %s %s",exp1,sign,exp2);
    }
}