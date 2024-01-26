package Repository;

import ADT.PrgState;
import Controller.ToyLanguageException;

import java.io.IOError;
import java.io.IOException;
import java.util.List;

public interface IRepository {
   // PrgState getCrtPrg() throws ToyLanguageException;
    void add(PrgState e);
    void emptyFile()throws IOException;
    List<PrgState> getPrgList();

    void setPrgList(List<PrgState> list);
    void logPrgStateExec(PrgState prgState) throws IOException,ToyLanguageException;
}
