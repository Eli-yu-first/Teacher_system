package com.hnust.controller.student;

import com.hnust.view.student.OverallDataView;
import com.sun.org.apache.xerces.internal.impl.dv.dtd.NMTOKENDatatypeValidator;
import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

@FXMLController
public class OverallDataController implements Initializable {


    public AnchorPane overAllDataWindow;
    public FlowPane studentAccuracy;
    public FlowPane classSubjectCount;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initPieChartAccuracy();
        initBarChartSubjectCount();
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

    public void initBarChartSubjectCount(){
        //String[] colors={"#409eff","#69d389","#BFBFBF","#FFBF00"};
        CategoryAxis x=new CategoryAxis();
        //x.setLabel("国家");
        x.setStartMargin(50);x.setEndMargin(50);//左边右边的空间
        //x.setGapStartAndEnd(false);//是否设置前后留的空间
        x.setSide(Side.LEFT);x.setTickLabelFill(Paint.valueOf("#ff55ff"));
        //x.setTickLabelFont(new Font(16));
        //x.setTickLabelGap(0);
        //x.setTickLabelsVisible(false);//是否显示名称
        //x.setTickLength(100);
        x.setTickMarkVisible(false);//是否显示刻度

        NumberAxis y = new NumberAxis(0,100,10);
        y.setLabel("做题总数");

        XYChart.Series<String,Number> xy1=new XYChart.Series<>();
        xy1.setName("线性代数");
        XYChart.Series<String,Number> xy2=new XYChart.Series<>();
        xy2.setName("数字系统");

        XYChart.Data<String,Number> d1=new XYChart.Data<String, Number>("中国",200);
        XYChart.Data<String,Number> d2=new XYChart.Data<String, Number>("美国",58);
        XYChart.Data<String,Number> d3=new XYChart.Data<String, Number>("日本",58);
        XYChart.Data<String,Number> d4=new XYChart.Data<String, Number>("中国",58);
        XYChart.Data<String,Number> d5=new XYChart.Data<String, Number>("日本",58);
        XYChart.Data<String,Number> d6=new XYChart.Data<String, Number>("美国",58);

        xy1.getData().addAll(d1,d2,d3);
        xy2.getData().addAll(d4,d5,d6);

        xy1.getData().forEach(new Consumer<XYChart.Data<String, Number>>() {
            @Override
            public void accept(XYChart.Data<String, Number> stringNumberData) {
                HBox h1=new HBox();
                h1.setAlignment(Pos.CENTER);
                h1.setStyle("-fx-background-color: #409eff");
                stringNumberData.setNode(h1);
//                stringNumberData.getNode().setOnMouseClicked(new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent event) {
//                        System.out.println(stringNumberData.getXValue()+stringNumberData.getYValue());
//                    }
//                });
            }
        });
        xy2.getData().forEach(new Consumer<XYChart.Data<String, Number>>() {
            @Override
            public void accept(XYChart.Data<String, Number> stringNumberData) {
                HBox h1=new HBox();
                h1.setAlignment(Pos.CENTER);
                h1.setStyle("-fx-background-color: #69d389");
                stringNumberData.setNode(h1);
            }
        });

        BarChart<String,Number> barChart=new BarChart<>(x,y);
        barChart.getData().add(xy1);
        barChart.getData().add(xy2);
        barChart.prefWidthProperty().bind(classSubjectCount.widthProperty());
        barChart.setPrefHeight(300);
        classSubjectCount.getChildren().add(barChart);
    }
}
