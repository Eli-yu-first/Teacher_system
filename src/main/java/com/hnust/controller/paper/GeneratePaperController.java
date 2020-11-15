package com.hnust.controller.paper;

import com.hnust.controller.MainController;
import com.hnust.domain.CourseData;
import com.hnust.domain.QuestionType;
import com.hnust.service.TestPaperService;
import com.hnust.store.DataStore;
import com.hnust.store.GeneratePaperDataStore;
import com.hnust.view.paper.GeneratePaperSecondView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

@FXMLController
public class GeneratePaperController implements Initializable {
    @Autowired
    private MainController mainController;
    @Autowired
    private GeneratePaperSecondView generatePaperSecondView;
    @Autowired
    private DataStore dataStore;
    @Autowired
    private GeneratePaperDataStore generatePaperDataStore;
    @Autowired
    private TestPaperService testPaperService;
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
    @FXML
    public DatePicker examTimeDataPicker;
    @FXML
    public ComboBox<String> courseNameCombox;
    @FXML
    public Label collegeNameLabel;
    @FXML
    public DatePicker outExamTimeDatePicker;
    @FXML
    public Label teacherNameLabel;
    @FXML
    public TextField passRateTextField;
    @FXML
    public RadioButton aExamRadioButton;
    @FXML
    public RadioButton bExamRadioButton;
    private ToggleGroup toggleGroup=new ToggleGroup();
    private ObservableList<String> list= FXCollections.observableArrayList();
    private List<CourseData> allList=new ArrayList<>();
    private String defaultValue="暂未加入课程组";
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataStore.setCollege_id("1");
        dataStore.setCollege_name("计算机科学与工程学院");
        dataStore.setTeacher_id("1");
        dataStore.setTeacher_name("qian");
        dataStore.setToken("12");
        getData();
        listenChange();
        initApparent();
    }
    //根据窗口改变，进行监听设置页面大小
    public void listenChange(){
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
    //TODO 初始化页面 (考试年级、专业)
    public void initApparent(){
        aExamRadioButton.setToggleGroup(toggleGroup);
        bExamRadioButton.setToggleGroup(toggleGroup);
        toggleGroup.selectToggle(aExamRadioButton);
        collegeNameLabel.setText(dataStore.getCollege_name());
        teacherNameLabel.setText(dataStore.getTeacher_name());
    }
    //TODO 收集表单中所有数据，并提交到store中（收集考试年级、专业）
    public Boolean collectData(){
        if(!allIsEmpty()){
            generatePaperDataStore.setExamTimeData(examTimeDataPicker.getValue());
            for(CourseData courseData:allList){
                if(courseData.getName()==courseNameCombox.getValue()){
                    generatePaperDataStore.setCourseId(courseData.getId());
                    break;
                }
            }
            generatePaperDataStore.setCourseName(courseNameCombox.getValue());
            generatePaperDataStore.setOutExamTime(outExamTimeDatePicker.getValue());
            generatePaperDataStore.setPassRate(passRateTextField.getText());
            generatePaperDataStore.setPaperKind(((RadioButton)toggleGroup.getSelectedToggle()).getText());
            return true;
        }
        return false;
    }
    //清空所有表单数据,以及store中数据
    public void clearData(){
        examTimeDataPicker.setValue(null);
        outExamTimeDatePicker.setValue(null);
        courseNameCombox.setItems(null);
        passRateTextField.setText("");
    }
    //为页面相关表单发送请求获取数据并设置combox
    public void getData(){
        //获取老师所加入课程的信息
        testPaperService.getCourseData(new Callback<List<CourseData>>() {
            @Override
            public void onResponse(Call<List<CourseData>> call, Response<List<CourseData>> response) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        allList.clear();
                        allList=response.body();
                        Iterator<CourseData> iterator=response.body().iterator();
                        while(iterator.hasNext()){
                            list.add(iterator.next().getName());
                        }
                        setCourseNameCombox();
                    }
                });
            }
            @Override
            public void onFailure(Call<List<CourseData>> call, Throwable t) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        allList.clear();
                        allList.add(new CourseData("网络异常","1"));
                        allList.add(new CourseData("数据结构","2"));
                        allList.add(new CourseData("操作系统","3"));
                        allList.add(new CourseData("计算机网络","4"));
                        list.addAll("网络异常","操作系统","数据结构","计算机网络");
                        setCourseNameCombox();
                    }
                });
            }
        }, dataStore.getTeacher_id(), dataStore.getToken());

    }
    //TODO 判断应填数据是否为空 （考试年级、专业）
    public Boolean allIsEmpty(){
        if(examTimeDataPicker.getValue()!=null
                &&courseNameCombox.getValue()!=null&&courseNameCombox.getValue()!=defaultValue
                &&outExamTimeDatePicker.getValue()!=null
                &&!"".equals(passRateTextField.getText().trim())
                &&toggleGroup.selectedToggleProperty()!=null){
            return false;
        }
        return true;
    }
    //初始化Combox
    public void setCourseNameCombox(){
        if(!list.isEmpty()){
            courseNameCombox.setItems(list);
        }else {
            courseNameCombox.getItems().add(defaultValue);
        }
        courseNameCombox.getSelectionModel().select(0);
    }
    //保存并且进入下一步
    public void next() throws IOException {
        if(collectData()){
            //mainController.skipView(generatePaperSecondView);
        }
    }
}
