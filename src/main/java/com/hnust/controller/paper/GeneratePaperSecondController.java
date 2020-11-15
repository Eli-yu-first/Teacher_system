package com.hnust.controller.paper;

import com.hnust.controller.MainController;
import com.hnust.controller.paper.component.AddPaperKindController;
import com.hnust.controller.paper.component.AddQuestionController;
import com.hnust.domain.QuestionType;
import com.hnust.domain.RepeatQues;
import com.hnust.domain.SubjectData;
import com.hnust.domain.SubjectDataRecord;
import com.hnust.service.TestPaperService;
import com.hnust.store.DataStore;
import com.hnust.store.GeneratePaperDataStore;
import com.hnust.utils.NumberJudge;
import com.hnust.view.paper.GeneratePaperThirdView;
import com.hnust.view.paper.GeneratePaperView;
import com.hnust.view.paper.component.AddPaperKindView;
import com.hnust.view.paper.component.AddQuestionView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.net.URL;
import java.util.*;
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
    private AddPaperKindView addPaperKindView;
    @Autowired
    private AddQuestionView addQuestionView;
    @Autowired
    private AddQuestionController addQuestionController;
    @Autowired
    private GeneratePaperDataStore generatePaperDataStore;
    @Autowired
    private DataStore dataStore;
    @Autowired
    private TestPaperService testPaperService;
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
    @FXML
    public Label repeatedNumLabel;
    @FXML
    public Label repeatedRateLabel;

    //容器宽度
    public Double width;
    //容器高度
    public Double height;

    private Map<String,CheckBox> checkBoxMap=new HashMap<>();
    private Map<String,ListView<SubjectDataRecord>> listViewMap=new HashMap<>();
    private Map<String,Label> easyLabelMap=new HashMap<>();
    private Map<String,Label> midLabelMap=new HashMap<>();
    private Map<String,Label> diffLabelMap=new HashMap<>();
    private Map<String,ObservableList> itemMap=new HashMap<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scp_paper.setFitToWidth(true);
        listenChange();
        container.parentProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                if(generatePaperDataStore.getFlag()!=2){
                    close();
                }
            }
        });
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
            addQuestionKind(generatePaperDataStore.getKind());
        }
    }

    //显示添加题目类型弹窗（选择题、判断题、简答题）
    public void addQuestionKind(String kind){
        addQuestionList(String.valueOf(getQuestionTypeId(kind)), kind);
    }

    //根据ListView中元素的个数来动态设置对应列表高度设置
    public void setListViewHeight(ListView<SubjectDataRecord>view,ObservableList<SubjectDataRecord> list){
        view.setPrefHeight((double)(232*list.size()));
        view.setStyle("-fx-fixed-cell-size:230");
    }

    //为对应的ListView设置内容
    public void showData(ListView<SubjectDataRecord>view,ObservableList<SubjectDataRecord> list,List<SubjectDataRecord> addList,CheckBox cb){
        addList.forEach(subjectDataRecord -> subjectDataRecord.setChecked(false));
        list.addAll(addList);
        view.setItems(list);
        setListViewHeight(view,list);
        view.setCellFactory(new Callback<ListView<SubjectDataRecord>, ListCell<SubjectDataRecord>>() {
            @Override
            public ListCell<SubjectDataRecord> call(ListView<SubjectDataRecord> param) {
                ListCell<SubjectDataRecord> cell=new ListCell<SubjectDataRecord>(){
                    @Override
                    protected void updateItem(SubjectDataRecord item, boolean empty) {
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
                            Label label4=new Label(String.valueOf(item.getScore()));
                            HBox hBox=new HBox(label3,label4);
                            hBox.setSpacing(5.0);
                            hBox.setAlignment(Pos.CENTER);
                            AnchorPane.setRightAnchor(hBox, 20.0);
                            AnchorPane.setTopAnchor(hBox, 18.0);
                            Label label5=new Label();
                            if(item.getSubjectData().getDifficult()==1){
                                label5.setText("较易");
                                label5.setStyle("-fx-text-fill: green;-fx-font-weight: bold");
                            }else if(item.getSubjectData().getDifficult()==2){
                                label5=new Label("中等");
                                label5.setStyle("-fx-text-fill: blue;-fx-font-weight: bold");
                            }
                            else if(item.getSubjectData().getDifficult()==3){
                                label5=new Label("较难");
                                label5.setStyle("-fx-text-fill: red;-fx-font-weight: bold");
                            }
                            HBox hBox1=new HBox(label5);
                            hBox1.setSpacing(5.0);
                            hBox1.setAlignment(Pos.CENTER);
                            AnchorPane.setRightAnchor(hBox1, 33.0);
                            AnchorPane.setTopAnchor(hBox1, 40.0);

                            Label label6=new Label("与样卷重复");
                            label6.setStyle("-fx-text-fill: red;-fx-font-weight: bold");
                            HBox hBox2=new HBox(label6);
                            hBox2.setSpacing(5.0);
                            hBox2.setAlignment(Pos.CENTER);
                            AnchorPane.setRightAnchor(hBox2, 0.0);
                            AnchorPane.setTopAnchor(hBox2, 60.0);
                            if(item.getRepeated()==false){
                                anchorPane.getChildren().addAll(checkBox,vBox,hBox,hBox1);
                            }else{
                                anchorPane.getChildren().addAll(checkBox,vBox,hBox,hBox1,hBox2);
                            }
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

    //添加选中的题目类型
    public void addQuestionList(String typeId,String type){
        if(!listViewMap.containsKey(typeId)){
            ListView<SubjectDataRecord> listView=new ListView();
            listView.setPrefHeight(0);
            listViewMap.put(typeId,listView);
            ObservableList<SubjectDataRecord> observableList=FXCollections.observableArrayList();
            itemMap.put(typeId,observableList);
            CheckBox checkBox=new CheckBox();
            checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue==true){
                    listView.getItems().stream().forEach(subjectDataRecord -> subjectDataRecord.setChecked(true));
                }else{
                    if(listView.getItems().stream().anyMatch(subjectDataRecord-> subjectDataRecord.getChecked()==false)){

                    }else{
                        listView.getItems().stream().forEach(subjectDataRecord -> subjectDataRecord.setChecked(false));
                    }
                }
                listView.refresh();
            });
            checkBoxMap.put(typeId,checkBox);
            AnchorPane.setTopAnchor(checkBox, 13.0);
            AnchorPane.setLeftAnchor(checkBox,10.0);
            Label titleLabel=new Label(type);
            titleLabel.getStyleClass().add("paper_title_bg");
            AnchorPane.setTopAnchor(titleLabel, 9.0);
            AnchorPane.setLeftAnchor(titleLabel,40.0);
            Label easyLabel=new Label("较易：");
            Label easyNumLabel=new Label("0");
            easyLabelMap.put(typeId,easyNumLabel);
            Label midLabel=new Label("中等：");
            Label midNumLabel=new Label("0");
            midLabelMap.put(typeId,midNumLabel);
            Label diffLabel=new Label("较难：");
            Label diffNumLabel=new Label("0");
            diffLabelMap.put(typeId,diffNumLabel);
            HBox hBox=new HBox(easyLabel,easyNumLabel,midLabel,midNumLabel,diffLabel,diffNumLabel);
            hBox.setSpacing(10.0);
            AnchorPane.setTopAnchor(hBox, 15.0);
            AnchorPane.setRightAnchor(hBox, 200.0);
            Button btn=new Button("添加题目");
            btn.setOnAction(showAddQuestion(type));
            btn.getStyleClass().addAll("btn","btn-info","btn_appearance");
            AnchorPane.setTopAnchor(btn, 5.0);
            AnchorPane.setRightAnchor(btn, 5.0);
            AnchorPane anchorPane=new AnchorPane(checkBox,titleLabel,hBox,btn);
            anchorPane.getStyleClass().add("paper_title");
            VBox vBox=new VBox(anchorPane,listView);
            paper_contain.getChildren().add(vBox);
        }
    }

    //添加题目按钮点击事件
    public EventHandler<ActionEvent> showAddQuestion(String type){
        EventHandler<ActionEvent> eventHandler=new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                storeQuestionType(type);//保存题型ID
                Dialog dialog=new Dialog();
                DialogPane dialogPane=new DialogPane();
                dialogPane.setStyle("-fx-background-color: #FFF;-fx-border-width: 2;-fx-border-color: #ADADAD");
                dialogPane.setContent(addQuestionView.getView());
                addQuestionController.setInitAppearance("题型:"+type);
                String typeId=String.valueOf(getQuestionTypeId(type));//获取题型ID
                dialogPane.setPrefWidth(width);
                dialogPane.setPrefHeight(height*1.05);
                dialog.setDialogPane(dialogPane);
                dialog.setTitle("选择题添加");
                dialog.initStyle(StageStyle.UNDECORATED);
                dialog.setGraphic(null);
                ButtonType ok=new ButtonType("添加选中题型", ButtonBar.ButtonData.OK_DONE);
                ButtonType cancel=new ButtonType("取消", ButtonBar.ButtonData.CANCEL_CLOSE);
                dialog.getDialogPane().getButtonTypes().addAll(ok,cancel);
                Optional<ButtonType> result=dialog.showAndWait();
                if(result.get()==ok) {
                    showData(listViewMap.get(typeId), itemMap.get(typeId), addQuestionController.getAddList(), checkBoxMap.get(typeId));
                    changePanel();
                }
            }
        };
        return eventHandler;
    }

    //获取题目类型保存对应的题目id
    public void storeQuestionType(String typeName){
        for (QuestionType questionType:generatePaperDataStore.getQuestionTypes()){
            if(typeName.equals(questionType.getName().trim())){
                generatePaperDataStore.setQuesyionTypeId(questionType.getId());
                break;
            }
        }
    }

    //根据题型，获取题型id
    public int getQuestionTypeId(String typeName){
        for (QuestionType questionType:generatePaperDataStore.getQuestionTypes()){
            if(questionType.getName()==typeName){
                return questionType.getId();
            }
        }
        return 0;
    }

    //修改分值
    public void changeScore() throws InterruptedException {
        if(!listViewMap.isEmpty()){
            TextInputDialog dialog = new TextInputDialog("分值");
            dialog.setHeaderText("设置所选题目分值");
            dialog.setContentText("输入分数:");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                if(numberJudge.isInteger(result.get().trim())){
                    Integer value=Integer.valueOf(result.get());
                    for (Map.Entry<String,ListView<SubjectDataRecord>> entry: listViewMap.entrySet()){
                        entry.getValue().getItems().filtered(subjectDataRecord -> subjectDataRecord.getChecked()==true).forEach(subjectDataRecord -> subjectDataRecord.setScore(value));
                        entry.getValue().refresh();
                    }
                    changePanel();
                }
            }
        }
    }

    //TODO 将题目设为隐藏
    public void setQuestionDisable(){

    }

    //面板值变化
    public void changePanel(){
        long questionNumberCount=0;
        long questionSumScoreCount=0;
        int easyQuestionCount=0;
        long midQuestionCount=0;
        long diffQuestionCount=0;
        int choseQuestionCount=0;
        int judgeQuestionCount=0;
        int shortQuestionCount=0;
        int repeatedNumCount=0;
        for (Map.Entry<String,ListView<SubjectDataRecord>> entry: listViewMap.entrySet()){
            questionNumberCount+=entry.getValue().getItems().size();
            easyQuestionCount += entry.getValue().getItems().stream().filter(subjectDataRecord -> subjectDataRecord.getSubjectData().getDifficult() == 1).count();
            midQuestionCount += entry.getValue().getItems().stream().filter(subjectDataRecord -> subjectDataRecord.getSubjectData().getDifficult() == 2).count();
            diffQuestionCount += entry.getValue().getItems().stream().filter(subjectDataRecord -> subjectDataRecord.getSubjectData().getDifficult() == 3).count();
            if("2".equals(entry.getKey())){
                choseQuestionCount=entry.getValue().getItems().size();
            }else if("3".equals(entry.getKey())){
                judgeQuestionCount=entry.getValue().getItems().size();
            }else if("4".equals(entry.getKey())){
                shortQuestionCount=entry.getValue().getItems().size();
            }
            repeatedNumCount+=repeatedNum(entry.getValue());
            easyLabelMap.get(entry.getKey()).setText(String.valueOf(entry.getValue().getItems().stream().filter(subjectDataRecord -> subjectDataRecord.getSubjectData().getDifficult() == 1).count()));
            midLabelMap.get(entry.getKey()).setText(String.valueOf(entry.getValue().getItems().stream().filter(subjectDataRecord -> subjectDataRecord.getSubjectData().getDifficult() == 2).count()));
            diffLabelMap.get(entry.getKey()).setText(String.valueOf(entry.getValue().getItems().stream().filter(subjectDataRecord -> subjectDataRecord.getSubjectData().getDifficult() == 3).count()));
            questionSumScoreCount += entry.getValue().getItems().stream().mapToLong(SubjectDataRecord::getScore).sum();
            entry.getValue().refresh();
        }
        choseQuestionLabel.setText(String.valueOf(choseQuestionCount));
        judgeQuestionLabel.setText(String.valueOf(judgeQuestionCount));
        shortQuestionLabel.setText(String.valueOf(shortQuestionCount));
        questionNumberLabel.setText(String.valueOf(questionNumberCount));
        questionSumScoreLabel.setText(String.valueOf(questionSumScoreCount));
        easyQuestionLabel.setText(String.valueOf(easyQuestionCount));
        midQuestionLabel.setText(String.valueOf(midQuestionCount));
        diffQuestionLabel.setText(String.valueOf(diffQuestionCount));
        repeatedNumLabel.setText(String.valueOf(repeatedNumCount));
        if(questionNumberCount!=0){
            repeatedRateLabel.setText(String.format("%d%%",(repeatedNumCount*100)/questionNumberCount));
        }
        else{
            repeatedRateLabel.setText("0%");
        }
    }

    //删除题目
    public void deleteQuestion(){
        for (Map.Entry<String,ListView<SubjectDataRecord>> entry: listViewMap.entrySet()){
           entry.getValue().getItems().removeIf(subjectDataRecord -> subjectDataRecord.getChecked()==true);
           setListViewHeight(entry.getValue(),itemMap.get(entry.getKey()));
        }
        changePanel();
    }

    //试卷查重
    public void recheck(){
        List<String> list=new ArrayList<>();
        for (Map.Entry<String,ListView<SubjectDataRecord>> entry: listViewMap.entrySet()) {
            list.addAll(entry.getValue().getItems().stream().map(subjectDataRecord -> subjectDataRecord.getSubjectData().getId()).collect(Collectors.toList()));
        }
        testPaperService.checkPaperRepeat(new retrofit2.Callback<List<RepeatQues>>() {
            @Override
            public void onResponse(Call<List<RepeatQues>> call, Response<List<RepeatQues>> response) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        for (RepeatQues repeatQues:response.body()){
                            for (Map.Entry<String,ListView<SubjectDataRecord>> entry: listViewMap.entrySet()) {
                                entry.getValue().getItems().stream().forEach(subjectDataRecord -> {
                                    if(subjectDataRecord.getSubjectData().getId()==repeatQues.getQues_id()){
                                        subjectDataRecord.setRepeated(true);
                                    }else{
                                        subjectDataRecord.setRepeated(false);
                                    }
                                });
                                entry.getValue().refresh();
                            }
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<List<RepeatQues>> call, Throwable t) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        for (Map.Entry<String, ListView<SubjectDataRecord>> entry : listViewMap.entrySet()) {
                            entry.getValue().getItems().stream().forEach(subjectDataRecord -> {
                                subjectDataRecord.setRepeated(true);
                            });
                            entry.getValue().refresh();
                        }
                    }
                });
            }
        }, dataStore.getToken(),dataStore.getTeacher_id(),generatePaperDataStore.getCourseId(), list);
    }

    //题目重复率
    public int repeatedNum(ListView<SubjectDataRecord> listView){
        List<SubjectDataRecord> list=listView.getItems().sorted((before, next) ->before.getSubjectData().getId().compareTo(next.getSubjectData().getId()));
        int num=0;
        for(int i=0;i<list.size()-1;i++){
            int flag=0;
            for(int j=i;j<list.size()-1;j++){
                if(list.get(j).getSubjectData().getId().equals(list.get(j+1).getSubjectData().getId())){
                    flag++;
                    i++;
                }else{
                    break;
                }
            }
            if (flag!=0){
                num++;
            }
        }
        return num;
    }

    //跳转至下一页
    public void next() throws IOException {
        mainController.skipView("手动生成试卷III");
    }

    //回退至上一页
    public void back() throws IOException {
        generatePaperDataStore.setFlag(1);
        mainController.skipView("手动生成试卷");
    }

    //清除数据
    public void close(){
        paper_contain.getChildren().clear();
        checkBoxMap.clear();
        listViewMap.clear();
        easyLabelMap.clear();
        midLabelMap.clear();
        diffLabelMap.clear();
        itemMap.clear();
        changePanel();
    }
}
