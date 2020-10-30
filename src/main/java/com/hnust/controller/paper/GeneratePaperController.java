package com.hnust.controller.paper;

import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class GeneratePaperController implements Initializable {
    @FXML
    HBox container;
    @FXML
    HBox line1;
    @FXML
    HBox line2;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        container.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                line1.setPrefWidth((double)newValue*0.15);
                line2.setPrefWidth((double)newValue*0.15);
            }
        });
    }
}
