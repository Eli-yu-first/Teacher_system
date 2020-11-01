package com.hnust.controller.paper.component;

import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @program: demo
 * @author: 彭鑫淼
 * @description: 未知
 * @create: 2020-11-01 12:51
 */
@FXMLController
public class AddPaperKindController implements Initializable {
    @FXML
    ComboBox comb;
    private String kind="选择题";
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comb.getItems().addAll("选择题","判断题","简答题");
        comb.getSelectionModel().select(0);
        comb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                kind= (String) newValue;
            }
        });
    }

    public String getKind() {
        return kind;
    }
}
