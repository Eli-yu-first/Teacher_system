package com.hnust.controller.paper.component;

import com.hnust.domain.QuestionType;
import com.hnust.service.TestPaperService;
import com.hnust.store.DataStore;
import com.hnust.store.GeneratePaperDataStore;
import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @program: demo
 * @author: 彭鑫淼
 * @description: 未知
 * @create: 2020-11-01 12:51
 */
@FXMLController
public class AddPaperKindController implements Initializable {
    @Autowired
    private TestPaperService testPaperService;
    @Autowired
    private DataStore dataStore;
    @Autowired
    private GeneratePaperDataStore generatePaperDataStore;
    @FXML
    public ComboBox comb;
    private String kind;
    private ObservableList<String> list= FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setComb();
        setCombKind();
    }
    //获取课程所对应的题目类型
    public void setCombKind() {
        testPaperService.getCourseQuesList(new Callback<List<QuestionType>>() {
            @Override
            public void onResponse(Call<List<QuestionType>> call, Response<List<QuestionType>> response) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        generatePaperDataStore.setQuestionTypes(response.body());
                        response.body().forEach(questionType -> {
                            list.add(questionType.getName());
                        });
                        setCombItem();
                        System.out.println(generatePaperDataStore);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<QuestionType>> call, Throwable t) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        QuestionType q1=new QuestionType(1, "选择题");
                        QuestionType q2=new QuestionType(2, "判断题");
                        QuestionType q3=new QuestionType(3, "简答题");
                        List<QuestionType> list1=new ArrayList<>();
                        list1.add(q1);
                        list1.add(q2);
                        list1.add(q3);
                        generatePaperDataStore.setQuestionTypes(list1);
                        list.addAll("选择题","判断题","简答题");
                        setCombItem();
                    }
                });
            }
        }, dataStore.getTeacher_id(), generatePaperDataStore.getCourseId(), dataStore.getToken());
    }
    //设置combox中的属性
    public void setComb(){
        comb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                kind= (String) newValue;
            }
        });
    }
    //设置combox中数据
    public void setCombItem(){
        if (!list.isEmpty()){
            comb.getItems().addAll(list);
        }else{
            comb.getItems().add("网络异常");
        }
        comb.getSelectionModel().select(0);
    }
    public String getKind() {
        return kind;
    }
}
