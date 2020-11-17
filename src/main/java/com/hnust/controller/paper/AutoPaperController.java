package com.hnust.controller.paper;

import com.hnust.controller.MainController;
import com.hnust.view.paper.AutoPaper2View;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * 创建人：zhangRuWang
 * 创建时间：2020/10/29
 * 描述：
 */
@FXMLController
public class AutoPaperController implements Initializable {
    @Autowired
    MainController autoMainController;
    @Autowired
    AutoPaper2View autoPaper2View;
    @FXML
    ComboBox comboBox;
    @FXML
    RadioButton r1;
    @FXML
    RadioButton r2;

    public Button addQueType;
    public AnchorPane auto1;
    public Button btn;

    @FXML
    private Text target;

    public void initialize(URL location, ResourceBundle resources) {

        ObservableList com= FXCollections.observableArrayList();
        com.add("选择一");
        com.add("选择二");
        com.add("选择三");
        comboBox.setItems(com);

        //将单选框放在一个选择组中
        ToggleGroup toggleGroup=new ToggleGroup();
        r1.setToggleGroup(toggleGroup);
        r2.setToggleGroup(toggleGroup);
    }

    public void toAuto2() throws IOException {
        autoMainController.skipPage(autoPaper2View);
    }
}
