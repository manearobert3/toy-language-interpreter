package Stmts;

import ADT.MyIDictionary;
import ADT.PrgState;
import Controller.ToyLanguageException;
import Types.Type;

import java.nio.file.OpenOption;

public class NopStmt implements IStmt{
    @Override
    public PrgState execute(PrgState state) throws ToyLanguageException {
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new NopStmt();
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        return typeEnv;
    }

    @Override
    public String toString(){
        return "nup";
    }

}
