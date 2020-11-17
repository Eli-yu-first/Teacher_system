package com.hnust.controller.paper;

import com.hnust.controller.MainController;
import com.hnust.controller.paper.component.AddPaperKindController;
import com.hnust.domain.QuestionType;
import com.hnust.domain.SubjectDataRecord;
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
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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

    //试卷表格
    public void showData(){
        QuestionType questionType = new QuestionType(1,"yy2y3y");
        QuestionType questionType1 = new QuestionType(2,"yy4yy");
        QuestionType questionType2 = new QuestionType(3,"y2y5yy");
        QuestionType questionType3 = new QuestionType(4,"yyy7y");
        QuestionType questionType4 = new QuestionType(5,"yyy8y");
        ObservableList list=FXCollections.observableArrayList();
        list.addAll(questionType,questionType1,questionType2,questionType3,questionType4);
        ListView<QuestionType> view = new ListView<>();
        view.setItems(list);
        view.setPrefHeight((double)(210*list.size()));
        view.setStyle("-fx-fixed-cell-size:210");
        autoPaper_contain3.getChildren().add(view);
        view.setCellFactory(new Callback<ListView<QuestionType>, ListCell<QuestionType>>() {
            @Override
            public ListCell<QuestionType> call(ListView<QuestionType> param) {
                ListCell<QuestionType> cell=new ListCell<QuestionType>(){
                    @Override
                    protected void updateItem(QuestionType item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty==false){
                            Label label=new Label(item.getName());
                            Label label2=new Label(item.getId().toString());
                            VBox vBox = new VBox(label,label2);

                            this.setGraphic(vBox);
//                            AnchorPane anchorPane=new AnchorPane();
//                            Label label1=new Label("题目类型: ");
//                            Label label = new Label();
//                            label1.getStyleClass().add("question");
//                            label1.setWrapText(true);
//                            label1.setMaxHeight(180);
//
//                            VBox vBox=new VBox(label1);
//                            vBox.setMaxWidth(width);
//                            vBox.setSpacing(10);
//                            AnchorPane.setTopAnchor(vBox, 15.0);
//                            AnchorPane.setLeftAnchor(vBox, 50.0);
//                            AnchorPane.setRightAnchor(vBox, 125.0);
//                            anchorPane.getChildren().addAll(vBox);
//                            anchorPane.setMaxHeight(150);
//                            anchorPane.setPrefHeight(150);
//                            this.setGraphic(anchorPane);
                        }else{
                            this.setGraphic(null);
                        }
                    }
                };
                return cell;
            }
        });
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
        autoMainController3.skipPage(autoPaper2View);
    }

    public void toAuto4() throws IOException {
        autoMainController3.skipPage(autoPaper4View);
    }

}
