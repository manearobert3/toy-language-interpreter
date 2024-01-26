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
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    MyIHeap heap;
    IStmt program;
    private final MyIDictionary<String, BufferedReader> filetable;
       // IStmt originalProgram;
    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String,Value> symtbl, MyIList<Value>
            ot, IStmt prg, MyIDictionary<String, BufferedReader> filetbl,MyIHeap heap){
        exeStack=stk;
        symTable=symtbl;
        out = ot;
        filetable=filetbl;
        this.heap=heap;
        this.id=incrementID();
        //originalProgram=deepCopy(prg);//recreate the entire original prg
        stk.push(prg);
        program = prg;
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

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyIDictionary<String, Value> symTable) {
        this.symTable = symTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public void setOut(MyIList<Value> out) {
        this.out = out;
    }

    public void setHeap(MyIHeap heap) {
        this.heap = heap;
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
    result.append("ID:").append(this.id).append("\n");
    result.append("ExeStack:\n");
    result.append(exeStackToString());
    result.append("SymTable:\n");
    result.append(symTableToString());
    result.append("Out:\n");
    result.append(outToString());
    result.append("FileTable:\n");
    result.append(fileTableToString());
    result.append("Heap:\n");
    result.append(heapToString());

    return result.toString();
}

    public String exeStackToString() {
        StringBuilder result = new StringBuilder();
        for (IStmt stmt : exeStack.getReversed()) {
            result.append(stmt).append("\n");
        }
        return result.toString();
    }
    public String fileTableToString() {
        StringBuilder result = new StringBuilder();
        for (String filename : filetable.getMap().keySet()) {
            result.append(filename).append("\n");
        }
        return result.toString();
    }
    public String symTableToString() {
        StringBuilder result = new StringBuilder();
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
