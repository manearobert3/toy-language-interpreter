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
        MyIDictionary<String, Value> newSymTable = new MyDictionary<>();

        for (String key : state.getSymTable().getMap().keySet()){
            Value originalValue = state.getSymTable().lookUp(key);
            Value copiedValue = originalValue.deepCopy();
            newSymTable.put(key,copiedValue);
        }
        PrgState newProgramStat=new PrgState(newStack,newSymTable,state.getOut(),statement,state.getFileTable(),state.getHeap(),state.getToySemaphoreTable());
        newProgramStat.incrementID();
        return newProgramStat;

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
