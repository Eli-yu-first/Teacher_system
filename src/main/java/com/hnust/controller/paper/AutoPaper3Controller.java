package com.hnust.controller.paper;

import com.hnust.controller.MainController;
import com.hnust.controller.paper.component.AddPaperKindController;
import com.hnust.domain.Visual1;
import com.hnust.store.DataTest;
import com.hnust.view.paper.AutoPaper2View;
import com.hnust.view.paper.AutoPaper4View;
import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class AutoPaper3Controller implements Initializable {

    @Autowired
    MainController autoMainController3;
    @Autowired
    AutoPaper2View autoPaper2View;
    @Autowired
    AutoPaper4View autoPaper4View;
    @Autowired
    AutoPaper2Controller autoPaper2Controller;
    @Autowired
    DataTest dataTest;

    //弹窗控制器
    @Autowired
    private AddPaperKindController addPaperKindController;
    //大容器
    @FXML
    public ScrollPane container3;
    //内包小容器
    @FXML
    public AnchorPane contain3;
    //题目列表
    @FXML
    public ScrollPane scp_autoPaper3;
    //题目列表
    @FXML
    public AnchorPane scp_autoPaper_contain3;
    @FXML
    public VBox autoPaper_contain3;
    @FXML
    public VBox autoPaper_contain_list3;
    //容器宽度
    public Double width;

    public void initialize(URL location, ResourceBundle resources) {
        scp_autoPaper3.setFitToWidth(true);
        listenChange();
        showData();
    }

    public void showData() {

    }

    //根据窗口改变，进行监听设置页面大小
    private void listenChange() {
        //通过监听最外层容器的宽度，来改变内层Anchor的宽度
        container3.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                contain3.setPrefWidth((double)newValue-2);
                width=(double)newValue*0.2;
            }
        });
        //通过监听最外层容器的高度，来改变内层Anchor的高度
        container3.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                contain3.setPrefHeight((double)newValue-2);
            }
        });

        //通过监听试卷列表宽度，改变内层Anchor的宽度
        scp_autoPaper3.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                scp_autoPaper_contain3.setPrefWidth((double)newValue-2);
            }
        });
        scp_autoPaper3.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                scp_autoPaper_contain3.setPrefHeight((double)newValue-2);
                scp_autoPaper_contain3.setMaxHeight((double)newValue+1);
            }
        });
    }

    public void toAuto2() throws IOException {
        autoMainController3.skipView(autoPaper2View);
    }

    public void toAuto4() throws IOException {
        autoMainController3.skipView(autoPaper4View);
    }

}
