package Stmts.ToySemaphore;

import ADT.*;
import Controller.ToyLanguageException;
import Expressions.Expression;
import Stmts.IStmt;
import Types.Type;
import Values.IntValue;
import Values.Value;

import Types.IntType;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;

public class NewSemaphoreStmt implements IStmt {
    private final String var;
    private final Expression exp1;

    public NewSemaphoreStmt(String var, Expression exp1) {
        this.var = var;
        this.exp1 = exp1;
    }
    @Override
    public PrgState execute(PrgState state) throws ToyLanguageException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap heap = state.getHeap();
        MyICountSemaphore<Pair<Integer, List<Integer>>> semTable = state.getCountSemaphoreTable();


        Value exp1Val = this.exp1.evaluate(symTable, heap);

        if(!exp1Val.getType().equals(new IntType()) )
            throw new ToyLanguageException("Values of expressions are not int");

        if(!symTable.isDefined(this.var))
            throw  new ToyLanguageException("Variable not defined");

        Value varVal = symTable.lookUp(this.var);
        if(!varVal.getType().equals(new IntType()))
            throw new ToyLanguageException("Variable not int");

        IntValue exp1Int = (IntValue) exp1Val;
        int newFreeLocation = semTable.allocate(new Pair<>(exp1Int.getValue(), new LinkedList<>()));
        symTable.put(this.var, new IntValue(newFreeLocation));

        state.setSymTable(symTable);
        state.setCountSemaphoreTable(semTable);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new NewSemaphoreStmt(this.var, this.exp1);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        Type varType = typeEnv.lookUp(this.var);
        Type expType = this.exp1.typecheck(typeEnv);

        if(varType.equals(new IntType()) && expType.equals(new IntType()))
            return typeEnv;
        else throw new ToyLanguageException("Types are not integer");
    }

    @Override
    public String toString() {
        return "newToySemaphore (" + this.var + ", " + this.exp1.toString() +  ")";
    }
}
