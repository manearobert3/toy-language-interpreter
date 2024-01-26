package View.gui;

import Controller.Controller;
import Stmts.IStmt;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.util.ArrayList;
import View.initialiseExamples;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import Controller.ToyLanguageException;
public class FirstWindowController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private List<IStmt> listOfExamples;
    private List<Controller> listOfControllers;
    private List<String> listOfErrors;

    @FXML
    private ListView<String> MyExamples;

    @FXML
    private Button RunExample;

//    public void switchToPrimary(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("window1.fxml"));
//        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
//        scene= new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }

    public void switchToScene2(ActionEvent event) throws IOException, InterruptedException, ToyLanguageException {
        String example = MyExamples.getSelectionModel().getSelectedItem();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("window2 - Copy.fxml"));
        root = loader.load();
        SecondWindowController window2Controller = loader.getController();
        window2Controller.setNumber(example,listOfExamples,listOfControllers);
       //Parent root = FXMLLoader.load(getClass().getResource("window2.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RunExample.disableProperty().bind(
                Bindings.isNull(MyExamples.getSelectionModel().selectedItemProperty())
        );
try {
    initialiseExamples.start();

}catch(ToyLanguageException e)
{
    Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage()); alert.showAndWait();
}
            listOfExamples = initialiseExamples.returnListOfExamples();
            listOfControllers = initialiseExamples.returnListOfControllers();
            listOfErrors = initialiseExamples.returnListOfErrors();
        List<String> examples = new ArrayList<>();

            for(int i=0;i<listOfExamples.size();i++)
                examples.add(listOfExamples.get(i).toString());
            MyExamples.getItems().addAll(examples);
            for(int i =0;i<listOfErrors.size();i++)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING, listOfErrors.get(i)); alert.showAndWait();
            }
    }
}
