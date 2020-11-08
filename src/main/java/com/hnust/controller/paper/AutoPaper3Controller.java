package com.hnust.controller.paper;

import com.hnust.controller.MainController;
import com.hnust.view.paper.AutoPaper2View;
import com.hnust.view.paper.AutoPaper3View;
import com.hnust.view.paper.AutoPaper4View;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class AutoPaper3Controller implements Initializable {

    @Autowired
    MainController autoMainController3;
    @Autowired
    AutoPaper2View autoPaper2View;
    @Autowired
    AutoPaper4View autoPaper4View;

    public void initialize(URL location, ResourceBundle resources) {

    }

    public void toAuto2() throws IOException {
        autoMainController3.skipView(autoPaper2View);
    }

    public void toAuto4() throws IOException {
        autoMainController3.skipView(autoPaper4View);
    }
}
