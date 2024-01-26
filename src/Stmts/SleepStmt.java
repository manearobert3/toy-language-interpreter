package Stmts;

import ADT.MyIDictionary;
import ADT.MyIStack;
import ADT.PrgState;
import Controller.ToyLanguageException;
import Types.Type;

public class SleepStmt implements IStmt {
    private final Integer number;

    public SleepStmt(Integer number) {
        this.number = number;
    }

    @Override
    public PrgState execute(PrgState state) throws ToyLanguageException {
        MyIStack<IStmt> exeStack = state.getExeStack();
        if (number > 0) {
            exeStack.push(new SleepStmt(number - 1));
            state.setExeStack(exeStack);
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new SleepStmt(number);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        return typeEnv;
    }
    @Override
    public String toString(){
        return "Sleep("+number+")";
    }
}
