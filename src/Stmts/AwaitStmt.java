package Stmts;

import ADT.MyIDictionary;
import ADT.PrgState;
import Controller.ToyLanguageException;
import Types.IntType;
import Types.Type;
import Values.IntValue;
import Values.Value;

public class AwaitStmt implements IStmt{
    private String variableName;

    public AwaitStmt(String variableName){
        this.variableName = variableName;
    }
    @Override
    public PrgState execute(PrgState state) throws ToyLanguageException {
        Value variableValue = state.getSymTable().lookUp(variableName);
        if(variableValue==null)
            throw new ToyLanguageException(String.format("Variable %s not declared",variableName));
        if(!variableValue.getType().equals(new IntType()))
            throw new ToyLanguageException(String.format("Variable %s should be of type int",variableName));

        Integer latchLoc = ((IntValue) variableValue).getValue();
        Integer latchVal = state.getLatchTable().get(latchLoc);

        if(latchVal == null)
            throw new ToyLanguageException("Not a valid latch Location");
        if(latchVal!=0)
            state.getExeStack().push(this);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new AwaitStmt(variableName);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        try {
            Type variableType = typeEnv.lookUp(variableName);
            if (variableType == null)
                throw new ToyLanguageException(String.format("Variable %s is not declared", variableName));
            if (!variableType.equals(new IntType()))
                throw new ToyLanguageException(String.format("Variable %s is not of type int", variableName));

        } catch (ToyLanguageException e) {
            throw new ToyLanguageException(e.getMessage());
        }

        return typeEnv;
    }
    @Override
    public String toString() {
        return String.format("await(%s)", variableName);
    }
}
