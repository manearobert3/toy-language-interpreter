package Stmts;

import ADT.MyIDictionary;
import ADT.PrgState;
import Controller.ToyLanguageException;
import Types.Type;

import java.net.IDN;

public interface IStmt {
    PrgState execute(PrgState state) throws ToyLanguageException;
    IStmt deepCopy();
    MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws ToyLanguageException;
}
