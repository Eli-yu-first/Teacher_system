package com.hnust.controller.paper;

import com.hnust.controller.MainController;
import com.hnust.view.paper.GeneratePaperSecondView;
import com.hnust.view.paper.GeneratePaperThirdView;
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
 * @create: 2020-10-30 17:37
 */
@FXMLController
public class GeneratePaperSecondController implements Initializable {
    @Autowired
    MainController mainController;
    @Autowired
    GeneratePaperThirdView generatePaperThirdView;
    @Autowired
    GeneratePaperView generatePaperView;
    @FXML
    ScrollPane container;
    @FXML
    AnchorPane contain;
    @FXML
    HBox contain_process;
    @FXML
    HBox line1;
    @FXML
    HBox line2;
    @FXML
    ScrollPane scp_paper;
    @FXML
    VBox scp_paper_contain;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        //通过监听试卷列表宽度，改变内层Anchor的宽度
        scp_paper.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                scp_paper_contain.setPrefWidth((double)newValue-2);
            }
        });
        //通过监听试卷列表高度，改变内层Anchor的高度
        scp_paper.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                scp_paper_contain.setPrefHeight((double) newValue-2);
            }
        });
    }
    public void next() throws IOException {
        mainController.skipView(generatePaperThirdView);
    }
    public void back() throws IOException {
        mainController.skipView(generatePaperView);
    }
}
