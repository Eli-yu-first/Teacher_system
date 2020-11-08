package com.hnust.controller.paper;

import com.hnust.view.paper.AutoPaper3View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import com.hnust.controller.MainController;
import de.felixroske.jfxsupport.FXMLController;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@FXMLController
public class AutoPaper2Controller implements Initializable {

    @Autowired
    MainController autoMainController2;
    @Autowired
    AutoPaper3View autoPaper3View;

    public void initialize(URL location, ResourceBundle resources) {

    }

    public void toAuto3() throws IOException {
        autoMainController2.skipView(autoPaper3View);
    }
}
