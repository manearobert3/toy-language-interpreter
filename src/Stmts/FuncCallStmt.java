package Stmts;

import ADT.MyDictionary;
import ADT.MyIDictionary;
import ADT.MyList;
import ADT.PrgState;
import Controller.ToyLanguageException;
import Expressions.Expression;
import Types.Type;
import Values.Value;
import javafx.util.Pair;

import java.util.List;
import java.util.Vector;

public class FuncCallStmt implements IStmt{
    private String functionName;
    private MyList<Expression> parameters;
    public FuncCallStmt(String functionName, List<Expression> parameters) {
        this.functionName = functionName;
        this.parameters = new MyList<Expression>();

        for (int i = 0; i < parameters.size(); ++i) {
            this.parameters.add(parameters.get(i));
        }
    }
    @Override
    public PrgState execute(PrgState state) throws ToyLanguageException {
        try {
            Pair<List<String>, IStmt> functionEntry = state.getProcedureTable().lookUp(functionName);
            if (functionEntry == null)
                throw new ToyLanguageException(String.format("Function '%s' does not exist!", functionName));

            List<String> paramNames = functionEntry.getKey();
            IStmt functionBody = functionEntry.getValue();

            List<Value> paramValues = new Vector<Value>();
            for (int i = 0; i < parameters.size(); ++i)
                paramValues.add(parameters.get(i).evaluate(state.getSymTable(), state.getHeap()));

            MyIDictionary<String, Value> newSymbolsTable = new MyDictionary<>();
            int size = paramNames.size();
            for (int i = 0; i < size; ++i)
                newSymbolsTable.put(paramNames.get(i), paramValues.get(i));

            state.getSymTables().push(newSymbolsTable);
            state.getExeStack().push(new FuncReturnStmt());
            state.getExeStack().push(functionBody);
        } catch (ToyLanguageException e) {
            throw new ToyLanguageException(e.getMessage());
        }

        return null;
    }

    @Override
    public IStmt deepCopy() {
        List<Expression> newParams = new Vector<Expression>();
        for (int i = 0; i < parameters.size(); ++i) {
            try {
                newParams.add(parameters.get(i).deepCopy());
            } catch (ToyLanguageException e) {
                return null;
            }
        }

        return new FuncCallStmt(functionName, newParams);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ToyLanguageException {
        return typeEnv;
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("call " + functionName + "(");
        for (int i = 0; i < parameters.size() - 1; ++i) {
            try {
                result.append(parameters.get(i).toString()).append(", ");
            } catch (ToyLanguageException e) {
                return null;
            }
        }

        if (parameters.size() > 0) {
            try {
                result.append(parameters.get(parameters.size() - 1).toString());
                result.append(")");
            } catch (ToyLanguageException e) {
                return null;
            }
        }

        return result.toString();
    }
}
