package com.hnust.controller.paper;

import com.hnust.controller.paper.component.AddPaperKindController;
import com.hnust.domain.Visual1;
import com.hnust.view.paper.AutoPaper3View;
import com.hnust.view.paper.component.AddPaperKindView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import com.hnust.controller.MainController;
import de.felixroske.jfxsupport.FXMLController;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@FXMLController
public class AutoPaper2Controller implements Initializable {

    @Autowired
    MainController autoMainController2;
    @Autowired
    AutoPaper3View autoPaper3View;
    @Autowired
    private AddPaperKindView addPaperKindView;
    //弹窗控制器
    @Autowired
    private AddPaperKindController addPaperKindController;
    @FXML
    public VBox autoPaper_contain;
    //题目列表
    @FXML
    public AnchorPane scp_autoPaper_contain;
    //题目列表
    @FXML
    public ScrollPane scp_autoPaper;

    //大容器
    @FXML
    public ScrollPane container;
    //内包小容器
    @FXML
    public AnchorPane contain;
    //添加弹窗
    public Dialog dialog=null;
    public String type = null;

    //容器宽度
    public Double width;

    //添加选择题型，选择题所有组件
    public VBox vBox_chose;
    public ListView listView_chose;
    public Label easy_chose;
    public TextField easy_num_chose;
    public Label mid_chose;
    public TextField mid_num_chose;
    public Label diff_chose;
    public TextField diff_num_chose;
    //添加判断题型，选择题所有组件
    public VBox vBox_judge;
    public ListView listView_judge;
    public Label easy_judge;
    public TextField easy_num_judge;
    public Label mid_judge;
    public TextField mid_num_judge;
    public Label diff_judge;
    public TextField diff_num_judge;
    //添加简答题型，选择题所有组件
    public VBox vBox_short;
    public ListView listView_short;
    public Label easy_short;
    public TextField easy_num_short;
    public Label mid_short;
    public TextField mid_num_short;
    public Label diff_short;
    public TextField diff_num_short;
    public Button btn_short;

    //获取选择题的较易题目的个数
    public String choice_easyNum = "0";
    //获取选择题中等题目的个数
    public String choice_midNum = "0";
    //获取选择题较难的题目个数
    public String choice_diffNum = "0";
    //获取判断题目的较易题目的个数
    public String judge_easyNum = "0";
    //获取选择题中等题目的个数
    public String judge_midNum = "0";
    // 获取选择题较难的题目个数
    public String judge_diffNum = "0";
    //获取简答题目的较易题目的个数
    public String short_easyNum = "0";
    //获取简答题中等题目的个数
    public String short_midNum = "0";
    //获取简答题较难的题目个数
    public String short_diffNum = "0";

    public void initialize(URL location, ResourceBundle resources) {
        scp_autoPaper.setFitToWidth(true);
        listenChange();
    }

