package Stmts;

import ADT.MyIDictionary;
import ADT.PrgState;
import Controller.ToyLanguageException;
import Expressions.Expression;
import Types.Type;

public class ConditionalAssignment implements IStmt{
    private String variable;
    private Expression condition;
    private Expression trueBranch;
    private Expression falseBranch;
    public ConditionalAssignment(String variable, Expression condition, Expression trueBranch, Expression falseBranch) {
        this.variable = variable;
        this.condition = condition;
        this.trueBranch = trueBranch;
        this.falseBranch = falseBranch;
    }
    @Override
    public PrgState execute(PrgState state) throws ToyLanguageException {
        IfStmt ifStmt = new IfStmt(
                condition,
                new AssignStmt(variable, trueBranch),
                new AssignStmt(variable, falseBranch)
        );
        state.getExeStack().push(ifStmt);

        return null;
    }

    @Override
    public IStmt deepCopy() {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        return typeEnv;
    }


    @Override
    public String toString() {
        return variable + " = (" + condition + ") ? " + trueBranch + " : " + falseBranch;
    }
}
