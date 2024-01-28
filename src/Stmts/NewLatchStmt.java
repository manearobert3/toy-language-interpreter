package Stmts;

import ADT.MyIDictionary;
import ADT.PrgState;
import Controller.ToyLanguageException;
import Expressions.Expression;
import Types.IntType;
import Types.Type;
import Values.IntValue;
import Values.Value;

public class NewLatchStmt implements IStmt{
    private String variableName;
    private Expression expression;

    public NewLatchStmt(String variableName, Expression expression){
        this.variableName=variableName;
        this.expression=expression;
    }

    @Override
    public PrgState execute(PrgState state) throws ToyLanguageException {
        Value expressionValue = expression.evaluate(state.getSymTable(),state.getHeap());
        if(!expressionValue.getType().equals(new IntType())){
            throw new ToyLanguageException(String.format("Expression '%s' should be evaluated to an integer",expression.toString()));
        }
        int latch = ((IntValue) expressionValue).getValue();
        int latchLoc = state.getLatchTable().put(latch);
        Value variableValue = state.getSymTable().lookUp(variableName);

        if(variableValue==null)
            throw new ToyLanguageException(String.format("Variable %s is no declared",variableName));
        if(!variableValue.getType().equals(new IntType()))
            throw new ToyLanguageException(String.format("Variable %s needs to be of type int",variableName));
        state.getSymTable().put(variableName,new IntValue(latchLoc));
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new NewLatchStmt(variableName,expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        Type variableType = typeEnv.lookUp(variableName);
        if (variableName == null)
        throw new ToyLanguageException(String.format("Variable %s is null, should not be null",variableName));
        if(!variableType.equals(new IntType()))
            throw new ToyLanguageException(String.format("Variable %s should be of type int",variableName));
        Type expressionType = expression.typecheck(typeEnv);
        if(!expressionType.equals(new IntType()))
            throw new ToyLanguageException(String.format("Expression %s should be of type int",expressionType));
        return typeEnv;
    }
    @Override
    public String toString(){
        return String.format("NewLatch(%s, %s)",variableName,expression.toString());
    }
}
