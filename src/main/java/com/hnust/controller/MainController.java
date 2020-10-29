package com.hnust.controller;

import com.hnust.store.DataStore;
import com.hnust.view.paper.GeneratePaperView;
import com.hnust.view.paper.PaperListView;
import com.hnust.view.personInfo.MyCourseGroupView;
import com.hnust.view.personInfo.PersonDataView;
import com.hnust.view.student.DetailDataView;
import com.hnust.view.student.OverallDataView;
import com.hnust.view.subject.BatchImportView;
import com.hnust.view.subject.SubjectListView;
import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.GUIState;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
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
    @Autowired
    private DataStore dataStore;

    private Stage stage;

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

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //System.out.println(dataStore.getToken());
        Screen s=Screen.getPrimary();
        Rectangle2D visualBound=s.getVisualBounds();
        stage=GUIState.getStage();
        stage.setMinWidth(visualBound.getWidth()*0.85);
        stage.setMinHeight(visualBound.getHeight()*0.8);
        try {
            skipView(personDataView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 改变右侧content的界面
     * @param view
     * @throws IOException
     */
    private void skipView(AbstractFxmlView view) throws IOException {
        content.getChildren().clear();
        content.getChildren().add(view.getView());
        AnchorPane.setBottomAnchor(view.getView(),0.0);
        AnchorPane.setTopAnchor(view.getView(),0.0);
        AnchorPane.setLeftAnchor(view.getView(),0.0);
        AnchorPane.setRightAnchor(view.getView(),0.0);
    }

    public void itemClick(MouseEvent mouseEvent) throws IOException {
        //设置全部隐藏
        setAllItemImageInvisible();
        //设置点击的显示
        Text text=(Text) mouseEvent.getTarget();
        AbstractFxmlView path=null;
        String label=null;
        switch (text.getText()) {
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
                label="试卷管理  /  手动生成试卷";
                break;
        }
        navFont.setText(label);
        skipView(path);
    }

    private void setAllItemImageInvisible() {
       header1Item1Image.setVisible(false);header1Item2Image.setVisible(false);
       header2Item1Image.setVisible(false);header2Item2Image.setVisible(false);
       header3Item1Image.setVisible(false);header3Item2Image.setVisible(false);
       header4Item1Image.setVisible(false);header4Item2Image.setVisible(false);
    }
}
