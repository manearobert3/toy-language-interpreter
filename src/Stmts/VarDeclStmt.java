package Stmts;

import ADT.MyIDictionary;
import ADT.PrgState;
import Controller.ToyLanguageException;
import Controller.TypeDoesNotMatch;
import Types.BoolType;
import Types.IntType;
import Types.StringType;
import Types.Type;
import Values.Value;

public class VarDeclStmt implements IStmt{
    private final String name;
    private final Type type;

    public VarDeclStmt(String name,Type type){
        this.name=name;
        this.type=type;
    }


    @Override
    public PrgState execute(PrgState state) throws ToyLanguageException {
        MyIDictionary<String,Value> symtable= state.getSymTable();
        if(symtable.isDefined(name))
            throw new ToyLanguageException("Already existing variable");
//        if (type.equals(new IntType())) {
//            symtable.put(name, new IntType().defaultValue());
//        }
//        else if(type.equals(new BoolType())){
//                symtable.put(name,new BoolType().defaultValue());
//        }
//        else if(type.equals(new StringType())){
//            symtable.put(name,new StringType().defaultValue());
//        }
        symtable.put(name,type.defaultValue());
        state.setSymTable(symtable);
        return null;
    }

    @Override
    public String toString() {
        return type + " " + name+ " ";
    }
    @Override
    public IStmt deepCopy() {
        return new VarDeclStmt(name,type.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        typeEnv.put(name,type);
        return typeEnv;
    }
}
