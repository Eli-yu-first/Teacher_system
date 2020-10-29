package com.hnust.controller.student;

import com.hnust.view.student.OverallDataView;
import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

@FXMLController
public class OverallDataController implements Initializable {


    public AnchorPane overAllDataWindow;
    public FlowPane studentAccuracy;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initPieChartAccuracy();
//        overAllDataWindow.widthProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                System.out.println("???"+newValue);
//            }
//        });
        //System.out.println(((AnchorPane)overallDataView.getView()).getWidth());
    }

    public void initPieChartAccuracy(){
        PieChart.Data d1=new PieChart.Data("错误率",20);
        PieChart.Data d2=new PieChart.Data("正确率",80);
        PieChart.Data d3=new PieChart.Data("错误率",20);
        PieChart.Data d4=new PieChart.Data("正确率",80);
        ObservableList<PieChart.Data> datalist= FXCollections.observableArrayList();
        datalist.addAll(d1,d2);
        ObservableList<PieChart.Data> datalist2= FXCollections.observableArrayList();
        datalist2.addAll(d3,d4);
        PieChart pieChart=new PieChart(datalist);
        PieChart pieChart2=new PieChart(datalist2);
        setPieChartCss(pieChart,"线性代数");
        setPieChartCss(pieChart2,"数字系统");
        studentAccuracy.getChildren().add(pieChart);
        studentAccuracy.getChildren().add(pieChart2);
    }

    public void setPieChartCss(PieChart pieChart,String title){
        pieChart.setPrefSize(250,250);
        pieChart.setLabelsVisible(false);//设置标签可见性
//        pieChart.setLegendVisible(true);//设置显示可见性
//        pieChart.setLegendSide(Side.RIGHT);//设置显示可见性
//        pieChart.setLabelLineLength(10);//设置线长
//        pieChart.setAnimated(true);//设置动画数据变换
//        //pieChart.setStartAngle(90);//设置旋转角度
        pieChart.setTitle(title);//设置标题

//        //pieChart.setTitleSide(Side.RIGHT);//设置标题位置
//        pieChart.getData().forEach(data -> {
//            Node node=data.getNode();
//            node.setOnMouseClicked(event -> {
//                Tooltip tooltip=new Tooltip(data.getPieValue()+"");
//                tooltip.setFont(new Font(20));
//                Tooltip.install(node,tooltip);
//            });
//        });

    }
}
