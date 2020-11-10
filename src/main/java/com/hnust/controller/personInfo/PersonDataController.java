package com.hnust.controller.personInfo;

import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class PersonDataController implements Initializable {
    @FXML
    AnchorPane container;
    @FXML
    public TextField collegeName;
    @FXML
    public TextField teacherNumber;
    @FXML
    public TextField teacherName;
    @FXML
    public TextField teacherPhone;
    @FXML
    public TextField teacherMail;
    @FXML
    ListView CourseGroupListView;
    private ObservableList<String> dataList = FXCollections.observableArrayList();

    public TextField college;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CourseGroupListView.setItems(dataList);
        dataList.add("数据结构组");
        dataList.add("高等数学组");
        dataList.add("数据结构组");
        dataList.add("离散数学组");


        collegeName.setText("计算机科学与工程学院");
        teacherNumber.setText("1805010304");
        teacherName.setText("刘正星");
        teacherPhone.setText("18813243259");
        teacherMail.setText("2262574607@qq.com");
    }





}
