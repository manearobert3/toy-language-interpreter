package Stmts;

import ADT.MyIDictionary;
import ADT.MyIStack;
import ADT.PrgState;
import Controller.ToyLanguageException;
import Expressions.Expression;
import Expressions.ValueExp;
import Types.Type;
import Values.IntValue;

public class WaitStmt implements IStmt {
    private final Integer number;

    public WaitStmt(Integer number) {
        this.number = number;
    }

    @Override
    public PrgState execute(PrgState state) throws ToyLanguageException {
        MyIStack<IStmt> exeStack = state.getExeStack();
        if (number > 0) {
            exeStack.push(new WaitStmt(number - 1));
            exeStack.push(new PrintStmt(new ValueExp(new IntValue(number))));

            state.setExeStack(exeStack);
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new WaitStmt(number);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        return typeEnv;
    }
    @Override
    public String toString(){
        return "Wait("+number+")";
    }
}
