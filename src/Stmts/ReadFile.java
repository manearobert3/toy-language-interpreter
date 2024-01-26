package Stmts;

import ADT.MyIDictionary;
import ADT.PrgState;
import Controller.ToyLanguageException;
import Expressions.Expression;
import Types.IntType;
import Types.StringType;
import Types.Type;
import Values.StringValue;
import Values.Value;
import Values.IntValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.IDN;

public class ReadFile implements IStmt{
    private final Expression exp;
    private final String name;

    public ReadFile(Expression expression, String variableName){
        exp=expression;
        name=variableName;
    }

    @Override
    public PrgState execute(PrgState state) throws ToyLanguageException {
        MyIDictionary<String,Value> symTable=state.getSymTable();
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();

        if(!symTable.isDefined(name))
            throw new ToyLanguageException("Not a declared variable");
        Value value=symTable.lookUp(name);
        if(!(value.getType().equals(new IntType())))
            throw new ToyLanguageException("Not a statement of type int");
        value=exp.evaluate(symTable,state.getHeap());
        if(!value.getType().equals(new StringType()))
            throw new ToyLanguageException("The evaluated expression is not of type string.");
        StringValue stringValue=(StringValue) value;
        if(!fileTable.isDefined(stringValue.toString()))
            throw new ToyLanguageException(String.format("FileTable doesn't contain %s",stringValue));
        BufferedReader buffRead= fileTable.lookUp(stringValue.toString());
        try {
            String line = buffRead.readLine();
            Object IntValue;
            if (line == null)
                line = "0";
            else {
                IntValue = (IntValue) new IntValue(Integer.parseInt(line));
                symTable.put(name, (Value) IntValue);
            }
            return null;
        }catch (IOException e){
                throw new ToyLanguageException(String.format("Unreadable value from file: %s",stringValue));
            }




    }
    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        if(exp.typecheck(typeEnv).equals(new StringType())){
            if(typeEnv.lookUp(name).equals(new IntType())){
            return typeEnv;
        }
        else
            throw new ToyLanguageException("ReadFile typeCheck Error: its variable must be an int.");}
       else
        {
            throw new ToyLanguageException("ReadFile typeCheck Error: expression must be a string.");
        }
    }

    @Override
    public IStmt deepCopy() {
        return new ReadFile(exp.deepCopy(),name);
    }

    @Override
    public String toString() {
        return String.format("ReadFile(%s,%s)",exp.toString(),name);
    }

}
