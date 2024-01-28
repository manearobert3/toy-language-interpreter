package ADT;

import Controller.StackIsEmptyException;
import Controller.ToyLanguageException;
import Stmts.IStmt;
import Values.Value;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.net.IDN;
import java.nio.Buffer;

public class PrgState{
    private int id=0;
    private static int incID=0;
    MyIStack<IStmt> exeStack;
    //MyIDictionary<String, Value> symTable;
    MyIStack<MyIDictionary<String,Value>> symTables;
    MyIList<Value> out;
    MyIHeap heap;
    IStmt program;
    MyIProcedureTable procedureTable;
    private final MyIDictionary<String, BufferedReader> filetable;
       IStmt originalProgram;
    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String,Value> symtbl, MyIList<Value>
            ot, IStmt prg, MyIDictionary<String, BufferedReader> filetbl,MyIHeap heap,MyIProcedureTable procedureTable){
        exeStack=stk;
        symTables=new MySymTablesStack();
        this.symTables.push(symtbl);
        this.procedureTable=procedureTable;
        out = ot;
        filetable=filetbl;
        this.heap=heap;
        this.id=incrementID();
        originalProgram=prg.deepCopy();//recreate the entire original prg
        stk.push(prg);
        program = prg;
    }

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String,Value> symtbl, MyIList<Value>
            ot, MyIDictionary<String, BufferedReader> filetbl,MyIHeap heap,MyIProcedureTable procedureTable){
        exeStack=stk;
        symTables=new MySymTablesStack();
        this.symTables.push(symtbl);
        this.procedureTable=procedureTable;
        out = ot;
        filetable=filetbl;
        this.heap=heap;
        this.id=incrementID();

    }
    public IStmt getStmt(){
        return program;
    }
    public synchronized int incrementID(){
        incID++;
        return incID;
    }

    public int getID(){
        return id;
    }
    public Boolean isNotCompleted(){
        if (exeStack.isEmpty()){
            return false;
        }
        else return true;
    }
    public PrgState oneStep() throws ToyLanguageException{
        if(exeStack.isEmpty()){
            throw new StackIsEmptyException("prgstate stack is empty");}
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }
    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }

    public MyIHeap getHeap() {
        return heap;
    }

    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

//    public MyIDictionary<String, Value> getSymTable() {
//        return symTable;
//    }

    public MyIDictionary<String, Value> getSymTable() {
        return symTables.peek();
    }

        public MyIStack<MyIDictionary<String, Value>> getSymTables() {
        return symTables;
    }

//    public void setSymTable(MyIDictionary<String, Value> symTable) {
//        this.symTable = symTable;
//    }

    public MyIList<Value> getOut() {
        return out;
    }

    public void setOut(MyIList<Value> out) {
        this.out = out;
    }

    public void setHeap(MyIHeap heap) {
        this.heap = heap;
    }
    public void setProcedureTable(MyIProcedureTable procedureTable){
        this.procedureTable=procedureTable;
    }
    public MyIProcedureTable getProcedureTable() {
        return procedureTable;
    }

    //    @Override
//    public String toString() {
//        return "PrgState{" +
//                "" +
//                "exeStack=" + exeStack.getReversed() +
//                ", symTable=" + symTable +
//                ", out=" + out +
//                '}';
//    }
@Override
public String toString() {
    StringBuilder result = new StringBuilder();
   // try {
        result.append("ID:").append(this.id).append("\n");
        result.append("ExeStack:\n");
        result.append(exeStackToString());
        result.append("SymTables:\n");

        result.append(symTableToString());
        result.append("Out:\n");
        result.append(outToString());
        result.append("FileTable:\n");
        result.append(fileTableToString());
        result.append("Heap:\n");
        result.append(heapToString());
    //}
    //catch(ToyLanguageException e){
   //    System.out.println( e.getMessage());
//}

    return result.toString();
}

    public String exeStackToString() {
        StringBuilder result = new StringBuilder();
        for (IStmt stmt : exeStack.getReversed()) {
            result.append(stmt).append("\n");
        }
        return result.toString();
    }

//    public String symTablesToString() throws ToyLanguageException {
//        StringBuilder returnString = new StringBuilder();
//        if (symTables.isEmpty())
//            return returnString.toString() + '\n';
//
//        MyStack<MyIDictionary<String, Value>> stackCopy = new MyStack<>();
//        while (!symTables.isEmpty()) {
//            if (symTables.peek() instanceof IStmt)
//                returnString.append((symTables.peek()).toString()).append('\n');
//            else
//                returnString.append(symTables.peek().toString()).append('\n');
//            stackCopy.push(symTables.pop());
//        }
//        return returnString.toString();
//    }
    public String fileTableToString() {
        StringBuilder result = new StringBuilder();
        for (String filename : filetable.getMap().keySet()) {
            result.append(filename).append("\n");
        }
        return result.toString();
    }
    public String symTableToString() {
        StringBuilder result = new StringBuilder();
        MyIDictionary<String, Value>symTable=symTables.peek();
        for (String var : symTable.getMap().keySet()) {
            try {
                result.append(var).append(" --> ").append(symTable.lookUp(var)).append("\n");
            }
            catch (ToyLanguageException e){
                System.out.println(e);
            }
        }
        return result.toString();
    }

    public String heapToString() {
        StringBuilder result = new StringBuilder();
        for (Integer var : heap.getContent().keySet()) {
            try {
                result.append(var).append(" --> ").append(heap.getValue(var)).append("\n");
            }
            catch (ToyLanguageException e){
                System.out.println(e);
            }
        }
        return result.toString();
    }
    public String procedureTableToString() throws ToyLanguageException {
        StringBuilder procedureTableStringBuilder = new StringBuilder();
        for (String key: procedureTable.getMap().keySet()) {
            procedureTableStringBuilder.append(String.format("%s - %s: %s\n", key, procedureTable.lookUp(key).getKey(), procedureTable.lookUp(key).getValue()));
        }
        procedureTableStringBuilder.append("\n");
        return procedureTableStringBuilder.toString();
    }

    public String outToString() {
        StringBuilder result = new StringBuilder();
        for (Value value : out.getOutput()) {
            result.append(value).append("\n");
        }
        return result.toString();
    }
    public MyIDictionary<String, BufferedReader> getFileTable(){
        return filetable;
    }
}
