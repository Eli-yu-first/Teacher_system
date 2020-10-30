package com.hnust.controller.student;

import com.hnust.domain.Visual1;
import com.hnust.domain.Visual2;
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
import javafx.scene.control.Alert;
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
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

@FXMLController
public class OverallDataController implements Initializable {


    public AnchorPane overAllDataWindow;
    public FlowPane studentAccuracy;
    public FlowPane classSubjectCount;
    public BarChart<String,Number> charBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initPieChartAccuracy();
        initBarChartSubjectCount();
    }

    public void initPieChartAccuracy(){
        //假数据生成
        List<Visual2> visual2s=new ArrayList<>();
        Visual2 d1=new Visual2(200,793,"线性代数");
        Visual2 d2=new Visual2(330,793,"数字系统");
        visual2s.add(d1);visual2s.add(d2);

        //数据处理显示
        for(Visual2 visual2:visual2s){
            PieChart.Data data1=new PieChart.Data("错误率",visual2.getFalseTotal());
            PieChart.Data data2=new PieChart.Data("正确率",visual2.getRightTotal());
            ObservableList<PieChart.Data> datalist= FXCollections.observableArrayList();
            datalist.addAll(data1,data2);

            PieChart pieChart=new PieChart(datalist);
            pieChart.setTitle(visual2.getCourseName());
            pieChart.setLabelsVisible(false);//设置标签可见性
//            pieChart.setLegendVisible(true);//设置显示可见性
//            pieChart.setLegendSide(Side.RIGHT);//设置显示可见性
//            pieChart.setLabelLineLength(10);//设置线长
//            pieChart.setAnimated(true);//设置动画数据变换
//            pieChart.setStartAngle(90);//设置旋转角度
//            pieChart.setTitleSide(Side.RIGHT);//设置标题位置
            pieChart.getData().forEach(data -> {
                Node node=data.getNode();
                Tooltip tooltip=new Tooltip(data.getPieValue()+"");
                tooltip.setFont(new Font(15));
                Tooltip.install(node,tooltip);
            });
            studentAccuracy.getChildren().add(pieChart);
        }
    }

    public void initBarChartSubjectCount(){

        //假数据生成
        List<Visual1> visual1s=new ArrayList<>();
        Visual1 data1=new Visual1("18计算机科学与技术一班","线性代数",200);
        Visual1 data2=new Visual1("18计算机科学与技术二班","线性代数",230);
        Visual1 data3=new Visual1("18计算机科学与技术三班","线性代数",220);
        Visual1 data4=new Visual1("18计算机科学与技术四班","线性代数",100);
        Visual1 data5=new Visual1("18计算机科学与技术一班","数字系统",240);
        Visual1 data6=new Visual1("18计算机科学与技术二班","数字系统",212);
        Visual1 data7=new Visual1("18计算机科学与技术三班","数字系统",300);
        Visual1 data8=new Visual1("18计算机科学与技术四班","数字系统",120);

        visual1s.add(data1);visual1s.add(data2);visual1s.add(data3);visual1s.add(data4);
        visual1s.add(data5);visual1s.add(data6);visual1s.add(data7);visual1s.add(data8);

        LinkedHashSet<String> courseNames=new LinkedHashSet<>();
        List<XYChart.Series<String,Number>> xys=new ArrayList<>();
        //数据显示
        for(Visual1 visual1:visual1s){
            courseNames.add(visual1.getCourseName());
        }
        for(String courseName:courseNames){
            XYChart.Series<String,Number> xy=new XYChart.Series<>();
            xy.setName(courseName);
            xys.add(xy);
        }
        for(Visual1 visual1:visual1s){
            for(XYChart.Series<String,Number> series:xys){
                if(series.getName().equals(visual1.getCourseName())){
                    series.getData().add(
                            new XYChart.Data<String,Number>(visual1.getClassName(),visual1.getSubjectCount())
                    );
                }
            }
        }
        charBar.getData().addAll(xys);
        charBar.prefWidthProperty().bind(classSubjectCount.widthProperty());
    }
}
