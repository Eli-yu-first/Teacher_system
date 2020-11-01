package com.hnust.controller.paper;

import com.hnust.controller.MainController;
import com.hnust.controller.paper.component.AddPaperKindController;
import com.hnust.view.paper.GeneratePaperThirdView;
import com.hnust.view.paper.GeneratePaperView;
import com.hnust.view.paper.component.AddPaperKindView;
import com.hnust.view.paper.component.PaperKindView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * @program: demo
 * @author: 彭鑫淼
 * @description: 未知
 * @create: 2020-10-30 17:37
 */
@FXMLController
public class GeneratePaperSecondController implements Initializable {
    @Autowired
    private MainController mainController;
    @Autowired
    private GeneratePaperThirdView generatePaperThirdView;
    @Autowired
    private GeneratePaperView generatePaperView;
    @Autowired
    private AddPaperKindView addPaperKindView;
    //题目标题
    @Autowired
    private PaperKindView paperKindView;
    //弹窗控制器
    @Autowired
    private AddPaperKindController addPaperKindController;
    //大容器
    @FXML
    public ScrollPane container;
    //内包小容器
    @FXML
    public AnchorPane contain;
    //进度条
    @FXML
    public HBox contain_process;
    //左线条
    @FXML
    public HBox line1;
    //右线条
    @FXML
    public HBox line2;
    //题目列表
    @FXML
    public ScrollPane scp_paper;
    //题目列表
    @FXML
    public AnchorPane scp_paper_contain;
    @FXML
    public VBox paper_contain;
    //容器宽度
    public Double width;
    //添加弹窗
    public Dialog dialog=null;

    @FXML
    ComboBox comb;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scp_paper.setFitToWidth(true);
        listenChange();
    }
    //根据窗口改变，进行监听设置页面大小
    public void listenChange(){
        //通过监听最外层容器的宽度，来改变内层Anchor的宽度
        container.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                contain.setPrefWidth((double)newValue-2);
                width=(double)newValue*0.2;
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
                scp_paper_contain.setMaxHeight((double)newValue+1);
            }
        });
        scp_paper.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                scp_paper_contain.setPrefHeight((double)newValue-2);
            }
        });
    }
    //添加题型
    public void addPaperKind(){
        dialog=new Dialog();
        DialogPane dialogPane=new DialogPane();
        dialogPane.setStyle("-fx-background-color: #FFF;-fx-border-width: 2;-fx-border-color: #ADADAD");
        dialogPane.setContent(addPaperKindView.getView());
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("题型添加");
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.setGraphic(null);
        ButtonType ok=new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel=new ButtonType("取消", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(ok,cancel);
        Optional<ButtonType> result=dialog.showAndWait();
        if(result.get()==ok){
            System.out.println(addPaperKindController.getKind());
            addKind(addPaperKindController.getKind());
        }
    }
    //下一页
    public void next() throws IOException {
        mainController.skipView(generatePaperThirdView);
    }
    //上一页
    public void back() throws IOException {
        mainController.skipView(generatePaperView);
    }
    //添加题型
    public void addKind(String kind){
        Parent parent=paperKindView.getView();
        parent.setId(UUID.randomUUID().toString().substring(0, 3));
        paper_contain.getChildren().add(parent);
    }
}
