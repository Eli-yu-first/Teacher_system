package com.hnust.controller.subject;

import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import javax.xml.bind.annotation.XmlList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

@FXMLController
public class BatchImportController implements Initializable {
    private ObservableList playListFiles =FXCollections.observableArrayList();
    @FXML
    AnchorPane container;
    @FXML
    ComboBox comb;
    //顶部提醒框
    @FXML
    public VBox remind;
    @FXML
    public TableView tableSize;
    //试卷框
   // @FXML
   // Button btn1;
   // @FXML
    //Button btn2;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tableSize.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

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
    /*public void downloadFile(ActionEvent event) throws IOException {

        File dirFile = new File(filePath);
        //文件路径不存在时，自动创建目录
        if(!dirFile.exists()){
            dirFile.mkdir();
        }

        URLConnection connection = theURL.openConnection();
        InputStream in = connection.getInputStream();
        // test.txt 这里的text与下载文件名称最好一致
        FileOutputStream os = new FileOutputStream(filePath+"\\test.txt");
        byte[] buffer = new byte[4 * 1024];
        int read;
        while ((read = in.read(buffer)) > 0) {
            os.write(buffer, 0, read);
        }
        os.close();
        in.close();
    }
*/

}
