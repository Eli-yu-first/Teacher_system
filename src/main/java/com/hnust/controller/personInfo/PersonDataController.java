package com.hnust.controller.personInfo;

import com.hnust.controller.MainController;
import com.hnust.controller.paper.component.EditInfoController;
import com.hnust.view.paper.component.EditInfoView;
import com.hnust.view.personInfo.MyCourseGroupView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

@FXMLController
public class PersonDataController implements Initializable {
    @Autowired
    MainController mainController;
    @Autowired
    MyCourseGroupView myCourseGroupView;
    @Autowired
    private EditInfoView editInfoView;
    //弹窗控制器r
    @Autowired
    private EditInfoController editInfoController;
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
    //添加弹窗
    public Dialog dialog=null;
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
        teacherPhone.setText("18873243259");
        teacherMail.setText("2262574607@qq.com");
    }

    public void EditInfo() throws IOException{

        dialog=new Dialog();
        DialogPane dialogPane=new DialogPane();
        dialogPane.setStyle("-fx-background-color: #FFF;-fx-border-width: 2;-fx-border-color: #ADADAD");
        dialogPane.setPrefHeight(250);
        dialogPane.setContent(editInfoView.getView());
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("信息修改");
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.setGraphic(null);
        ButtonType ok=new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel=new ButtonType("取消", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(ok,cancel);
        Optional<ButtonType> result=dialog.showAndWait();
        if(result.get()==ok) {
            Map<String,String> a = editInfoController.aaa();
            teacherPhone.setText(a.get("phone"));
            teacherMail.setText(a.get("mail"));
            System.out.println(a+"---------------------");
        }
    }

     public void check()throws IOException{
        mainController.skipView(myCourseGroupView);
}




}
