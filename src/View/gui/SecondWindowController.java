package View.gui;

import ADT.*;
import Controller.Controller;
import Stmts.CompStmt;
import Stmts.IStmt;
import Values.Value;
import Controller.ToyLanguageException;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import Controller.StackIsEmptyException;
import javafx.util.Pair;
import Controller.KeyNotFoundInDictionary;

public class SecondWindowController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ListView<String> ExeStackListView;
    @FXML
    private ListView<String> FileTableListView;

    @FXML
    private ListView<String> OutListView;


    @FXML
    private TableColumn<Pair<String, String>, String> heapAdress;

    @FXML
    private TableColumn<Pair<String, String>, String> heapValue;
    @FXML
    private TableView<Pair<String,String>> HeapTable ;

    @FXML
    private Button runOneStepButton;

    @FXML
    private TextField NrPrgStates;
    private List<IStmt> listOfExamples;
    private List<Controller> listOfControllers;
    private int numberOfExample;
    @FXML
    private Text nameOfExample;
    private Controller currentController;
    private Controller originalController;
    private IStmt currentExample;


    @FXML
    private TableColumn<BarrierTable, String> indexSem;

    @FXML
    private TableColumn<BarrierTable, String> listSem;
    @FXML
    private TableView<BarrierTable> toySemaphoreTable;
    @FXML
    private TableColumn<BarrierTable, String> valueSem;


    @FXML
    private ListView<Integer> ListPrgStatesIdent;
    ObservableList<Pair<String,String>> listHeap= FXCollections.observableArrayList();
    ObservableList<Pair<String,String>> listSymTable= FXCollections.observableArrayList();
    @FXML
    private TableView<Pair<String,String>> symTable;

    @FXML
    private TableColumn<Pair<String,String>,String> symTableValue;

    @FXML
    private TableColumn<Pair<String,String>,String> varName;


    @FXML
    private Button RunSelectedPrgStateButton;

    private PrgState currentPrgState;

    public SecondWindowController() {
    }

    @FXML
    void changeController(ActionEvent event) throws StackIsEmptyException {
        originalController=currentController;
        int numberOfController=ListPrgStatesIdent.getSelectionModel().getSelectedItem();
        for(int i=0;i<currentController.getPrgList().size();i++){
            if(currentController.getPrgList().get(i).getID()==numberOfController)
            {
                currentPrgState=currentController.getPrgList().get(i);
            }

        }
        populateSymTable();
        OutAndFileTable();
        EditExeStack();
        setNrPrgStates();
        populateListViewPrgStsIdent();
        populateHeapTable();
    }
    @FXML
    public void initialize() {
        RunSelectedPrgStateButton.disableProperty().bind(
                Bindings.isNull(ListPrgStatesIdent.getSelectionModel().selectedItemProperty())
        );
        NrPrgStates.setEditable(false);
        heapAdress.setCellValueFactory(cellData->new javafx.beans.property.SimpleStringProperty(cellData.getValue().getKey()));
        heapValue.setCellValueFactory(cellData->new javafx.beans.property.SimpleStringProperty(cellData.getValue().getValue()));
        symTableValue.setCellValueFactory(cellData->new javafx.beans.property.SimpleStringProperty(cellData.getValue().getKey()));
        varName.setCellValueFactory(cellData->new javafx.beans.property.SimpleStringProperty(cellData.getValue().getValue()));

        toySemaphoreTable.getColumns().clear();
        indexSem.setCellValueFactory(new PropertyValueFactory<>("index"));
        valueSem.setCellValueFactory(new PropertyValueFactory<>("value"));
        listSem.setCellValueFactory(new PropertyValueFactory<>("listOfValues"));
        toySemaphoreTable.getColumns().addAll(indexSem, valueSem, listSem);

    }
    public void switchToPrimary(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("window1.fxml"));
        root = loader.load();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updateSemaphore() throws IOException {
        toySemaphoreTable.getItems().clear();
        Map<Integer,Triplet> newMap = currentPrgState.getToySemaphoreTable().getContent();
        for (Map.Entry<Integer, Triplet> k : currentPrgState.getToySemaphoreTable().getContent().entrySet()) {
            String key = k.getKey().toString();
            Triplet value = k.getValue();
            Integer first = value.first;
            List<Integer> second = value.second;
            Integer third = value.third;

            toySemaphoreTable.getItems().add(new BarrierTable(first,third,second));
        }

}

    public void setNumber(String exampleString, List<IStmt> listOfExamples, List<Controller>listOfControllers) throws IOException, InterruptedException, ToyLanguageException {
        RunSelectedPrgStateButton.disableProperty().bind(
                Bindings.isNull(ListPrgStatesIdent.getSelectionModel().selectedItemProperty())
        );
        NrPrgStates.setEditable(false);
        String nameAndNumber = null;
        this.listOfControllers=listOfControllers;
        this.listOfExamples=listOfExamples;
        for(int i=0;i<listOfExamples.size();i++)
        {
            if (listOfExamples.get(i).toString().equals(exampleString)) {
                currentExample =listOfExamples.get(i);
                currentController=listOfControllers.get(i);
                numberOfExample=i;
                nameAndNumber = String.valueOf(i);
            }
        }
        nameAndNumber=nameAndNumber.concat(": ");

        nameAndNumber=nameAndNumber.concat(exampleString);
        nameOfExample.setText(nameAndNumber);
        currentPrgState=currentController.getPrgList().get(0);


        populateHeapTable();
        OutAndFileTable();
        HeapTable.refresh();
        symTable.refresh();
        symTable.setItems(listSymTable);
        HeapTable.setItems(listHeap);

    }


    public void populateSymTable(){
        listSymTable.clear();
        for (Map.Entry<String, Value> entry : currentPrgState.getSymTable().getMap().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().toString();

            listSymTable.add(new Pair<>(value,String.valueOf(key)));
        }

        symTable.refresh();
    }
    public void populateHeapTable(){
        listHeap.clear();
        for (Map.Entry<Integer, Value> entry : currentPrgState.getHeap().getHeap().entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue().toString();

            listHeap.add(new Pair<>(String.valueOf(key),value));
        }

        HeapTable.refresh();
    }

    public void OutAndFileTable(){
        FileTableListView.getItems().clear();
        OutListView.getItems().clear();
        OutListView.getItems().add(currentPrgState.outToString());
        FileTableListView.getItems().add(currentPrgState.fileTableToString());
    }


    @FXML
    void runOneStepFunc(ActionEvent event) throws InterruptedException, IOException {
        try {
            currentController.oneStep();
            populateSymTable();
            OutAndFileTable();
            EditExeStack();
            setNrPrgStates();
            populateListViewPrgStsIdent();
            populateHeapTable();
            updateSemaphore();

        }catch(ToyLanguageException e)
        {Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage()); alert.showAndWait();};

        HeapTable.refresh();

    }

    void setNrPrgStates(){
        NrPrgStates.setText(String.valueOf(currentController.getPrgList().size()));
    }

    @FXML
    void EditExeStack() {
        try {
            ExeStackListView.getItems().clear();
            MyIStack newStack = currentPrgState.getExeStack().deepCopy();

            while (!newStack.isEmpty()) {
                IStmt stmt = (IStmt) newStack.pop();

                if (stmt instanceof CompStmt) {
                    // If the statement is a CompStmt, add its components separately
                    CompStmt compStmt = (CompStmt) stmt;
                    newStack.push(compStmt.getSnd());
                    newStack.push(compStmt.getFirst());
                } else {
                    // If the statement is not a CompStmt, add it directly
                    ExeStackListView.getItems().add(stmt.toString());
                }
            }
        }catch (StackIsEmptyException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage()); alert.showAndWait();
        }
    }

    void populateListViewPrgStsIdent(){
        ListPrgStatesIdent.getItems().clear();
        Integer theSize=currentController.getPrgList().size();
        for(int i=0;i<theSize;i++){
            ListPrgStatesIdent.getItems().add(currentController.getPrgList().get(i).getID());
        }
    }

}
