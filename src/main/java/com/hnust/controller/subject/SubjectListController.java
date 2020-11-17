package com.hnust.controller.subject;

import com.hnust.controller.MainController;
import com.hnust.domain.CourseData;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class SubjectListController implements Initializable {

    @FXML
    private AnchorPane subjectListWindow;
    @FXML
    TableView tableSize;

    @FXML
    private ComboBox<CourseData> comb;

    @Autowired
    private MainController mainController;

    //private String selectedCourseId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        subjectListWindow.parentProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                getAllCourses();
            }else{
            }
        });
        //System.out.println("只运行一次？");

    }

    public void getAllCourses() {
        //System.out.println("每次运行一次？？");
        //假数据
        CourseData c1=new CourseData("高等数学","342341");
        CourseData c2=new CourseData("操作系统","135234");
        CourseData c3=new CourseData("大学英语","237461");

        ObservableList<CourseData> com= FXCollections.observableArrayList();
        com.addAll(c1,c2,c3);

        comb.setItems(com);
        comb.setConverter(new StringConverter<CourseData>() {
            @Override
            public String toString(CourseData object) {
                return object.getName();
            }

            @Override
            public CourseData fromString(String string) {
                return null;
            }
        });

        comb.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //System.out.println("选择的值为："+newValue);
            //selectedCourseId= newValue.getId();
        });
    }

    public void shiftToBatchImport(ActionEvent actionEvent) {
        mainController.skipView("批量导入");
    }
}
