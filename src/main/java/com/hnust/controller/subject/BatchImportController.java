package com.hnust.controller.subject;

import com.hnust.api.TestPaperApi;
import com.hnust.controller.subject.component.AnswerWindowPaperController;
import com.hnust.controller.subject.component.WindowPaperController;
import com.hnust.domain.Data;
import com.hnust.domain.Result;
import com.hnust.mock.DataList;
import com.hnust.util.IDCell;
import com.hnust.view.subject.component.AnswerWindowPaperView;
import com.hnust.view.subject.component.WindowPaperView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import retrofit2.Call;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;
import java.util.ResourceBundle;

@FXMLController
public class BatchImportController implements Initializable {
    ObservableList<Data> list= DataList.initData();

    @Autowired
    TestPaperApi testPaperApi;

    private ObservableList playListFiles =FXCollections.observableArrayList();
    //查看答案
    @Autowired
    private AnswerWindowPaperController answerWindowPaperController;
    @Autowired
    private AnswerWindowPaperView answerWindowPaperView;
    //修改题目
    @Autowired
    private WindowPaperController windowPaperController;
    @Autowired
    private WindowPaperView windowPaperView;
    @FXML
    AnchorPane container;
    @FXML
    ComboBox comb;
    //顶部提醒框
    @FXML
    public VBox remind;
    @FXML
    public TableView tableSize;

    //全选标记位置
    SimpleBooleanProperty flag=new SimpleBooleanProperty(false);

