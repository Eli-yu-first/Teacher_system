package com.hnust.controller.student;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class DetailDataController implements Initializable {
    @FXML
    AnchorPane detail;
    @FXML
    VBox vBoxId;
    @FXML
    TableView tableDetail;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*表格自适应*/
        tableDetail.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
}
