package com.hnust.controller.subject;

import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class BatchImportController implements Initializable {
    private ObservableList playListFiles =FXCollections.observableArrayList();
    @FXML
    AnchorPane container;
   // @FXML
   // Button btn1;
   // @FXML
    //Button btn2;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    public void chooseFile(ActionEvent event) {
        FileChooser fileChooser=new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Text Files", "*.txt");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
    }
    public void downFile(){
        System.out.println("hello world!");
    }
    
}
