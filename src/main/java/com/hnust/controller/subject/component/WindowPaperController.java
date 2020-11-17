package com.hnust.controller.subject.component;

import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @program: demo
 * @author: 彭鑫淼
 * @description: 未知
 * @create: 2020-11-01 12:51
 */
@FXMLController
public class WindowPaperController implements Initializable {
    @FXML
    RadioButton r1;
    @FXML
    RadioButton r2;
    @FXML
    RadioButton r3;
    @FXML
    ComboBox comb1;
    @FXML
    ComboBox comb2;
    @FXML
    ComboBox comb3;
    @FXML
    ComboBox comb4;
    @FXML
    ComboBox comb5;
    private String kind="选择题";
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comb1.getItems().addAll("选择题","判断题","应用题");
        comb1.getSelectionModel().select(0);
        comb1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                kind= (String) newValue;
            }
        });
        comb2.getItems().addAll("选择题","判断题","应用题");
        comb3.getItems().addAll("选择题","判断题","应用题");
        comb4.getItems().addAll("选择题","判断题","应用题");
        comb5.getItems().addAll("选择题","判断题","应用题");



        //将单选框放在一个选择组中
        ToggleGroup toggleGroup=new ToggleGroup();
        r1.setToggleGroup(toggleGroup);
        r2.setToggleGroup(toggleGroup);
        r3.setToggleGroup(toggleGroup);
    }
    public String getKind() {
        return kind;
    }


}
