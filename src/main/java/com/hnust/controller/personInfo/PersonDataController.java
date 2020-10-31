package com.hnust.controller.personInfo;

import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

//import javax.swing.text.html.ListView;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class PersonDataController implements Initializable {
    @FXML
    AnchorPane container;

    @FXML
    ListView CourseGroupListView;
    private ObservableList<String> dataList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CourseGroupListView.setItems(dataList);
        dataList.add("数据结构组");
        dataList.add("高等数学组");
        dataList.add("数据结构组");
        dataList.add("离散数学组");
    }

}
