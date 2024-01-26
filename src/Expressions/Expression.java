package Expressions;
import ADT.MyIHeap;
import Controller.DivisionByZero;
import Controller.ToyLanguageException;
import Types.Type;
import Values.Value;
import ADT.MyIDictionary;
public interface Expression {
    Expression deepCopy();
    Value evaluate(MyIDictionary<String,Value> tbl, MyIHeap heap) throws ToyLanguageException;
    Type typecheck (MyIDictionary<String, Type> typeEnv) throws ToyLanguageException;

}
