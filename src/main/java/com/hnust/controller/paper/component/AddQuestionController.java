package com.hnust.controller.paper.component;

import com.hnust.controller.paper.GeneratePaperSecondController;
import com.hnust.domain.Question;
import com.hnust.domain.Visual1;
import com.hnust.view.paper.GeneratePaperSecondView;
import de.felixroske.jfxsupport.FXMLController;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
    @FXML
    public ListView<Question> listView;
    @FXML
    public Label label;
    private List<Question> addList=new ArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setItems(this.getQuestion());
        listView.setStyle("-fx-fixed-cell-size:230");
        listView.setCellFactory(new Callback<ListView<Question>, ListCell<Question>>() {
            @Override
            public ListCell<Question> call(ListView<Question> param) {
                ListCell<Question> cell=new ListCell<Question>(){
                    @Override
                    protected void updateItem(Question item, boolean empty) {
                        final ListCell cell=this;
                        super.updateItem(item, empty);
                        if(empty==false){
                            AnchorPane anchorPane=new AnchorPane();
                            CheckBox checkBox=new CheckBox();
                            checkBox.setSelected(item.getChecked());
                            checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                    if(newValue==true){
                                        item.setChecked(true);
                                        if(!addList.contains(item)){
                                            addList.add(item);
                                        }
                                    }else{
                                        item.setChecked(false);
                                        if(addList.contains(item)){
                                            addList.remove(item);
                                        }
                                    }
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
                            Label label3=new Label("较易");
                            label3.setStyle("-fx-text-fill: green;-fx-font-weight: bold");
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
    public ObservableList getQuestion(){
        Question q1=new Question(1,"我是谁","用不到",false);
        Question q2=new Question(2,"我是谁","用不到",false);
        Question q3=new Question(3,"我是谁","用不到",false);
        Question q4=new Question(4,"我是谁","用不到",false);
        Question q5=new Question(5,"我是谁","用不到",false);
        ObservableList list= FXCollections.observableArrayList();
        list.addAll(q1,q2,q3,q4,q5);
        return list;
    }
    public void setInitAppearance(String text){
        this.label.setText(text);
    }
    public List<Question> getAddList() {
        return addList;
    }
    public void setAddList(List<Question> addList) {
        this.addList = addList;
    }
}
