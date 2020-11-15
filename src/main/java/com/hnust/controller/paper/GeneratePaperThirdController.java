package com.hnust.controller.paper;

import com.hnust.controller.MainController;
import com.hnust.store.GeneratePaperDataStore;
import com.hnust.view.paper.GeneratePaperSecondView;
import com.hnust.view.paper.GeneratePaperView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @program: demo
 * @author: 彭鑫淼
 * @description: 未知
 * @create: 2020-10-30 17:53
 */
@FXMLController
public class GeneratePaperThirdController implements Initializable {
    @Autowired
    private MainController mainController;
    @Autowired
    private GeneratePaperView generatePaperView;
    @Autowired
    private GeneratePaperSecondView generatePaperSecondView;
    @Autowired
    private GeneratePaperSecondController generatePaperSecondController;
    @Autowired
    private GeneratePaperController generatePaperController;
    @FXML
    public ScrollPane container;
    @FXML
    public AnchorPane contain;
    @FXML
    public HBox contain_process;
    @FXML
    public HBox line1;
    @FXML
    public HBox line2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listenChange();//页面大小
    }
    //根据窗口改变，进行监听设置页面大小
    public void listenChange(){
        container.setFitToWidth(true);
        //通过监听最外层容器的宽度，来改变内层Anchor的宽度
        container.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                contain.setPrefWidth((double)newValue-2);
            }
        });
        //通过监听最外层容器的高度，来改变内层Anchor的高度
        container.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                contain.setPrefHeight((double)newValue-2);
            }
        });
        //设置线条的长度
        contain_process.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                line1.setPrefWidth((double)newValue*0.15);
                line2.setPrefWidth((double)newValue*0.15);
            }
        });
    }
    public void next() throws IOException {
        mainController.skipView(generatePaperView);
        generatePaperSecondController.clear();
        generatePaperController.clearData();
    }
    public void back() throws IOException{
        mainController.skipView(generatePaperSecondView);
    }
}
