package Stmts;

import ADT.PrgState;
import Controller.ToyLanguageException;
import Types.Type;
import ADT.MyIDictionary;
import ADT.MyIList;
import Expressions.Expression;
import Values.Value;

public class PrintStmt implements IStmt {
    private final Expression exp;
    public PrintStmt(Expression exp){
        this.exp=exp;
    }

    @Override
    public PrgState execute(PrgState state) throws ToyLanguageException{
        MyIList<Value> lista= state.getOut();
        lista.add(exp.evaluate(state.getSymTable(),state.getHeap()));
        state.setOut(lista);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new PrintStmt(exp.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString(){
        return "Print "+exp.toString()+" ";
    }

}