    private int helpVar = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableSize.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //设置表格中数据内容
        tableSize.setItems(list);
        //设置第一列（全选）
        TableColumn<Data,Boolean> tc_chk=new TableColumn<>("全选");
        CheckBox checkBox=new CheckBox();
        //为多选框设置双向绑定
        checkBox.selectedProperty().bindBidirectional(flag);
        //全选/取消全选
        checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(helpVar==1){
                        helpVar=0;
                    }else {
                        //TableColumn tableColumn = (TableColumn) tableSize.getColumns().get(0);
                        for (Object Data : tableSize.getItems()) {
                            Data data = (Data) Data;
                            data.setChecked(newValue);
                        }
                    }

            }
        });
        tc_chk.setGraphic(checkBox);//表头全选框
        tc_chk.setSortable(false);
        tc_chk.setCellValueFactory(new PropertyValueFactory<Data,Boolean>("checked"));
        tc_chk.setCellFactory(new Callback<TableColumn<Data, Boolean>, TableCell<Data, Boolean>>() {
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
                            checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                                @Override
                                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                    Data data= (Data) cell.getTableRow().getItem();
                                    data.setChecked(newValue);
                                    int check=1;
                                    for(Object fData:tableSize.getItems()){
                                        Data faa=(Data) fData;
                                        if(faa.isChecked()==false){
                                            check=0;
                                            break;
                                        }
                                    }
                                    if(check==0){
                                        helpVar = 1;
                                        flag.set(false);
                                        helpVar = 0;
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
        tc_chk.setPrefWidth(20);
        //设置第列二（序号）
        TableColumn tc_mark=new TableColumn("序号");
        tc_mark.setCellFactory(new IDCell<>());
        //设置第三列（内容）
        TableColumn<Data,String> tc_content=new TableColumn<Data,String>("内容");
        tc_content.setCellValueFactory(new PropertyValueFactory<Data,String>("Content"));
        //设置第四列（所属章节）
        TableColumn<Data,String> tc_chapter=new TableColumn<Data,String>("所属章节");
        tc_chapter.setCellValueFactory(new PropertyValueFactory<Data,String>("Chapter"));
        //设置第五列（题目类型）
        TableColumn<Data,String> tc_type=new TableColumn<Data,String>("题目类型");
        tc_type.setCellValueFactory(new PropertyValueFactory<Data,String>("Type"));
        //设置第六列（题目难度）
        TableColumn<Data,String> tc_difficulty=new TableColumn<Data,String>("题目难度");
        tc_difficulty.setCellValueFactory(new PropertyValueFactory<Data,String>("Difficulty"));
        //设置第七列（题目解析）
        TableColumn<Data,String> tc_parsing=new TableColumn<Data,String>("题目解析");
        tc_parsing.setCellValueFactory(new PropertyValueFactory<Data,String>("Parsing"));
        //设置第八列（知识点）
        TableColumn<Data,String> tc_knowledge=new TableColumn<Data,String>("知识点");
        tc_knowledge.setCellValueFactory(new PropertyValueFactory<Data,String>("Knowledge"));
        //设置第九列（答案）
//        TableColumn<Data,String> tc_answer=new TableColumn<Data,String>("答案");
//        tc_answer.setCellValueFactory(new PropertyValueFactory<Data,String>("Answer"));
        TableColumn tc_answer=new TableColumn("答案");
        tc_answer.setCellFactory(new Callback<TableColumn, TableCell>() {
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
                            Hyperlink h=new Hyperlink("查看答案");
                            //点击弹窗
                            h.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    Dialog dialog=new Dialog();
                                    DialogPane dialogPane=new DialogPane();
                                    dialogPane.setStyle("-fx-background-color: #FFF;-fx-border-width: 2;-fx-border-color: #ADADAD");
                                    dialogPane.setContent(answerWindowPaperView.getView());
                                    dialog.setDialogPane(dialogPane);
                                    dialog.setTitle("答案详情");
                                    dialog.initStyle(StageStyle.UNDECORATED);
                                    dialog.setGraphic(null);
                                    ButtonType ok=new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
                                    ButtonType cancel=new ButtonType("取消", ButtonBar.ButtonData.CANCEL_CLOSE);
                                    dialog.getDialogPane().getButtonTypes().addAll(ok,cancel);
                                    Optional<ButtonType> result=dialog.showAndWait();
                                    if(result.get()==ok){
                                        //System.out.println(answerWindowPaperController.getKind());
                                        //addKind(windowPaperController.getKind());
                                    }
                                }
                            });
                            hBox.getChildren().addAll(h);
                            this.setGraphic(hBox);
                        }
                    }
                };
                return cell;
            }
        });
        //设置第十列（课程目标）
        TableColumn<Data,String> tc_objectives=new TableColumn<Data,String>("课程目标");
        tc_objectives.setCellValueFactory(new PropertyValueFactory<Data,String>("Objectives"));
        //设置第十一列（权限）
        TableColumn<Data,String> tc_permissions=new TableColumn<Data,String>("权限");
        tc_permissions.setCellValueFactory(new PropertyValueFactory<Data,String>("Permissions"));
        //设置第十二列（操作）
       /* TableColumn<Data,String> tc_operation=new TableColumn<Data,String>("操作");
        tc_operation.setCellValueFactory(new PropertyValueFactory<Data,String>("Operation"));*/
        TableColumn tc_operation=new TableColumn("操作");
        tc_operation.setCellFactory(new Callback<TableColumn, TableCell>() {
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
                            Hyperlink h1=new Hyperlink("详情");
                            //增加详情弹窗
                            h1.setOnAction(new EventHandler<ActionEvent>(){
                                @Override
                                public void handle(ActionEvent event) {
                                    Dialog dialog=new Dialog();
                                    DialogPane dialogPane=new DialogPane();
                                    dialogPane.setStyle("-fx-background-color: #FFF;-fx-border-width: 2;-fx-border-color: #ADADAD");
                                    dialogPane.setContent(windowPaperView.getView());
                                    dialog.setDialogPane(dialogPane);
                                    //dialog.setTitle("题型添加");
                                    dialog.initStyle(StageStyle.UNDECORATED);
                                    dialog.setGraphic(null);
                                    ButtonType ok=new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
                                    ButtonType cancel=new ButtonType("取消", ButtonBar.ButtonData.CANCEL_CLOSE);
                                    dialog.getDialogPane().getButtonTypes().addAll(ok,cancel);
                                    Optional<ButtonType> result=dialog.showAndWait();
                                    if(result.get()==ok){
                                        System.out.println(windowPaperController.getKind());
                                        //addKind(windowPaperController.getKind());
                                    }
                                }
                            });
                            Hyperlink h2=new Hyperlink("删除");
                            h2.setOnAction(new EventHandler<ActionEvent>(){
                                @Override
                                public void handle(ActionEvent event) {
                                    val remove = list.remove(getIndex());
                                    System.out.println(remove);
                                    tableSize.refresh();
                                }
                            });
                            h1.getStyleClass().add("color");
                            Hyperlink h3=new Hyperlink("修改");
                            //增加修改弹窗
                            h3.setOnAction(new EventHandler<ActionEvent>(){
                                @Override
                                public void handle(ActionEvent event) {
                                    Dialog dialog=new Dialog();
                                    DialogPane dialogPane=new DialogPane();
                                    dialogPane.setStyle("-fx-background-color: #FFF;-fx-border-width: 2;-fx-border-color: #ADADAD");
                                    dialogPane.setContent(windowPaperView.getView());
                                    dialog.setDialogPane(dialogPane);
                                    dialog.setTitle("修改");
                                    dialog.initStyle(StageStyle.UNDECORATED);
                                    dialog.setGraphic(null);
                                    ButtonType ok=new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
                                    ButtonType cancel=new ButtonType("取消", ButtonBar.ButtonData.CANCEL_CLOSE);
                                    dialog.getDialogPane().getButtonTypes().addAll(ok,cancel);
                                    Optional<ButtonType> result=dialog.showAndWait();
                                    if(result.get()==ok){
                                        System.out.println(windowPaperController.getKind());
                                        //addKind(windowPaperController.getKind());
                                    }
                                }
                            });
                            hBox.getChildren().addAll(h1,h2,h3);
                            this.setGraphic(hBox);
                        }
                    }
                };
                return cell;
            }
        });
        tableSize.getColumns().addAll(tc_chk,tc_mark,tc_content,tc_chapter,tc_type,tc_difficulty,tc_parsing,
                tc_knowledge,tc_answer,tc_objectives,tc_permissions,tc_operation);

        ObservableList com= FXCollections.observableArrayList();
        com.add("操作系统");
        com.add("线性代数");
        com.add("高等数学");
        comb.setItems(com);
        /*comb.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView param) {
                ListCell listCell=new ListCell(){
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty==false){
                            Label label=new Label((String) item);
                            label.setStyle("-fx-font-size: 1.5em");
                            this.setGraphic(label);
                        }else{
                            this.setGraphic(null);
                        }
                    }
                };
                return listCell;
            }
        });*/
    }
   //根据窗口改变，进行监听设置页面大小
    public void listenChange(){
        //通过监听顶部提醒框改变宽度
        container.widthProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                remind.setPrefWidth((double)newValue-2);
            }
        });
    }
    public void chooseFile(ActionEvent event) {
        FileChooser fileChooser=new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Text Files", "*.txt");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        System.out.println(file.getPath());
    }
    public void downloadFile(ActionEvent event) throws IOException {

        File dirFile = new File(System.getProperty("user.dir"));
        //文件路径不存在时，自动创建目录
        if(!dirFile.exists()){
            dirFile.mkdir();
        }
        Call<Result<File>> result = testPaperApi.getTem();
        Result<File> resultFile = result.execute().body();
        File file = resultFile.getData();
        FileInputStream in = new FileInputStream(file);
        // 这里的text与下载文件名称最好一致
        FileOutputStream os = new FileOutputStream(System.getProperty("user.dir")+file.getName());
        byte[] buffer = new byte[4 * 1024];
        int read;
        while ((read = in.read(buffer)) > 0) {
            os.write(buffer, 0, read);
        }
        os.close();
        in.close();
    }


}
