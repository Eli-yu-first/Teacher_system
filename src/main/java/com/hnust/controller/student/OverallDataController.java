package com.hnust.controller.student;

import com.hnust.view.student.OverallDataView;
import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class OverallDataController implements Initializable {


    public AnchorPane overAllDataWindow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        overAllDataWindow.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("???"+newValue);
            }
        });
        //System.out.println(((AnchorPane)overallDataView.getView()).getWidth());
    }
}
