package Stmts;

import ADT.MyIDictionary;
import ADT.MyIHeap;
import ADT.PrgState;
import Controller.ToyLanguageException;
import Expressions.Expression;
import Types.RefType;
import Types.Type;
import Values.RefValue;
import Values.Value;
public class NewHeapStmt implements IStmt{
    private final String varName;
    private final Expression expression;

    public NewHeapStmt(String varname,Expression exp){
        this.varName=varname;
        this.expression=exp;
    }
    @Override
    public PrgState execute(PrgState state) throws ToyLanguageException {
        MyIDictionary<String,Value> symTable=state.getSymTable();
        MyIHeap heap=state.getHeap();
        if(!symTable.isDefined(varName))
            throw new ToyLanguageException(String.format("%s is not defined in the symTable",varName));
        Value value= symTable.lookUp(varName);
        if(!(value.getType() instanceof RefType))
            throw new ToyLanguageException(String.format("%s not of type Ref Type in New",value));
        Value toEval = expression.evaluate(symTable,heap);
        RefValue refValue = (RefValue) value;
        if(!toEval.getType().equals(refValue.getLocationType()))
            throw new ToyLanguageException(String.format("Expression value of type %s not of type %s",toEval,refValue.getLocationType()));
        Integer id = heap.add(toEval);
        symTable.put(varName, new RefValue(id,refValue.getLocationType()));
        state.setHeap(heap);
       // state.setSymTable(symTable);
        return null;



    }

    @Override
    public IStmt deepCopy() {
        return new NewHeapStmt(varName,expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        Type typevar = typeEnv.lookUp(varName);
        Type typexp = expression.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new ToyLanguageException("NEW stmt: right hand side and left hand side have different types ");
    }

    @Override
    public String toString(){
        return String.format("New(%s,%s)",varName,expression);
    }
}
