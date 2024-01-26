package Stmts;

import ADT.MyDictionary;
import ADT.MyIDictionary;
import ADT.MyIStack;
import ADT.PrgState;
import Controller.ToyLanguageException;
import Expressions.Expression;
import Expressions.RelationalExp;
import Types.Type;

public class SwitchStmt implements IStmt{

        Expression exp;
        Expression exp1;
        Expression exp2;
        IStmt stmt;
    IStmt stmt1;
    IStmt stmt2;

        public SwitchStmt(Expression exp, Expression exp1, Expression exp2, IStmt stmt, IStmt stmt1, IStmt stmt2) {
            this.exp = exp;
            this.exp1 = exp1;
            this.exp2 = exp2;
            this.stmt = stmt;
            this.stmt1 = stmt1;
            this.stmt2 = stmt2;
        }

        @Override
        public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
            Type typexp = exp.typecheck(typeEnv);
            Type typexp1 = exp1.typecheck(typeEnv);
            Type typexp2 = exp2.typecheck(typeEnv);

            if (typexp.equals(typexp1) && typexp2.equals(typexp1)) {
                stmt.typecheck(typeEnv.deepCopy());
                stmt1.typecheck(typeEnv.deepCopy());
                stmt2.typecheck(typeEnv.deepCopy());
                return typeEnv;
            } else
                throw new ToyLanguageException("The Expressions of Switch Statement were not of the same type!");
        }

        @Override
        public PrgState execute(PrgState state) throws ToyLanguageException {
            MyIStack<IStmt> exeStack = state.getExeStack();
            IStmt newStmt =
                    new IfStmt(
                            new RelationalExp("==", exp, exp1),
                            stmt,
                            new IfStmt(
                                    new RelationalExp("==", exp, exp2),
                                    stmt1,
                                    stmt2));
            exeStack.push(newStmt);
            state.setExeStack(exeStack);
            return null;
        }

        @Override
        public IStmt deepCopy() {
            return new SwitchStmt(exp.deepCopy(), exp1.deepCopy(), exp2.deepCopy(), stmt.deepCopy(), stmt1.deepCopy(), stmt2.deepCopy());
        }

        public String toString() {
            return "\n(switch(" + exp.toString() + ")\n (case(" + exp1.toString()
                    + "): " + stmt.toString() + ")\n(case (" + exp2.toString() + "): " +
                    stmt1.toString() + ")\n (default: " + stmt2.toString() + "));\n";
        }
    }

