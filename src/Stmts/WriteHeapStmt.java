package Stmts;

import ADT.MyIDictionary;
import ADT.MyIHeap;
import ADT.PrgState;
import Controller.ToyLanguageException;
import Controller.TypeDoesNotMatch;
import Expressions.Expression;
import Types.RefType;
import Types.Type;
import Values.RefValue;
import Values.Value;

public class WriteHeapStmt implements IStmt{
    private final String varName;
    private final Expression expression;

    public WriteHeapStmt(String varName, Expression expression){
        this.varName=varName;
        this.expression=expression;
    }


    @Override
    public PrgState execute(PrgState state) throws ToyLanguageException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap heap=state.getHeap();
        if (!symTable.isDefined(varName))
            throw new ToyLanguageException(String.format("key: %s not in the symTable",varName));
        Value value= symTable.lookUp(varName);
        if(!(value.getType() instanceof RefType))
            throw new ToyLanguageException(String.format("value:%s not of type RefType",value));
        RefValue refValue = (RefValue) value;
        Value toEval= expression.evaluate(symTable,heap);
        if(!(toEval.getType().equals(refValue.getLocationType())))
            throw new ToyLanguageException((String.format("Value %s not of type: %s",toEval,refValue.getLocationType())));

        heap.update(refValue.getAdress(),toEval);
        state.setHeap(heap);
//        state.setSymTable(symTable);
        return null;




    }

    @Override
    public String toString(){
        return String.format("HeapStmt(%s,%s);",varName,expression);
    }
    @Override
    public IStmt deepCopy() {
        return new WriteHeapStmt(varName,expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        if(typeEnv.lookUp(varName).equals(new RefType(expression.typecheck(typeEnv.deepCopy()))))
        return typeEnv;
        else throw new ToyLanguageException("Error WriteHeapStmt TypeCheck: the statements are of different types");
    }
}
