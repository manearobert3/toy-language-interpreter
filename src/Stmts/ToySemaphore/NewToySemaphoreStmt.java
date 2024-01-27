package Stmts.ToySemaphore;

import ADT.MyIDictionary;
import ADT.MyIHeap;
import ADT.PrgState;
import ADT.Triplet;
import Controller.ToyLanguageException;
import Expressions.Expression;
import Stmts.IStmt;
import Types.Type;
import Values.IntValue;
import Values.Value;

import Types.IntType;

import java.util.LinkedList;

public class NewToySemaphoreStmt implements IStmt {
    private final String var;
    private final Expression exp1;
    private final Expression exp2;

    public NewToySemaphoreStmt(String var, Expression exp1, Expression exp2) {
        this.var = var;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }
    @Override
    public PrgState execute(PrgState state) throws ToyLanguageException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap heap = state.getHeap();


        Value exp1Val = this.exp1.evaluate(symTable, heap);
        Value exp2Val = this.exp2.evaluate(symTable, heap);

        if(!exp1Val.getType().equals(new IntType()) || !exp2Val.getType().equals(new IntType()))
            throw new ToyLanguageException("Values of expressions are not int");

        if(!symTable.isDefined(this.var))
            throw  new ToyLanguageException("Variable not defined");

        Value varVal = symTable.lookUp(this.var);
        if(!varVal.getType().equals(new IntType()))
            throw new ToyLanguageException("Variable not int");

        int exp1Int = ((IntValue) exp1Val).getValue();
        int exp2Int = ((IntValue) exp2Val).getValue();
        int newFreeLocation = state.getToySemaphoreTable().allocate(new Triplet(exp1Int, new LinkedList<>(), exp2Int));
        symTable.put(this.var, new IntValue(newFreeLocation));

        state.setSymTable(symTable);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new NewToySemaphoreStmt(this.var, this.exp1, this.exp2);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "newToySemaphore (" + this.var + ", " + this.exp1.toString() + ", " + this.exp2.toString() + ")";
    }
}
