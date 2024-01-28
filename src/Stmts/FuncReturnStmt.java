package Stmts;

import ADT.MyIDictionary;
import ADT.PrgState;
import Controller.ToyLanguageException;
import Types.Type;

public class FuncReturnStmt implements IStmt{
    @Override
    public PrgState execute(PrgState state) throws ToyLanguageException {
        try {
            state.getSymTables().pop();
        } catch (ToyLanguageException e) {
            throw new ToyLanguageException(e.getMessage());
        }

        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new FuncReturnStmt();
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "return";
    }
}