    //根据窗口改变，进行监听设置页面大小
    private void listenChange() {
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

        //通过监听试卷列表宽度，改变内层Anchor的宽度
        scp_autoPaper.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                scp_autoPaper_contain.setPrefWidth((double)newValue-2);
            }
        });
        scp_autoPaper.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                scp_autoPaper_contain.setPrefHeight((double)newValue-2);
                scp_autoPaper_contain.setMaxHeight((double)newValue+1);
            }
        });
    }

    public void toAuto3() throws IOException {
        autoMainController2.skipView(autoPaper3View);

        try {
            //获取选择题的个数
            choice_easyNum = easy_num_chose.getText();
            choice_midNum = mid_num_chose.getText();
            choice_diffNum = diff_num_chose.getText();
            //获取判断题的个数.
            judge_easyNum = easy_num_judge.getText();
            judge_midNum = mid_num_judge.getText();
            judge_diffNum = diff_num_judge.getText();
            //获取简答题的个数
            short_easyNum = easy_num_short.getText();
            short_midNum = mid_num_short.getText();
            short_diffNum = diff_num_short.getText();

        }catch (Exception e) {
            System.out.println("出错啦！！！");

        }


        /*
        System.out.println(choice_easyNum);
        System.out.println(choice_midNum);
        System.out.println(choice_diffNum);
        System.out.println(judge_easyNum);
        System.out.println(judge_midNum);
        System.out.println(judge_diffNum);
        System.out.println(short_easyNum);
        System.out.println(short_midNum);
        System.out.println(short_diffNum);*/

    }

    //“添加题型按钮的点击事件”
    public void addAutoType() {
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
            //获取选择的信息，赋值给type
            type = addPaperKindController.getKind();
            addKind(type);
        }
    }
    //添加题型
    public void addKind(String type){
        if("选择题".equals(type)&&vBox_chose==null){
            showData1();
        }
        if("判断题".equals(type)&&vBox_judge==null){
            showData2();
        }
        if("简答题".equals(type)&&vBox_short==null){
            showData3();
        }
    }

    public void showData1(){
        listView_chose = new ListView();
        listView_chose.setPrefHeight(0);
        vBox_chose = new VBox(listView_chose);
        autoPaper_contain.getChildren().add(vBox_chose);
        Visual1 v1=new Visual1("1","2",1);

        ObservableList list= FXCollections.observableArrayList();
        list.addAll(v1);
        listView_chose.setItems(list);
        listView_chose.setPrefHeight(200);
        listView_chose.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView param) {
                ListCell cell=new ListCell(){
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty==false){
                            AnchorPane anchorPane=new AnchorPane();

                            Label label1=new Label("题目类型: " + type);
                            label1.getStyleClass().add("question");
                            label1.setWrapText(true);
                            label1.setMaxHeight(180);

                            //输入较易的题目类型个数
                            easy_chose=new Label("较易:  ");
                            easy_num_chose=new TextField();
                            easy_num_chose.setMaxWidth(120);
                            //输入中等的题目类型个数
                            mid_chose=new Label("中等:  ");
                            mid_num_chose=new TextField();
                            mid_num_chose.setMaxWidth(120);
                            //输入较难的题目类型个数
                            diff_chose=new Label("较难:  ");
                            diff_num_chose=new TextField();
                            diff_num_chose.setMaxWidth(120);

                            HBox hBox = new HBox(easy_chose,easy_num_chose);
                            HBox hBox1 = new HBox(mid_chose,mid_num_chose);
                            HBox hBox2 = new HBox(diff_chose,diff_num_chose);
                            VBox vBox=new VBox(label1,hBox,hBox1,hBox2);
                            vBox.setMaxWidth(width);
                            vBox.setSpacing(10);
                            AnchorPane.setTopAnchor(vBox, 15.0);
                            AnchorPane.setLeftAnchor(vBox, 50.0);
                            AnchorPane.setRightAnchor(vBox, 125.0);
                            anchorPane.getChildren().addAll(vBox);
                            anchorPane.setMaxHeight(150);
                            anchorPane.setPrefHeight(150);
                            this.setGraphic(anchorPane);
                        }else{
                            this.setGraphic(null);
                        }
                    }
                };
                return cell;
            }
        });
    }

    public void showData2(){
        listView_judge = new ListView();
        listView_judge.setPrefHeight(0);
        vBox_judge = new VBox(listView_judge);
        autoPaper_contain.getChildren().add(vBox_judge);
        Visual1 v1=new Visual1("1","2",1);

        ObservableList list= FXCollections.observableArrayList();
        list.addAll(v1);
        listView_judge.setItems(list);
        listView_judge.setPrefHeight(200);
        listView_judge.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView param) {
                ListCell cell=new ListCell(){
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty==false){
                            AnchorPane anchorPane=new AnchorPane();

                            Label label1=new Label("题目类型: " + type);
                            label1.getStyleClass().add("question");
                            label1.setWrapText(true);
                            label1.setMaxHeight(180);

                            //输入较易的题目类型个数
                            easy_judge=new Label("较易:  ");
                            easy_num_judge=new TextField();
                            easy_num_judge.setMaxWidth(120);
                            //输入中等的题目类型个数
                            mid_judge=new Label("中等:  ");
                            mid_num_judge=new TextField();
                            mid_num_judge.setMaxWidth(120);
                            //输入较难的题目类型个数
                            diff_judge=new Label("较难:  ");
                            diff_num_judge=new TextField();
                            diff_num_judge.setMaxWidth(120);

                            HBox hBox = new HBox(easy_judge,easy_num_judge);
                            HBox hBox1 = new HBox(mid_judge,mid_num_judge);
                            HBox hBox2 = new HBox(diff_judge,diff_num_judge);
                            VBox vBox=new VBox(label1,hBox,hBox1,hBox2);
                            vBox.setMaxWidth(width);
                            vBox.setSpacing(10);
                            AnchorPane.setTopAnchor(vBox, 15.0);
                            AnchorPane.setLeftAnchor(vBox, 50.0);
                            AnchorPane.setRightAnchor(vBox, 125.0);
                            anchorPane.getChildren().addAll(vBox);
                            anchorPane.setMaxHeight(150);
                            anchorPane.setPrefHeight(150);
                            this.setGraphic(anchorPane);
                        }else{
                            this.setGraphic(null);
                        }
                    }
                };
                return cell;
            }
        });
    }

    public void showData3(){
        listView_short=new ListView();
        listView_short.setPrefHeight(0);
        vBox_short=new VBox(listView_short);
        autoPaper_contain.getChildren().add(vBox_short);

        Visual1 v1=new Visual1("1","2",1);

        ObservableList list= FXCollections.observableArrayList();
        list.addAll(v1);
        listView_short.setItems(list);
        listView_short.setPrefHeight(200);
        listView_short.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView param) {
                ListCell cell=new ListCell(){
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty==false){
                            AnchorPane anchorPane=new AnchorPane();

                            Label label1=new Label("题目类型: " + type);
                            label1.getStyleClass().add("question");
                            label1.setWrapText(true);
                            label1.setMaxHeight(180);

                            //输入较易的题目类型个数
                            easy_short=new Label("较易:  ");
                            easy_num_short=new TextField();
                            easy_num_short.setMaxWidth(120);
                            //输入中等的题目类型个数
                            mid_short=new Label("中等:  ");
                            mid_num_short=new TextField();
                            mid_num_short.setMaxWidth(120);
                            //输入较难的题目类型个数
                            diff_short=new Label("较难:  ");
                            diff_num_short=new TextField();
                            diff_num_short.setMaxWidth(120);

                            HBox hBox = new HBox(easy_short,easy_num_short);
                            HBox hBox1 = new HBox(mid_short,mid_num_short);
                            HBox hBox2 = new HBox(diff_short,diff_num_short);
                            VBox vBox=new VBox(label1,hBox,hBox1,hBox2);
                            vBox.setMaxWidth(width);
                            vBox.setSpacing(10);
                            AnchorPane.setTopAnchor(vBox, 15.0);
                            AnchorPane.setLeftAnchor(vBox, 50.0);
                            AnchorPane.setRightAnchor(vBox, 125.0);
                            anchorPane.getChildren().addAll(vBox);
                            anchorPane.setMaxHeight(150);
                            anchorPane.setPrefHeight(150);
                            this.setGraphic(anchorPane);
                        }else{
                            this.setGraphic(null);
                        }
                    }
                };
                return cell;
            }
        });
    }
}
