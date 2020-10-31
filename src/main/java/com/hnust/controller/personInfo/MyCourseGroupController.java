package com.hnust.controller.personInfo;

import com.hnust.controller.MainController;
import com.hnust.view.subject.SubjectListView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sound.sampled.Line;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class MyCourseGroupController implements Initializable {
    @Autowired
    MainController mainController;
    @Autowired
    SubjectListView subjectListView;
    @FXML
    TableView tableSize;
    @FXML
    ComboBox courseGroup;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableSize.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); //表格宽度自适应

        ObservableList com= FXCollections.observableArrayList(); //下拉项
        com.add("数据结构组");
        com.add("高等数学组");
        com.add("线性代数组");
        courseGroup.setItems(com);
    }

    public void ToSubjectList() throws IOException {  //题库跳转到题目列表
        mainController.skipView(subjectListView);
    }
}
