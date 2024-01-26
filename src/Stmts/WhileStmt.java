package Stmts;

import ADT.MyIDictionary;
import ADT.MyIHeap;
import ADT.PrgState;
import Controller.ToyLanguageException;
import Expressions.Expression;
import Types.BoolType;
import Types.StringType;
import Types.Type;
import Values.BoolValue;
import Values.Value;

import java.sql.Statement;

public class WhileStmt implements IStmt{
    private final Expression expression;
    private final IStmt statement;
    public WhileStmt(Expression exp, IStmt stmt){
        this.expression=exp;
        this.statement=stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws ToyLanguageException {
        MyIDictionary<String, Value> symTbl=state.getSymTable();
        MyIHeap heap = state.getHeap();
        Value value = expression.evaluate(symTbl,heap);
        if(!value.getType().equals(new BoolType()))
            throw new ToyLanguageException(String.format("Value: %s is not of type Bool",value));
        if(!(value instanceof BoolValue))
            throw new ToyLanguageException((String.format("Value: %s is not of Bool Value",value)));
        BoolValue boolValue = (BoolValue) value;
        if(boolValue.getValue()){
            state.getExeStack().push(this.deepCopy());
            state.getExeStack().push(statement);
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new WhileStmt(expression.deepCopy(),statement.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        Type type = expression.typecheck(typeEnv);
        if (type.equals(new BoolType())) {
            statement.typecheck(typeEnv.deepCopy());
            return typeEnv;
        }
        throw new ToyLanguageException("While Error TypeChecker: We need bool condition.");
    }



    @Override
    public String toString(){
        return String.format("WhileStmt{%s,%s}",expression,statement);
    }

}
