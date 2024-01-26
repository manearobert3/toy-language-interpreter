package Expressions;

import ADT.MyIDictionary;
import ADT.MyIHeap;
import Controller.ToyLanguageException;
import Controller.TypeDoesNotMatch;
import Types.BoolType;
import Types.IntType;
import Types.Type;
import Values.BoolValue;
import Values.Value;

import java.util.Objects;

public class LogicExp implements Expression {
    Expression exp1;
    Expression exp2;
    String operator;

    public LogicExp(String operator, Expression exp1, Expression exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operator = operator;
    }

    @Override
    public Expression deepCopy() {
        return new LogicExp(operator, exp1.deepCopy(), exp2.deepCopy());
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> tbl, MyIHeap heap) throws ToyLanguageException {
        Value val1, val2;
        val1 = exp1.evaluate(tbl,heap);
        if (val1.getType().equals(new BoolType())) {
            val2 = exp2.evaluate(tbl,heap);
            if (val2.getType().equals(new BoolType())) {
                BoolValue op1 = (BoolValue) val1;
                BoolValue op2 = (BoolValue) val2;
                boolean bool1, bool2;
                bool1 = op1.getValue();
                bool2 = op2.getValue();
                if (Objects.equals(this.operator, "and")) {
                    return new BoolValue(bool1 && bool2);
                } else if (Objects.equals(this.operator, "or")) {
                    return new BoolValue(bool1 || bool2);
                }
                throw new ToyLanguageException("Unknown operation");
            } else {
                throw new TypeDoesNotMatch("One of the operator is not a boolean");
            }
        } else {
            throw new TypeDoesNotMatch("One of the operator is not a boolean");
        }
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        Type type1, type2;
        type1=exp1.typecheck(typeEnv);
        type2=exp2.typecheck(typeEnv);
        if (type1.equals(new BoolType())) {
            if (type2.equals(new BoolType())) {
                return new BoolType();
            } else
                throw new TypeDoesNotMatch("second operand is not of type bool");
        }else
            throw new TypeDoesNotMatch("first operand is not of type bool");
    }

    @Override
    public String toString(){
        return exp1.toString() +" "+operator+" "+exp2.toString();
    }
}
