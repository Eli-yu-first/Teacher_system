package com.hnust.controller.paper;

import com.hnust.controller.MainController;
import com.hnust.controller.paper.component.AddPaperKindController;
import com.hnust.controller.paper.component.AddQuestionController;
import com.hnust.domain.Question;
import com.hnust.domain.Visual1;
import com.hnust.utils.NumberJudge;
import com.hnust.view.paper.GeneratePaperThirdView;
import com.hnust.view.paper.GeneratePaperView;
import com.hnust.view.paper.component.AddPaperKindView;
import com.hnust.view.paper.component.AddQuestionView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
    @Autowired
    private AddQuestionView addQuestionView;
    @Autowired
    private AddQuestionController addQuestionController;
    //弹窗控制器
    @Autowired
    private AddPaperKindController addPaperKindController;
    //字符判断工具
    @Autowired
    private NumberJudge numberJudge;
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
    //题目数
    @FXML
    public Label questionNumberLabel;
    //总分
    @FXML
    public Label questionSumScoreLabel;
    //简单
    @FXML
    public Label easyQuestionLabel;
    //中等
    @FXML
    public Label midQuestionLabel;
    //较难
    @FXML
    public Label diffQuestionLabel;
    //选择
    @FXML
    public Label choseQuestionLabel;
    //判断
    @FXML
    public Label judgeQuestionLabel;
    //简答
    @FXML
    public Label shortQuestionLabel;

    private long questionNumberCount=0;
    private long questionSumScoreCount=0;
    private long easyQuestionCount=0;
    private long choseEasyQuestionCount=0;
    private long judgeEasyQuestionCount=0;
    private long shortEasyQuestionCount=0;
    private long midQuestionCount=0;
    private long choseMidQuestionCount=0;
    private long judgeMidQuestionCount=0;
    private long shortMidQuestionCount=0;
    private long diffQuestionCount=0;
    private long choseDiffQuestionCount=0;
    private long judgeDiffQuestionCount=0;
    private long shortDiffQuestionCount=0;
    private long choseQuestionCount=0;
    private long judgeQuestionCount=0;
    private long shortQuestionCount=0;
    //容器宽度
    public Double width;
    //容器高度
    public Double height;
    //添加选择题型，选择题所有组件
    private VBox vBox_chose;
    private AnchorPane anchorPane_chose;
    private ListView<Question> listView_chose;
    private CheckBox checkBox_chose;
    private Label title_chose;
    private HBox hBox_chose;
    private Label easy_chose;
    private Label easy_num_chose;
    private Label mid_chose;
    private Label mid_num_chose;
    private Label diff_chose;
    private Label diff_num_chose;
    private Button btn_chose;
    private ObservableList<Question> list_chose= FXCollections.observableArrayList();
    //添加判断题型，选择题所有组件
    private VBox vBox_judge;
    private AnchorPane anchorPane_judge;
    private ListView<Question> listView_judge;
    private CheckBox checkBox_judge;
    private Label title_judge;
    private HBox hBox_judge;
    private Label easy_judge;
    private Label easy_num_judge;
    private Label mid_judge;
    private Label mid_num_judge;
    private Label diff_judge;
    private Label diff_num_judge;
    private Button btn_judge;
    private ObservableList<Question> list_judge= FXCollections.observableArrayList();
    //添加简答题型，选择题所有组件
    private VBox vBox_short;
    private AnchorPane anchorPane_short;
    private ListView<Question> listView_short;
    private CheckBox checkBox_short;
    private Label title_short;
    private HBox hBox_short;
    private Label easy_short;
    private Label easy_num_short;
    private Label mid_short;
    private Label mid_num_short;
    private Label diff_short;
    private Label diff_num_short;
    private Button btn_short;
    private ObservableList<Question> list_short= FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scp_paper.setFitToWidth(true);
        listenChange();
        setAllLabel();
    }
    //根据窗口改变，进行监听设置页面大小
    public void listenChange(){
        //通过监听最外层容器的宽度，来改变内层Anchor的宽度
        container.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                contain.setPrefWidth((double)newValue-2);
                width=(double)newValue;
            }
        });
        //通过监听最外层容器的高度，来改变内层Anchor的高度
        container.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                contain.setPrefHeight((double)newValue-2);
                height=(double)newValue;
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
    //显示添加题型弹窗
    public void addQuestionKind(){
        Dialog dialog=new Dialog();
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
            addQuestionKind(addPaperKindController.getKind());
        }
    }
    //跳转至下一页
    public void next() throws IOException {
        mainController.skipView(generatePaperThirdView);
    }
    //回退至上一页
    public void back() throws IOException {
        mainController.skipView(generatePaperView);
    }
    //显示添加题目类型弹窗（选择题、判断题、简答题）
    public void addQuestionKind(String kind){
        if("选择题".equals(kind)&&vBox_chose==null){
            this.addChose();
        }
        if("判断题".equals(kind)&&vBox_judge==null){
            this.addJudge();
        }
        if("简答题".equals(kind)&&vBox_short==null){
            this.addShort();
        }
    }
    //为对应的ListView设置内容
    public void showData(ListView<Question>view,ObservableList<Question> list,List<Question> addList,CheckBox cb){
        list.addAll(addList);
        view.setItems(list);
        setListViewHeight(view,list);
        view.setCellFactory(new Callback<ListView<Question>, ListCell<Question>>() {
            @Override
            public ListCell<Question> call(ListView<Question> param) {
                ListCell<Question> cell=new ListCell<Question>(){
                    @Override
                    protected void updateItem(Question item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty==false){
                            AnchorPane anchorPane=new AnchorPane();
                            CheckBox checkBox=new CheckBox();
                            checkBox.setSelected(item.getChecked());
                            checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                                if(newValue==true){
                                    item.setChecked(true);
                                    Boolean bl=view.getItems().stream().anyMatch(question -> question.getChecked()==false);
                                    if(bl==false){
                                        cb.setSelected(true);
                                    }
                                }else{
                                    item.setChecked(false);
                                    cb.setSelected(false);
                                }
                            });
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
                            vBox.setMaxWidth(width*0.2);
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
                            Label label5=new Label("较易");
                            label5.setStyle("-fx-text-fill: green;-fx-font-weight: bold");
                            HBox hBox1=new HBox(label5);
                            hBox1.setSpacing(5.0);
                            hBox1.setAlignment(Pos.CENTER);
                            AnchorPane.setRightAnchor(hBox1, 33.0);
                            AnchorPane.setTopAnchor(hBox1, 40.0);
                            anchorPane.getChildren().addAll(checkBox,vBox,hBox,hBox1);
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
    //根据ListView中元素的个数来动态设置对应列表高度设置
    public void setListViewHeight(ListView<Question>view,ObservableList<Question> list){
        view.setPrefHeight((double)(232*list.size()));
        view.setStyle("-fx-fixed-cell-size:230");
    }
    //添加题型选择题
    public void addChose(){
        checkBox_chose=new CheckBox();
        checkBox_chose.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue==true){
                listView_chose.getItems().stream().forEach(question -> question.setChecked(true));
            }else{
                if(listView_chose.getItems().stream().anyMatch(question -> question.getChecked()==false)){

                }else{
                    listView_chose.getItems().stream().forEach(question -> question.setChecked(false));
                }
            }
            listView_chose.refresh();
        });
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
        btn_chose.setOnAction(showAddQuestion());
        btn_chose.getStyleClass().addAll("btn","btn-info","btn_appearance");
        AnchorPane.setTopAnchor(btn_chose, 5.0);
        AnchorPane.setRightAnchor(btn_chose, 5.0);
        anchorPane_chose=new AnchorPane(checkBox_chose,title_chose,hBox_chose,btn_chose);
        anchorPane_chose.getStyleClass().add("paper_title");
        listView_chose=new ListView();
        listView_chose.setPrefHeight(0);
        vBox_chose=new VBox(anchorPane_chose,listView_chose);
        paper_contain.getChildren().add(0, vBox_chose);
    }
    //添加题型判断题
    public void addJudge(){
        checkBox_judge=new CheckBox();
        checkBox_judge.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue==true){
                listView_judge.getItems().stream().forEach(question -> question.setChecked(true));
            }else{
                if(listView_judge.getItems().stream().anyMatch(question -> question.getChecked()==false)){

                }else{
                    listView_judge.getItems().stream().forEach(question -> question.setChecked(false));
                }
            }
            listView_judge.refresh();
        });
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
        btn_judge.setOnAction(showAddQuestion());
        btn_judge.getStyleClass().addAll("btn","btn-info","btn_appearance");
        AnchorPane.setTopAnchor(btn_judge, 5.0);
        AnchorPane.setRightAnchor(btn_judge, 5.0);
        anchorPane_judge=new AnchorPane(checkBox_judge,title_judge,hBox_judge,btn_judge);
        anchorPane_judge.getStyleClass().add("paper_title");
        listView_judge=new ListView();
        listView_judge.setPrefHeight(0);
        listView_judge.getStyleClass().addAll("outScroll");
        vBox_judge=new VBox(anchorPane_judge,listView_judge);
        if(vBox_short==null&&vBox_chose==null){
            paper_contain.getChildren().add(0,vBox_judge);
        }else if((vBox_short==null&&vBox_chose!=null)||(vBox_short!=null&&vBox_chose!=null)){
            paper_contain.getChildren().add(1,vBox_judge);
        }else if(vBox_short!=null&&vBox_chose==null){
            paper_contain.getChildren().add(0,vBox_judge);
        }
    }
    //添加题型简答题
    public void addShort(){
        checkBox_short=new CheckBox();
        checkBox_short.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue==true){
                listView_short.getItems().stream().forEach(question -> question.setChecked(true));
            }else{
                if(listView_short.getItems().stream().anyMatch(question -> question.getChecked()==false)){

                }else{
                    listView_short.getItems().stream().forEach(question -> question.setChecked(false));
                }
            }
            listView_short.refresh();
        });
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
        btn_short.setOnAction(showAddQuestion());
        btn_short.getStyleClass().addAll("btn","btn-info","btn_appearance");
        AnchorPane.setTopAnchor(btn_short, 5.0);
        AnchorPane.setRightAnchor(btn_short, 5.0);
        anchorPane_short=new AnchorPane(checkBox_short,title_short,hBox_short,btn_short);
        anchorPane_short.getStyleClass().add("paper_title");
        listView_short=new ListView();
        listView_short.setPrefHeight(0);
        vBox_short=new VBox(anchorPane_short,listView_short);
        if(vBox_judge==null&&vBox_chose==null){
            paper_contain.getChildren().add(0,vBox_short);
        }else if((vBox_judge==null&&vBox_chose!=null)||(vBox_judge!=null&&vBox_chose==null)){
            paper_contain.getChildren().add(1,vBox_short);
        }else if(vBox_judge!=null&&vBox_chose!=null){
            paper_contain.getChildren().add(2,vBox_short);
        }
    }
    //添加题目按钮点击事件
    public EventHandler<ActionEvent> showAddQuestion(){
        EventHandler<ActionEvent> eventHandler=new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Dialog dialog=new Dialog();
                DialogPane dialogPane=new DialogPane();
                dialogPane.setStyle("-fx-background-color: #FFF;-fx-border-width: 2;-fx-border-color: #ADADAD");
                dialogPane.setContent(addQuestionView.getView());
                if(((Button)event.getTarget())==btn_chose){
                    addQuestionController.setInitAppearance("题型:选择题");
                }
                else if(((Button)event.getTarget())==btn_judge){
                    addQuestionController.setInitAppearance("题型:判断题");
                }
                else if(((Button)event.getTarget())==btn_short){
                    addQuestionController.setInitAppearance("题型:简答题");
                }
                dialogPane.setPrefWidth(width);
                dialogPane.setPrefHeight(height);
                dialog.setDialogPane(dialogPane);
                dialog.setTitle("选择题添加");
                dialog.initStyle(StageStyle.UNDECORATED);
                dialog.setGraphic(null);
                ButtonType ok=new ButtonType("添加选中题型", ButtonBar.ButtonData.OK_DONE);
                ButtonType cancel=new ButtonType("取消", ButtonBar.ButtonData.CANCEL_CLOSE);
                dialog.getDialogPane().getButtonTypes().addAll(ok,cancel);
                Optional<ButtonType> result=dialog.showAndWait();
                if(result.get()==ok){
                    if(((Button)event.getTarget())==btn_chose){
                        showData(listView_chose, list_chose, addQuestionController.getAddList(),checkBox_chose);
                    }
                    else if(((Button)event.getTarget())==btn_judge){
                        showData(listView_judge, list_judge, addQuestionController.getAddList(),checkBox_judge);
                    }
                    else if(((Button)event.getTarget())==btn_short){
                        showData(listView_short, list_short, addQuestionController.getAddList(),checkBox_short);
                    }
                    changePanel();
                    setAllLabel();
                    addQuestionController.close();
                }else{
                    addQuestionController.close();
                }
            }
        };
        return eventHandler;
    }
    //设置分值面板
    public void setAllLabel(){
        questionNumberLabel.setText(String.valueOf(questionNumberCount));
        questionSumScoreLabel.setText(String.valueOf(questionSumScoreCount));
        easyQuestionLabel.setText(String.valueOf(easyQuestionCount));
        midQuestionLabel.setText(String.valueOf(midQuestionCount));
        diffQuestionLabel.setText(String.valueOf(diffQuestionCount));
        choseQuestionLabel.setText(String.valueOf(choseQuestionCount));
        judgeQuestionLabel.setText(String.valueOf(judgeQuestionCount));
        shortQuestionLabel.setText(String.valueOf(shortQuestionCount));
    }
    //面板值变化
    public void changePanel(){
        if(vBox_chose!=null){
            choseQuestionCount=listView_chose.getItems().size();
            listView_chose.getItems().stream().forEach(question -> {
                if(question.getDiff()==1){
                    choseEasyQuestionCount++;
                }
                if (question.getDiff()==2){
                    choseMidQuestionCount++;
                }
                if (question.getDiff()==3){
                    choseDiffQuestionCount++;
                }
            });
        }
        if(vBox_judge!=null){
            judgeQuestionCount=listView_judge.getItems().size();
            listView_judge.getItems().stream().forEach(question -> {
                if(question.getDiff()==1){
                    judgeEasyQuestionCount++;
                }
                if (question.getDiff()==2){
                    judgeMidQuestionCount++;
                }
                if (question.getDiff()==3){
                    judgeDiffQuestionCount++;
                }
            });
        }
        if(vBox_short!=null){
            shortQuestionCount=listView_short.getItems().size();
            listView_short.getItems().stream().forEach(question -> {
                if(question.getDiff()==1){
                    shortEasyQuestionCount++;
                }
                if (question.getDiff()==2){
                    shortMidQuestionCount++;
                }
                if (question.getDiff()==3){
                    shortDiffQuestionCount++;
                }
            });
        }
        questionNumberCount=choseQuestionCount+judgeQuestionCount+shortQuestionCount;
        easyQuestionCount=choseEasyQuestionCount+judgeEasyQuestionCount+shortEasyQuestionCount;
        midQuestionCount=choseMidQuestionCount+judgeMidQuestionCount+shortMidQuestionCount;
        diffQuestionCount=choseDiffQuestionCount+judgeDiffQuestionCount+shortDiffQuestionCount;
    }
    //修改分值
    public void changeScore(){
        if(!list_chose.isEmpty()||!list_chose.isEmpty()||!list_short.isEmpty()){
            TextInputDialog dialog = new TextInputDialog("分值");
            dialog.setHeaderText("设置所选题目分值");
            dialog.setContentText("输入分数:");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                if(numberJudge.isInteger(result.get().trim())){
                    Integer value=Integer.valueOf(result.get());
                    if(list_chose.size()!=0&&listView_chose!=null){
                        list_chose.filtered(question -> question.getChecked()==true).forEach(question -> question.setScore(value));
                        listView_chose.refresh();
                    }
                    if(list_judge.size()!=0&&listView_judge!=null){
                        list_judge.filtered(question -> question.getChecked()==true).forEach(question -> question.setScore(value));
                        listView_judge.refresh();
                    }
                    if(list_short.size()!=0&&listView_short!=null){
                        list_short.filtered(question -> question.getChecked()==true).forEach(question -> question.setScore(value));
                        listView_short.refresh();
                    }
                    changePanel();
                    setAllLabel();
                }
            }
        }
    }
    //删除题目
    public void deleteQuestion(){
        if(list_chose.size()!=0&&listView_chose!=null){
            list_chose.removeIf(question -> question.getChecked()==true);
            setListViewHeight(listView_chose,list_chose);
            listView_chose.refresh();
        }
        if(list_judge.size()!=0&&listView_judge!=null){
            list_judge.removeIf(question -> question.getChecked()==true);
            setListViewHeight(listView_judge,list_judge);
            listView_judge.refresh();
        }
        if(list_short.size()!=0&&listView_short!=null){
            list_short.removeIf(question -> question.getChecked()==true);
            setListViewHeight(listView_short,list_short);
            listView_short.refresh();
        }
        changePanel();
        setAllLabel();
    }
}
