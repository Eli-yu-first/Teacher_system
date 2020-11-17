package com.hnust.controller;

import com.hnust.store.DataStore;
import com.hnust.view.paper.*;
import com.hnust.view.personInfo.MyCourseGroupView;
import com.hnust.view.personInfo.PersonDataView;
import com.hnust.view.student.DetailDataView;
import com.hnust.view.student.OverallDataView;
import com.hnust.view.subject.BatchImportView;
import com.hnust.view.subject.SubjectListView;
import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.GUIState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class MainController implements Initializable{
    @FXML
    private Label navFont;
    @FXML
    private AnchorPane mainWindow;
    @FXML
    private AnchorPane content;
//    @Autowired
//    private DataStore dataStore;

    public ImageView header4Item3Image;
    public ImageView header1Item1Image;
    public ImageView header1Item2Image;
    public ImageView header2Item1Image;
    public ImageView header2Item2Image;
    public ImageView header3Item2Image;
    public ImageView header3Item1Image;
    public ImageView header4Item1Image;
    public ImageView header4Item2Image;

    @Autowired
    private GeneratePaperView generatePaperView;
    @Autowired
    private PaperListView paperListView;
    @Autowired
    private MyCourseGroupView myCourseGroupView;
    @Autowired
    private PersonDataView personDataView;
    @Autowired
    private DetailDataView detailDataView;
    @Autowired
    private OverallDataView overallDataView;
    @Autowired
    private BatchImportView batchImportView;
    @Autowired
    private SubjectListView subjectListView;
    @Autowired
    private AutoPaperView autoPaperView;
    @Autowired
    private GeneratePaperSecondView generatePaperSecondView;
    @Autowired
    private GeneratePaperThirdView generatePaperThirdView;
    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //设置窗口自适应
        Screen s=Screen.getPrimary();
        Rectangle2D visualBound=s.getVisualBounds();
        Stage stage = GUIState.getStage();
        stage.setMinWidth(visualBound.getWidth()*0.9);
        stage.setMinHeight(visualBound.getHeight()*0.85);
        skipView("个人资料");
    }

    /**
     * 改变右侧content的界面,并按钮切换
     * @param text
     */
    public void skipView(String text) {
        //设置全部隐藏
        setAllItemImageInvisible();
        AbstractFxmlView path=null;
        String label=null;
        switch (text) {
            case "个人资料":
                header1Item1Image.setVisible(true);
                label="个人信息管理  /  个人资料";
                path = personDataView;
                break;
            case "我的课程组":
                header1Item2Image.setVisible(true);
                path = myCourseGroupView;
                label="个人信息管理  /  我的课程组";
                break;
            case "题目列表":
                header2Item1Image.setVisible(true);
                label="题目管理  /  题目列表";
                path = subjectListView;
                break;
            case "批量导入":
                header2Item2Image.setVisible(true);
                path = batchImportView;
                label="题目管理  /  批量导入";
                break;
            case "总体数据查看":
                header3Item1Image.setVisible(true);
                path = overallDataView;
                label="学生管理  /  总体数据查看";
                break;
            case "详细数据查看":
                header3Item2Image.setVisible(true);
                path = detailDataView;
                label="学生管理  /  详细数据查看";
                break;
            case "试卷列表":
                header4Item1Image.setVisible(true);
                path = paperListView;
                label="试卷管理  /  试卷列表";
                break;
            case "手动生成试卷":
                header4Item2Image.setVisible(true);
                path = generatePaperView;
                label="试卷管理  /  手动生成试卷  /  设置试卷基本信息";
                break;
            case "手动生成试卷II":
                header4Item2Image.setVisible(true);
                path = generatePaperSecondView;
                label="试卷管理  /  手动生成试卷  /  选择题目";
                break;
            case "手动生成试卷III":
                header4Item2Image.setVisible(true);
                path = generatePaperThirdView;
                label="试卷管理  /  手动生成试卷  /  保存试卷";
                break;
            case "自动生成试卷":
                header4Item3Image.setVisible(true);
                path = autoPaperView;
                label="试卷管理  /  自动生成试卷";
                break;
        }
        navFont.setText(label);
        skipPage(path);
    }

    public void itemClick(MouseEvent mouseEvent) throws IOException {
        //设置点击的显示
        Text text=(Text) mouseEvent.getTarget();
        skipView(text.getText());
    }

    private void setAllItemImageInvisible() {
       header1Item1Image.setVisible(false);header1Item2Image.setVisible(false);
       header2Item1Image.setVisible(false);header2Item2Image.setVisible(false);
       header3Item1Image.setVisible(false);header3Item2Image.setVisible(false);
       header4Item1Image.setVisible(false);header4Item2Image.setVisible(false);
       header4Item3Image.setVisible(false);
    }

    public void shiftToPersonalData(MouseEvent mouseEvent) {
        skipView("个人资料");
    }

    public void skipPage(AbstractFxmlView path){
        content.getChildren().clear();
        assert path != null;
        content.getChildren().add(path.getView());
        AnchorPane.setBottomAnchor(path.getView(),0.0);
        AnchorPane.setTopAnchor(path.getView(),0.0);
        AnchorPane.setLeftAnchor(path.getView(),0.0);
        AnchorPane.setRightAnchor(path.getView(),0.0);
    }
}
