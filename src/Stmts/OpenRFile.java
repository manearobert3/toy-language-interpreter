package Stmts;

import ADT.MyIDictionary;
import ADT.PrgState;
import Controller.ToyLanguageException;
import Expressions.Expression;
import Types.StringType;
import Types.Type;
import Values.StringValue;
import Values.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class OpenRFile implements IStmt{
    private final Expression exp;

    public OpenRFile(Expression expression){
        exp=expression;
    }
    @Override
    public PrgState execute(PrgState state) throws ToyLanguageException {
        Value value = exp.evaluate(state.getSymTable(),state.getHeap());
        if(!(value instanceof StringValue)){
            throw new ToyLanguageException("Expression is not of String Type");

        }
        String filename=((StringValue) value).toString();
        if(state.getFileTable().isDefined(filename)){
            throw new ToyLanguageException("File is already open");
        }
        BufferedReader br;
        try{
            br = new BufferedReader(new FileReader(filename));

            // Create a new entry in the FileTable
            state.getFileTable().put(filename, br);
        } catch (IOException e) {
            throw new ToyLanguageException("Error: IO exception while opening the file");
        }
        state.getFileTable().put(filename.toString(),br);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        if(exp.typecheck(typeEnv).equals(new StringType())){
            return typeEnv;
        }
        else
            throw new ToyLanguageException("openReadFile typeCheck Error: expression must be a string.");
    }
    @Override
    public IStmt deepCopy() {
        return new OpenRFile(exp.deepCopy());
    }
    @Override
    public String toString(){
        return "OpenRFile of "+exp ;
    }
}
