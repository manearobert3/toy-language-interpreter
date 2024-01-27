package Stmts.ToySemaphore;

import ADT.*;
import Controller.ToyLanguageException;
import Stmts.IStmt;
import Types.IntType;
import Types.Type;
import Values.IntValue;
import Values.Value;

public class AcquireStmt implements IStmt {
    private final String var;

    public AcquireStmt(String var) {
        this.var = var;
    }
    @Override
    public PrgState execute(PrgState state) throws ToyLanguageException {
        MyIDictionary<String, Value> symbolTable = state.getSymTable();
        MyIToySemaphore<Triplet> semTable = state.getToySemaphoreTable();
        MyIStack<IStmt> stack = state.getExeStack();

        if(!symbolTable.isDefined(this.var))
            throw new ToyLanguageException("Variable is not in symbol table");
        Value varVal = symbolTable.lookUp(this.var);
        if(!varVal.getType().equals(new IntType()))
            throw new ToyLanguageException("Variable's value is not of type int");

        int foundIndex = ((IntValue) varVal).getValue();

        if(!semTable.exists(foundIndex))
            throw new ToyLanguageException("Index does not exist");

        Triplet triplet = semTable.lookup(foundIndex);
        if(triplet.first - triplet.third >= triplet.second.size()){
            if(!triplet.second.contains(state.getID()))
                triplet.second.add(state.getID());
        } else
            stack.push(this);

        state.setExeStack(stack);
        state.setToySemaphoreTable(semTable);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new AcquireStmt(this.var);    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        return typeEnv;
    }
    @Override
    public String toString() {
        return "ToyAcquire(" + this.var + ")";
    }
}
