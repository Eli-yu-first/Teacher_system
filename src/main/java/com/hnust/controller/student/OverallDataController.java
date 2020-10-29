package com.hnust.controller.student;

import com.hnust.view.student.OverallDataView;
import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class OverallDataController implements Initializable {


    public AnchorPane overAllDataWindow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        PieChart.Data d1=new PieChart.Data("1",10);
        PieChart.Data d2=new PieChart.Data("2",60);
        PieChart.Data d3=new PieChart.Data("3",20);
        PieChart.Data d4=new PieChart.Data("4",10);

        ObservableList<PieChart.Data> datalist= FXCollections.observableArrayList();
        datalist.add(d1);datalist.add(d2);datalist.add(d3);datalist.add(d4);
        PieChart pieChart=new PieChart(datalist);
        overAllDataWindow.getChildren().add(pieChart);
        AnchorPane.setTopAnchor(pieChart,200.0);
//        overAllDataWindow.widthProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                System.out.println("???"+newValue);
//            }
//        });
        //System.out.println(((AnchorPane)overallDataView.getView()).getWidth());
    }
}
