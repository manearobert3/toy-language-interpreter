package Stmts.ToySemaphore;

import ADT.MyIDictionary;
import ADT.MyIToySemaphore;
import ADT.PrgState;
import ADT.Triplet;
import Controller.ToyLanguageException;
import Stmts.IStmt;
import Types.Type;
import Types.IntType;

import Values.IntValue;
import Values.Value;
public class ReleaseVarStmt implements IStmt {
    private final String var;

    public ReleaseVarStmt(String var) {
        this.var = var;
    }
    @Override
    public PrgState execute(PrgState state) throws ToyLanguageException {
        MyIDictionary<String, Value> symbolTable = state.getSymTable();
        MyIToySemaphore<Triplet> semTable = state.getToySemaphoreTable();

        if(!symbolTable.isDefined(this.var))
            throw new ToyLanguageException("Variable is not in symbol table");
        Value varVal = symbolTable.lookUp(this.var);
        if(!varVal.getType().equals(new IntType()))
            throw new ToyLanguageException("Variable's value is not of type int");

        int foundIndex = ((IntValue) varVal).getValue();

        if(!semTable.exists(foundIndex))
            throw new ToyLanguageException("Index does not exist");

        Triplet triplet = semTable.lookup(foundIndex);
        if(triplet.second.contains(state.getID()))
            triplet.second.remove(triplet.second.indexOf(state.getID()));

        state.setToySemaphoreTable(semTable);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new ReleaseVarStmt(this.var);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        return typeEnv;
    }
    @Override
    public String toString() {
        return "ToyRelease (" + this.var + ')';
    }
}
