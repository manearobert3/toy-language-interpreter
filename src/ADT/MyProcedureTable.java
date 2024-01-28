package ADT;

import Controller.KeyNotFoundInDictionary;
import Controller.ToyLanguageException;
import Stmts.IStmt;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public class MyProcedureTable extends MyDictionary<String, Pair<List<String>, IStmt>> implements MyIProcedureTable{

}
