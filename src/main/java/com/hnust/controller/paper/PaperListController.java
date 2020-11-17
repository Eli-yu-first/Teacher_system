package com.hnust.controller.paper;

import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import com.hnust.domain.Data;

import com.hnust.mock.DataList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import lombok.val;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class PaperListController implements Initializable {
    ObservableList<Data> list=DataList.initData();
    @FXML
    TableView tableView;
    @FXML
    TableColumn columnChoice;
//    @FXML
//    private TableColumn<Data,Boolean> columnChoice;
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
    @FXML
    TextField toPage;
    SimpleBooleanProperty flag=new SimpleBooleanProperty(false);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableView.setItems(list);
        CheckBox checkBox=new CheckBox();
        //为多选框设置双向绑定
        checkBox.selectedProperty().bindBidirectional(flag);
        //全选/取消全选
        checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                TableColumn tableColumn= (TableColumn) tableView.getColumns().get(0);
                for (Object Data: tableView.getItems()) {
                    Data data = (Data) Data;
                    data.setChecked(newValue);
                }
            }
        });
        columnChoice.setGraphic(checkBox);//重新设置表头格式
        columnChoice.setSortable(false);//排序功能关闭
        columnChoice.setCellValueFactory(new PropertyValueFactory<Data,Boolean>("checked"));
        columnChoice.setCellFactory(new Callback<TableColumn<Data,Boolean>, TableCell<Data, Boolean>>() {
            @Override
            public TableCell<Data, Boolean> call(TableColumn<Data, Boolean> param) {
                TableCell<Data,Boolean> cell=new TableCell<Data,Boolean>(){
                    @Override
                    protected void updateItem(Boolean item, boolean empty) {
                        final TableCell cell=this;
                        super.updateItem(item, empty);
                        if(empty==false){
                            HBox hBox=new HBox();
                            hBox.setAlignment(Pos.CENTER);
                            CheckBox checkBox=new CheckBox();
                            checkBox.setSelected(item);
                            //给CheckBox添加变化事件
                            //TODO
                            checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                    Data data= (Data) cell.getTableRow().getItem();
                                    data.setChecked(newValue);
                                    int check=1;
                                    for(Object Data:tableView.getItems()){
                                        Data fdata=(Data) Data;
                                        if(fdata.isChecked()==false){
                                            check=0;
                                            break;
                                        }
                                    }
                                    if(check==0){
                                        flag.set(false);
                                    }else{
                                        flag.set(true);
                                    }
                                    System.out.println(cell.getTableRow().getItem().toString());
                                    System.out.println("CheckBox.Index: "+cell.getIndex());
                                }
                            });
                            hBox.getChildren().add(checkBox);
                            this.setGraphic(hBox);
                        }
                    }
                };
                return cell;
            }
        });
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
                            h1.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    val remove=list.remove(getIndex());
                                    System.out.println(remove);
                                    tableView.refresh();
                                }
                            });
                            h1.getStyleClass().add("color");
                            hBox.getChildren().addAll(h2,h3,h1);
                            this.setGraphic(hBox);
                        }else {
                            this.setGraphic(null);
                        }
                    }
                };
                return cell;
            }
        });
        columnChoice.setPrefWidth(20);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);//表格自适应
        tableView.setTableMenuButtonVisible(true);
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
    }

    public void delete(ActionEvent actionEvent) {

    }

}
