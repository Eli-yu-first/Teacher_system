package com.hnust.controller.paper.component;

import com.hnust.controller.paper.GeneratePaperSecondController;
import com.hnust.domain.SubjectData;
import com.hnust.domain.SubjectDataRecord;
import com.hnust.domain.SubjectInfo;
import com.hnust.service.TestPaperService;
import com.hnust.store.DataStore;
import com.hnust.store.GeneratePaperDataStore;
import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import retrofit2.Call;
import retrofit2.Response;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


/**
 * @program: demo
 * @author: 彭鑫淼
 * @description: 未知
 * @create: 2020-11-09 10:17
 */
@FXMLController
public class AddQuestionController implements Initializable {
    @Autowired
    private GeneratePaperSecondController generatePaperSecondController;
    @Autowired
    private GeneratePaperDataStore generatePaperDataStore;
    @Autowired
    private DataStore dataStore;
    @Autowired
    private TestPaperService testPaperService;
    @FXML
    public ListView<SubjectDataRecord> listView;
    @FXML
    public TextField searchValueField;
    @FXML
    public Label questionKindLabel;
    @FXML
    public Label checkedLabel;
    @FXML
    public Label easyQuestionLabel;
    @FXML
    public Label midQuestionLabel;
    @FXML
    public Label diffQuestionLabel;
    @FXML
    public CheckBox cB;
    @FXML
    public ComboBox comb;
    @FXML
    public Pagination page;
    @FXML
    public Label courseNameLLabel;
    private int currentPage=1;
    private String lastSearch="";
    //保存数据的集合
    ObservableList<SubjectDataRecord> list= FXCollections.observableArrayList();
    private long checkedCount=0;
    private long easyQuestionCount=0;
    private long midQuestionCount=0;
    private long diffQuestionCount=0;
    private int flag=0;
    //各种组件初始化操作
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //全选监听事件
        getQuestion();
        addCheckBoxListener();
        addPageListener();
        setComBoxItem();
        setListView();
    }
    //请求数据 TODO(拿到类型ID)
    public void getQuestion(){
        testPaperService.getQuestion(new retrofit2.Callback<SubjectInfo>() {
            @Override
            public void onResponse(Call<SubjectInfo> call, Response<SubjectInfo> response) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        response.body().getSubject().forEach(subjectData -> {
                            list.add(new SubjectDataRecord(subjectData, 0,false,false));
                        });
                        if(response.body().getTotal()%5==0){
                            setListViewItem(response.body().getTotal()/5);
                        }else{
                            setListViewItem(response.body().getTotal()/5+1);
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<SubjectInfo> call, Throwable t) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        SubjectDataRecord s1=new SubjectDataRecord(new SubjectData("1","123","12","1","1",1,"1","1",null,"1",1),1,false,false);
                        SubjectDataRecord s2=new SubjectDataRecord(new SubjectData("1","123","12","1","1",2,"1","1",null,"1",1),2,false,false);
                        SubjectDataRecord s3=new SubjectDataRecord(new SubjectData("3","123","12","1","1",1,"1","1",null,"1",1),3,false,false);
                        SubjectDataRecord s4=new SubjectDataRecord(new SubjectData("3","123","12","1","1",3,"1","1",null,"1",1),1,false,false);
                        SubjectDataRecord s5=new SubjectDataRecord(new SubjectData("5","123","12","1","1",1,"1","1",null,"1",1),1,false,false);
                        list.addAll(s1,s2,s3,s4,s5);
                        setListViewItem(3);

                    }
                });
            }
        },dataStore.getToken(), dataStore.getTeacher_id(), generatePaperDataStore.getCourseId(),String.valueOf(generatePaperDataStore.getQuesyionTypeId()),String.valueOf(currentPage));

    }
    //设置页面对应显示
    public void setInitAppearance(String text){
        questionKindLabel.setText(text);
        courseNameLLabel.setText("课程:"+generatePaperDataStore.getCourseName());
    }
    //设置所有Label面板
    public void setAllLabel(){
        checkedLabel.setText(String.valueOf(checkedCount));
        easyQuestionLabel.setText(String.valueOf(easyQuestionCount));
        midQuestionLabel.setText(String.valueOf(midQuestionCount));
        diffQuestionLabel.setText(String.valueOf(diffQuestionCount));
    }
    //清除数据
    public void clearCount(){
        checkedCount=0;
        easyQuestionCount=0;
        midQuestionCount=0;
        diffQuestionCount=0;
    }
    //获取列表中选中数据
    public List<SubjectDataRecord> getAddList() {
        return list.filtered(subjectDataRecord -> subjectDataRecord.getChecked()==true);
    }
    //关闭弹窗，恢复初始设置
    public void close(){
        cB.setSelected(false);
    }
    //为分页器增加监听事件
    public void addPageListener(){
        page.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                currentPage=newValue.intValue()+1;
                searchQuestion();
            }
        });
    }
    //全选框监听
    public void addCheckBoxListener(){
        cB.setSelected(false);
        cB.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue==true){
                listView.getItems().stream().forEach(question -> question.setChecked(true));
                checkedCount=listView.getItems().size();
                easyQuestionCount=listView.getItems().stream().filter(subjectDataRecord -> subjectDataRecord.getSubjectData().getDifficult()==1).count();
                midQuestionCount=listView.getItems().stream().filter(subjectDataRecord -> subjectDataRecord.getSubjectData().getDifficult()==2).count();
                diffQuestionCount=listView.getItems().stream().filter(subjectDataRecord -> subjectDataRecord.getSubjectData().getDifficult()==3).count();
            }else{
                if(listView.getItems().stream().anyMatch(subjectDataRecord -> subjectDataRecord.getChecked()==false)){

                }else{
                    listView.getItems().stream().forEach(subjectDataRecord -> subjectDataRecord.setChecked(false));
                    clearCount();
                }
            }
            setAllLabel();
            listView.refresh();
        });
    }
    //设置下拉选择框
    public void setComBoxItem(){
        comb.getItems().addAll("较易","中等","较难");
        comb.getSelectionModel().select(0);
    }
    //设置ListView大小，及列表绑定
    public void setListView(){
        listView.setStyle("-fx-fixed-cell-size:230");
        listView.setCellFactory(new Callback<ListView<SubjectDataRecord>, ListCell<SubjectDataRecord>>() {
            @Override
            public ListCell<SubjectDataRecord> call(ListView<SubjectDataRecord> param) {
                ListCell<SubjectDataRecord> cell=new ListCell<SubjectDataRecord>(){
                    @Override
                    protected void updateItem(SubjectDataRecord item, boolean empty) {
                        final ListCell cell=this;
                        super.updateItem(item, empty);
                        if(empty==false){
                            AnchorPane anchorPane=new AnchorPane();
                            CheckBox checkBox=new CheckBox();
                            checkBox.selectedProperty().bindBidirectional(new SimpleBooleanProperty(item.getChecked()));
                            checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                    if(newValue==true){
                                        item.setChecked(true);
                                        checkedCount++;
                                        if (item.getSubjectData().getDifficult()==1){
                                            easyQuestionCount++;
                                        }else if(item.getSubjectData().getDifficult()==2){
                                            midQuestionCount++;
                                        }else if(item.getSubjectData().getDifficult()==3){
                                            diffQuestionCount++;
                                        }
                                        Boolean bl=listView.getItems().stream().anyMatch(question -> question.getChecked()==false);
                                        if(bl==false){
                                            cB.setSelected(true);
                                        }
                                    }else{
                                        item.setChecked(false);
                                        checkedCount--;
                                        if (item.getSubjectData().getDifficult()==1){
                                            easyQuestionCount--;
                                        }else if(item.getSubjectData().getDifficult()==2){
                                            midQuestionCount--;
                                        }else if(item.getSubjectData().getDifficult()==3){
                                            diffQuestionCount--;
                                        }
                                        cB.setSelected(false);
                                    }
                                    setAllLabel();
                                    listView.refresh();
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
                            vBox.setMaxWidth(generatePaperSecondController.width*0.2);
                            vBox.setSpacing(10);
                            AnchorPane.setTopAnchor(vBox, 15.0);
                            AnchorPane.setLeftAnchor(vBox, 50.0);
                            AnchorPane.setRightAnchor(vBox, 15.0);
                            Label label3=new Label();
                            if(item.getSubjectData().getDifficult()==1){
                                label3.setText("较易");
                                label3.setStyle("-fx-text-fill: green;-fx-font-weight: bold");
                            }else if(item.getSubjectData().getDifficult()==2){
                                label3=new Label("中等");
                                label3.setStyle("-fx-text-fill: blue;-fx-font-weight: bold");
                            }
                            else if(item.getSubjectData().getDifficult()==3){
                                label3=new Label("较难");
                                label3.setStyle("-fx-text-fill: red;-fx-font-weight: bold");
                            }
                            HBox hBox=new HBox(label3);
                            hBox.setSpacing(5.0);
                            hBox.setAlignment(Pos.CENTER);
                            AnchorPane.setRightAnchor(hBox, 5.0);
                            AnchorPane.setTopAnchor(hBox, 0.0);
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
    //设置接收到response后一系列变化(页码变化)
    public void setListViewItem(int sumPage){
        listView.setItems(list);
        page.setPageCount(sumPage);
        listView.refresh();
    }
    //搜索题目
    public void searchQuestion(){
        if(!"".equals(searchValueField.getText().trim())){
            flag=1;
            if(!lastSearch.equals(searchValueField.getText().trim())){
                lastSearch=searchValueField.getText().trim();
                page.setCurrentPageIndex(0);
            }
            testPaperService.getQuesByCon(new retrofit2.Callback<SubjectInfo>() {
                @Override
                public void onResponse(Call<SubjectInfo> call, Response<SubjectInfo> response) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            list.clear();
                            response.body().getSubject().forEach(subjectData -> {
                                list.add(new SubjectDataRecord(subjectData, 3,false,false));
                            });
                            if(response.body().getTotal()%5==0){
                                setListViewItem(response.body().getTotal()/5);
                            }else{
                                setListViewItem(response.body().getTotal()/5+1);
                            }

                        }
                    });
                }
                @Override
                public void onFailure(Call<SubjectInfo> call, Throwable t) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            list.clear();
                            setListViewItem(3);
                        }
                    });
                }
            }, dataStore.getToken(), dataStore.getTeacher_id(), generatePaperDataStore.getCourseId(),String.valueOf(generatePaperDataStore.getQuesyionTypeId()), searchValueField.getText().trim(),String.valueOf(currentPage));
        }else{
            if(flag==1){
                page.setCurrentPageIndex(0);
                flag=0;
            }
            getQuestion();
        }
    }
}
