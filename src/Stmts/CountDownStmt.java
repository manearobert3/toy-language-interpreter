package Stmts;

import ADT.MyIDictionary;
import ADT.PrgState;
import Controller.ToyLanguageException;
import Types.IntType;
import Types.Type;
import Values.IntValue;
import Values.Value;

public class CountDownStmt implements IStmt{
    private String variableName;

    public CountDownStmt(String variableName) {
        this.variableName = variableName;
    }
    @Override
    public PrgState execute(PrgState state) throws ToyLanguageException {
        Value variableValue = state.getSymTable().lookUp(variableName);
        if (variableValue == null)
            throw new ToyLanguageException(String.format("Variable '%s' has not been declared", variableName));
        if (!variableValue.getType().equals(new IntType()))
            throw new ToyLanguageException(String.format("Variable '%s' should be of integer type", variableName));

        Integer latchLocation = ((IntValue) variableValue).getValue();
        Integer latchValue = state.getLatchTable().get(latchLocation);

        if (latchValue == null)
            throw new ToyLanguageException("Invalid latch table location!");
        if (latchValue > 0)
            state.getLatchTable().update(latchLocation, latchValue-1);

        state.getOut().add(new IntValue(state.getID()));
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new CountDownStmt(variableName);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {

            Type variableType = typeEnv.lookUp(variableName);
            if (variableType == null)
                throw new ToyLanguageException(String.format("Variable %s has not been declared!", variableName));
            if (!variableType.equals(new IntType()))
                throw new ToyLanguageException(String.format("Variable %s should be of integer type!", variableName));



        return typeEnv;

    }
}
