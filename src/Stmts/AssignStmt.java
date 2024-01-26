package Stmts;

import ADT.MyIDictionary;
import ADT.PrgState;
import Controller.KeyNotFoundInDictionary;
import Controller.ToyLanguageException;
import Controller.TypeDoesNotMatch;
import Expressions.Expression;
import Types.Type;
import Values.Value;

import java.net.IDN;

public class AssignStmt implements IStmt{
    private final String repres;
    private final Expression exp;

    public AssignStmt(String string, Expression expression){
        repres=string;
        exp=expression;
    }
    @Override
    public PrgState execute(PrgState state) throws ToyLanguageException {
        MyIDictionary<String,Value> symTable=state.getSymTable();
        Type expType=symTable.lookUp(repres).getType();

        if(symTable.isDefined((repres))){
            Value value=exp.evaluate(symTable, state.getHeap());
        if(value.getType().equals(expType))
            symTable.put(repres,value);
else throw new TypeDoesNotMatch("the declared type and the type of the expression don't match");
        }
        else throw new KeyNotFoundInDictionary("there is no such variable declared");
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new AssignStmt(repres,exp.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        Type typevar = typeEnv.lookUp(repres);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(typexp))
            return typeEnv;
        else
            throw new ToyLanguageException("Assignment: right hand side and left hand side have different types ");
    }

    @Override
    public String toString(){
        return repres+" = "+exp.toString()+" ";
    }
}
