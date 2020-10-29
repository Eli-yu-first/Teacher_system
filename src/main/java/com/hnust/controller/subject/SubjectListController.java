package com.hnust.controller.subject;

import com.hnust.controller.MainController;
import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class SubjectListController implements Initializable {
    @FXML
    AnchorPane container;
    @FXML
    TableView tableSize;
    @FXML
    ComboBox comb;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableSize.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList com= FXCollections.observableArrayList();
        com.add("选择一");
        com.add("选择二");
        com.add("选择三");
        comb.setItems(com);
    }
}
