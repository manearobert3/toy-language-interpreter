package Stmts;

import ADT.*;
import Controller.ToyLanguageException;
import Stmts.IStmt;
import Types.BoolType;
import Types.Type;
import Values.Value;

import java.io.BufferedReader;
import java.util.Stack;

public class forkStmt implements IStmt{
    private final IStmt statement;
    public forkStmt(IStmt stmt){
        this.statement=stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws ToyLanguageException {
        MyIStack<IStmt> newStack = new MyStack<>();
        newStack.push(statement);
        MyIDictionary<String, Value> newSymTable = new MyDictionary<>();
        for (String key : state.getSymTable().getMap().keySet()){
            Value originalValue = state.getSymTable().lookUp(key);
            Value copiedValue = originalValue.deepCopy();
            newSymTable.put(key,copiedValue);
        }
        return new PrgState(newStack,newSymTable,state.getOut(),state.getFileTable(),state.getHeap(),state.getLatchTable());

    }
    @Override
    public String toString(){
        return String.format("Fork(%s)",statement);
    }
    @Override
    public IStmt deepCopy() {
        return new forkStmt(statement.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        statement.typecheck(typeEnv.deepCopy());
        return typeEnv;
    }
}
