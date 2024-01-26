package Stmts;

import ADT.MyIDictionary;
import ADT.PrgState;
import Controller.KeyNotFoundInDictionary;
import Controller.ToyLanguageException;
import Controller.TypeDoesNotMatch;
import Expressions.Expression;
import Types.StringType;
import Types.Type;
import Values.StringValue;
import Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class closeReadFile implements IStmt{

    private final Expression exp;

    public closeReadFile(Expression expression){
        exp=expression;
    }

    @Override
    public PrgState execute(PrgState state) throws ToyLanguageException {
        MyIDictionary<String, Value>symTable = state.getSymTable();
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        Value value = exp.evaluate(symTable,state.getHeap());
        if(!value.getType().equals(new StringType()))
        {
            throw new TypeDoesNotMatch(String.format("The expression: %s, was not evaluated as a String",exp));
        }
        StringValue filename = (StringValue) value;
        if(!(fileTable.isDefined(filename.toString())))
            throw new KeyNotFoundInDictionary(String.format("The value is not declared in the Symbol Table: %s",value));
        BufferedReader buffRead = fileTable.lookUp(filename.toString());
        try{
            buffRead.close();
        }
        catch(IOException e){
            throw new ToyLanguageException(String.format("Error in closing %s",value));
        }
        fileTable.remove(filename.toString());
        return null;

    }

    @Override
    public IStmt deepCopy() {
        return new closeReadFile(exp.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        if(exp.typecheck(typeEnv).equals(new StringType())){
            return typeEnv;
        }
        else
            throw new ToyLanguageException("closeReadFile typeCheck Error: expression must be a string.");
    }

    @Override
    public String toString(){
        return String.format("ClosedReadFile(%s)",exp.toString());
    }
}
