package com.hnust.controller.paper;

import com.hnust.view.paper.AutoPaperView;
import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * 创建人：zhangRuWang
 * 创建时间：2020/10/29
 * 描述：
 */
@FXMLController
public class AutoPaperController implements Initializable {
    @FXML
    ComboBox comboBox;
    @FXML
    RadioButton r1;
    @FXML
    RadioButton r2;

    public Button addQueType;
    public AnchorPane auto1,auto2;
    public Button btn;

    @FXML
    private Text target;

    public void initialize(URL location, ResourceBundle resources) {


        ObservableList com= FXCollections.observableArrayList();
        com.add("选择一");
        com.add("选择二");
        com.add("选择三");
        comboBox.setItems(com);

        //将单选框放在一个选择组中
        ToggleGroup toggleGroup=new ToggleGroup();
        r1.setToggleGroup(toggleGroup);
        r2.setToggleGroup(toggleGroup);
    }

    public void btnClick(MouseEvent mouseEvent) {
        auto1.setVisible(false);
        /*auto2.setVisible(true);*/
    }

    public void addQueTypeBtn(MouseEvent mouseEvent) {

        List<String> choices = new ArrayList<>();
        choices.add("填空");
        choices.add("选择");
        choices.add("判断");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("填空", choices);
        Optional<String> result =  dialog.showAndWait();
        dialog.setResizable(true);
        dialog.setTitle("添加题型");
        dialog.setHeaderText(null);
        dialog.setContentText("题型: ");
        dialog.setGraphic(null);
        dialog.setHeight(200);
        dialog.setWidth(300);

        if(result.get() == "填空") {

        }
    }
    /*public AutoPaperController() throws IOException {

        String data = "Hello World!";

        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/fxml/paper/autoPaper.fxml"));
        Parent root = loader.load();
        AutoPaperController controller = loader.<AutoPaperController>getController();
        controller.setData(data);
    }*/

    public void setData(String data) {
        target.setText(data);
    }

}
