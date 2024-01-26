package Repository;

import ADT.PrgState;
import Controller.ToyLanguageException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class Repository implements IRepository{

    List<PrgState>  repo;
    private final String logFilePath;

    public Repository(PrgState prgState,String logFilePath){
        this.repo = new LinkedList <PrgState>();
        this.logFilePath=logFilePath;
        this.repo.add(prgState);
        this.emptyFile();
    }
    @Override
    public void add(PrgState e){
        repo.add(e);
    }

    @Override
    public void logPrgStateExec(PrgState prgState) throws IOException, ToyLanguageException {
        PrintWriter logFile;
        logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.println(prgState.toString());
        logFile.close();
    }
    @Override
    public List<PrgState> getPrgList() {
        return repo;
    }

    @Override
    public void setPrgList(List<PrgState> list) {
        repo = list;
    }

    @Override
    public void emptyFile(){
        try {
            PrintWriter file;
            file = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
            file.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }

//    @Override
//    public PrgState getCrtPrg() throws ToyLanguageException {
//        if(this.repo.isEmpty())
//            throw new ToyLanguageException("There is nothing else to execute");
//        return repo.get(0);
//    }

    @Override
    public String toString() {
        return "Repository{" +
                "repo=" + repo +
                '}';
    }
}
