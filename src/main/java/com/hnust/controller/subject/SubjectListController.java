package com.hnust.controller.subject;

import com.hnust.controller.MainController;
import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableSize.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        container.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                
            }
        });
    }
}
