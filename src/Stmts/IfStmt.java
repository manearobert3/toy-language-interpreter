package Stmts;

import ADT.MyIDictionary;
import ADT.PrgState;
import Controller.ToyLanguageException;
import Controller.TypeDoesNotMatch;
import Expressions.Expression;
import Types.BoolType;
import Types.Type;
import Values.BoolValue;
import Values.Value;

public class IfStmt implements IStmt {

    private final Expression exp;
    private final IStmt first;
    private final IStmt second;

    public IfStmt(Expression e, IStmt f, IStmt s) {
        this.exp = e;
        this.first = f;
        this.second = s;
    }

    @Override
    public PrgState execute(PrgState state) throws ToyLanguageException {
        Value value = exp.evaluate(state.getSymTable(),state.getHeap());
        if (value.getType().equals(new BoolType())) {
            BoolValue expcond = (BoolValue) value;
            if (expcond.getValue())
                state.getExeStack().push(first);
            else
                state.getExeStack().push(second);
            return null;
        } else {
            throw new TypeDoesNotMatch("Not a boolean type was used inside the if");
        }
    }

    @Override
    public String toString(){
        return "if ("+exp.toString()+"): "+first.toString()+"else "+second.toString();
    }

        @Override
        public IStmt deepCopy () {
            return new IfStmt(exp.deepCopy(), first.deepCopy(), second.deepCopy());
        }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        Type typexp=exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            first.typecheck(typeEnv.deepCopy());
            second.typecheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else
            throw new ToyLanguageException("The condition of IF has not the type bool");
    }
    }
