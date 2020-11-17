package com.hnust.controller.paper.component;

import com.hnust.controller.personInfo.PersonDataController;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;

import javafx.scene.control.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * 创建人：zhangRuWang
 * 创建时间：2020/10/29
 * 描述：
 */
@FXMLController
public class EditInfoController implements Initializable {
    @Autowired
    PersonDataController personDataController;
    @FXML
    public TextField teacherNameEdit;
    @FXML
    public TextField teacherPhoneEdit;
    @FXML
    public TextField teacherMailEdit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public Map<String,String> aaa() {
        Map<String,String> map=new HashMap<>();
        map.put("phone",teacherPhoneEdit.getText());
        map.put("mail",teacherMailEdit.getText());
        return map;
    }
}