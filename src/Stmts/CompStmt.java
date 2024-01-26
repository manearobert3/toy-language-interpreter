package Stmts;

import ADT.MyIDictionary;
import ADT.MyIStack;
import ADT.PrgState;
import Controller.ToyLanguageException;
import Types.Type;

public class CompStmt implements IStmt {
    IStmt first;
    IStmt snd;

        public CompStmt(IStmt first,IStmt snd){
            this.first=first;
            this.snd=snd;
        }
        @Override
    public String toString(){
        return "("+first.toString()+","+snd.toString()+")";
    }
    @Override
    public PrgState execute(PrgState state){
        MyIStack<IStmt> stk=state.getExeStack();
        stk.push(snd);
        stk.push(first);
        return null;
    }
    public IStmt getFirst(){
            return first;
    }

    public IStmt getSnd(){
            return snd;
    }
    @Override
    public IStmt deepCopy(){
            return new CompStmt(first.deepCopy(),snd.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        return snd.typecheck(first.typecheck(typeEnv));
    }


}
