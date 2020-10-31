package com.hnust.controller.paper;

import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;
import com.hnust.domain.Data;

import com.hnust.mock.DataList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import javax.security.auth.Subject;
import javax.swing.text.TabableView;
import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class PaperListController implements Initializable {
    ObservableList<Data> list=DataList.initData();
    @FXML
    TableView tableView;
    @FXML
    TableColumn columnSubject;
    @FXML
    TableColumn columnPerson;
    @FXML
    TableColumn columnTime;
    @FXML
    TableColumn columnOperate;
    @FXML
    Pagination page;
//    @FXML
//    Button btn;
    @FXML
    TextField toPage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableView.setItems(list);
        columnSubject.setCellValueFactory(new PropertyValueFactory<Data,String>("Subject"));
        columnPerson.setCellValueFactory(new PropertyValueFactory<Data,String>("Person"));
        columnTime.setCellValueFactory(new PropertyValueFactory<Data,String>("Time"));
        columnOperate.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                TableCell cell=new TableCell(){
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty==false){
                            HBox hBox=new HBox();
                            hBox.setAlignment(Pos.CENTER);
                            hBox.setSpacing(10);
                            Hyperlink h2=new Hyperlink("预览");
                            Hyperlink h3=new Hyperlink("设为样卷");
                            Hyperlink h1=new Hyperlink("删除");
                            h1.getStyleClass().add("color");
                            hBox.getChildren().addAll(h2,h3,h1);
                            this.setGraphic(hBox);
                        }
                    }
                };
                return cell;
            }
        });
        tableView.getColumns().addAll(columnSubject,columnPerson,columnTime,columnOperate);
        toPage.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String pageNum=toPage.getText().trim();
                if(pageNum!=null&&!"".equals(pageNum)&&pageNum.trim().matches("^[0-9]*$")&&event.getCode()== KeyCode.ENTER){
                    page.setCurrentPageIndex(Integer.valueOf(pageNum)-1);
                    System.out.println("To Page : "+toPage.getText());
                }
            }
        });
        page.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("Current Page : "+(newValue.intValue()+1));
            }
        });
    }
}
