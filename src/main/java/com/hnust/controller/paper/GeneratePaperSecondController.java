package com.hnust.controller.paper;

import com.hnust.controller.MainController;
import com.hnust.controller.paper.component.AddPaperKindController;
import com.hnust.domain.Visual1;
import com.hnust.view.paper.GeneratePaperThirdView;
import com.hnust.view.paper.GeneratePaperView;
import com.hnust.view.paper.component.AddPaperKindView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
    private MainController mainController;
    @Autowired
    private GeneratePaperThirdView generatePaperThirdView;
    @Autowired
    private GeneratePaperView generatePaperView;
    @Autowired
    private AddPaperKindView addPaperKindView;
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
    @FXML
    public VBox paper_contain_list;
    //容器宽度
    public Double width;
    //添加弹窗
    public Dialog dialog=null;
    //添加选择题型，选择题所有组件
    public VBox vBox_chose;
    public AnchorPane anchorPane_chose;
    public ListView listView_chose;
    public CheckBox checkBox_chose;
    public Label title_chose;
    public HBox hBox_chose;
    public Label easy_chose;
    public Label easy_num_chose;
    public Label mid_chose;
    public Label mid_num_chose;
    public Label diff_chose;
    public Label diff_num_chose;
    public Button btn_chose;
    //添加判断题型，选择题所有组件
    public VBox vBox_judge;
    public AnchorPane anchorPane_judge;
    public ListView listView_judge;
    public CheckBox checkBox_judge;
    public Label title_judge;
    public HBox hBox_judge;
    public Label easy_judge;
    public Label easy_num_judge;
    public Label mid_judge;
    public Label mid_num_judge;
    public Label diff_judge;
    public Label diff_num_judge;
    public Button btn_judge;
    //添加简答题型，选择题所有组件
    public VBox vBox_short;
    public AnchorPane anchorPane_short;
    public ListView listView_short;
    public CheckBox checkBox_short;
    public Label title_short;
    public HBox hBox_short;
    public Label easy_short;
    public Label easy_num_short;
    public Label mid_short;
    public Label mid_num_short;
    public Label diff_short;
    public Label diff_num_short;
    public Button btn_short;
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
            }
        });
        scp_paper.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                scp_paper_contain.setPrefHeight((double)newValue-2);
                scp_paper_contain.setMaxHeight((double)newValue+1);
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
        if("选择题".equals(kind)&&vBox_chose==null){
            addChose();
            showData(listView_chose);
        }
        if("判断题".equals(kind)&&vBox_judge==null){
            addJudge();
            showData(listView_judge);
        }
        if("简答题".equals(kind)&&vBox_short==null){
            addShort();
            showData(listView_short);
        }
    }
    //试卷表格
    public void showData(ListView view){
        Visual1 v1=new Visual1("1","2",1);
        Visual1 v2=new Visual1("1","2",1);
        Visual1 v3=new Visual1("1","2",1);
        Visual1 v4=new Visual1("1","2",1);
        Visual1 v5=new Visual1("1","2",0);
        Visual1 v6=new Visual1("1","2",1);
        ObservableList list= FXCollections.observableArrayList();
        list.addAll(v1,v2,v3,v4,v5,v6);
        view.setItems(list);
        view.setPrefHeight(1500);
        view.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView param) {
                ListCell cell=new ListCell(){
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty==false){
                            AnchorPane anchorPane=new AnchorPane();
                            CheckBox checkBox=new CheckBox();
                            checkBox.setSelected(true);
                            AnchorPane.setTopAnchor(checkBox, 15.0);
                            AnchorPane.setLeftAnchor(checkBox, 10.0);
                            Label label1=new Label("1.这是一个选择题（）是一个选择题（是一个选择题（是一个选择题（是一个选择题（是一个选择题（是一个选择题（" +
                                    "是一个选择题（是一个选择题（是一个选择题（是一个选择题（" +
                                    "是一个选择题（是一个选择题（是一个选择题（是一个选择题（" +
                                    "是一个选择题（是一个选择题（是一个选择题（是一个选择题（");
                            label1.getStyleClass().add("question");
                            label1.setWrapText(true);
                            label1.setMaxHeight(180);
                            Label label2=new Label("A. 1  B. 2  C. 3  D. 4");
                            label2.getStyleClass().add("question");
                            label2.setWrapText(true);
                            VBox vBox=new VBox(label1,label2);
                            vBox.setMaxWidth(width);
                            vBox.setSpacing(10);
                            AnchorPane.setTopAnchor(vBox, 15.0);
                            AnchorPane.setLeftAnchor(vBox, 50.0);
                            AnchorPane.setRightAnchor(vBox, 125.0);
                            Label label3=new Label("分值：");
                            Label label4=new Label("3分");
                            HBox hBox=new HBox(label3,label4);
                            hBox.setSpacing(5.0);
                            hBox.setAlignment(Pos.CENTER);
                            AnchorPane.setRightAnchor(hBox, 20.0);
                            AnchorPane.setTopAnchor(hBox, 18.0);
                            anchorPane.getChildren().addAll(checkBox,vBox,hBox);
                            anchorPane.setMaxHeight(200);
                            anchorPane.setPrefHeight(200);
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
    //添加选择题类型
    public void addChose(){
        checkBox_chose=new CheckBox();
        AnchorPane.setTopAnchor(checkBox_chose, 13.0);
        AnchorPane.setLeftAnchor(checkBox_chose,10.0);
        title_chose=new Label("一、选择题");
        title_chose.getStyleClass().add("paper_title_bg");
        AnchorPane.setTopAnchor(title_chose, 9.0);
        AnchorPane.setLeftAnchor(title_chose,40.0);
        easy_chose=new Label("较易：");
        easy_num_chose=new Label("1");
        mid_chose=new Label("中等：");
        mid_num_chose=new Label("1");
        diff_chose=new Label("较难");
        diff_num_chose=new Label("1");
        hBox_chose=new HBox(easy_chose,easy_num_chose,mid_chose,mid_num_chose,diff_chose,diff_num_chose);
        hBox_chose.setSpacing(10.0);
        AnchorPane.setTopAnchor(hBox_chose, 15.0);
        AnchorPane.setRightAnchor(hBox_chose, 200.0);
        btn_chose=new Button("添加题目");
        btn_chose.getStyleClass().addAll("btn","btn-info","btn_appearance");
        AnchorPane.setTopAnchor(btn_chose, 5.0);
        AnchorPane.setRightAnchor(btn_chose, 5.0);
        anchorPane_chose=new AnchorPane(checkBox_chose,title_chose,hBox_chose,btn_chose);
        anchorPane_chose.getStyleClass().add("paper_title");
        listView_chose=new ListView();
        listView_chose.setPrefHeight(0);
        vBox_chose=new VBox(anchorPane_chose,listView_chose);
        paper_contain.getChildren().add(vBox_chose);
    }
    //添加判断题类型
    public void addJudge(){
        checkBox_judge=new CheckBox();
        AnchorPane.setTopAnchor(checkBox_judge, 13.0);
        AnchorPane.setLeftAnchor(checkBox_judge,10.0);
        title_judge=new Label("二、判断题");
        title_judge.getStyleClass().add("paper_title_bg");
        AnchorPane.setTopAnchor(title_judge, 9.0);
        AnchorPane.setLeftAnchor(title_judge,40.0);
        easy_judge=new Label("较易：");
        easy_num_judge=new Label("1");
        mid_judge=new Label("中等：");
        mid_num_judge=new Label("1");
        diff_judge=new Label("较难");
        diff_num_judge=new Label("1");
        hBox_judge=new HBox(easy_judge,easy_num_judge,mid_judge,mid_num_judge,diff_judge,diff_num_judge);
        hBox_judge.setSpacing(10.0);
        AnchorPane.setTopAnchor(hBox_judge, 15.0);
        AnchorPane.setRightAnchor(hBox_judge, 200.0);
        btn_judge=new Button("添加题目");
        btn_judge.getStyleClass().addAll("btn","btn-info","btn_appearance");
        AnchorPane.setTopAnchor(btn_judge, 5.0);
        AnchorPane.setRightAnchor(btn_judge, 5.0);
        anchorPane_judge=new AnchorPane(checkBox_judge,title_judge,hBox_judge,btn_judge);
        anchorPane_judge.getStyleClass().add("paper_title");
        listView_judge=new ListView();
        listView_judge.setPrefHeight(0);
        vBox_judge=new VBox(anchorPane_judge,listView_judge);
        paper_contain.getChildren().add(vBox_judge);
    }
    //添加简答题型
    public void addShort(){
        checkBox_short=new CheckBox();
        AnchorPane.setTopAnchor(checkBox_short, 13.0);
        AnchorPane.setLeftAnchor(checkBox_short,10.0);
        title_short=new Label("三、简答题");
        title_short.getStyleClass().add("paper_title_bg");
        AnchorPane.setTopAnchor(title_short, 9.0);
        AnchorPane.setLeftAnchor(title_short,40.0);
        easy_short=new Label("较易：");
        easy_num_short=new Label("1");
        mid_short=new Label("中等：");
        mid_num_short=new Label("1");
        diff_short=new Label("较难");
        diff_num_short=new Label("1");
        hBox_short=new HBox(easy_short,easy_num_short,mid_short,mid_num_short,diff_short,diff_num_short);
        hBox_short.setSpacing(10.0);
        AnchorPane.setTopAnchor(hBox_short, 15.0);
        AnchorPane.setRightAnchor(hBox_short, 200.0);
        btn_short=new Button("添加题目");
        btn_short.getStyleClass().addAll("btn","btn-info","btn_appearance");
        AnchorPane.setTopAnchor(btn_short, 5.0);
        AnchorPane.setRightAnchor(btn_short, 5.0);
        anchorPane_short=new AnchorPane(checkBox_short,title_short,hBox_short,btn_short);
        anchorPane_short.getStyleClass().add("paper_title");
        listView_short=new ListView();
        listView_short.setPrefHeight(0);
        vBox_short=new VBox(anchorPane_short,listView_short);
        paper_contain.getChildren().add(vBox_short);
    }
}
