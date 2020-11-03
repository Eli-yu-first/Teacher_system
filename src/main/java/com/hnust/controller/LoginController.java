package com.hnust.controller;

import com.hnust.MainApp;
import com.hnust.domain.Teacher;
import com.hnust.service.TeacherService;
import com.hnust.store.DataStore;
import com.hnust.view.LoginView;
import com.hnust.view.MainView;
import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.GUIState;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import java.net.URL;
import java.util.ResourceBundle;


@FXMLController
public class LoginController implements Initializable {

    public TextField loginAccount;
    public PasswordField loginPassword;
    public TextField loginCode;
    public AnchorPane loginWindow;
    public TextField registerName;
    public TextField registerTeaNumber;
    public TextField registerTeaEmail;
    public PasswordField registerPassword;
    public PasswordField registerRepeatPassword;
    public PasswordField registerPhone;
    public PasswordField registerCode;
    public GridPane loginContainer;
    public GridPane registerContainer;
    public Label teaTitle;
    private Stage stage;
    @Autowired
    private LoginView loginView;
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private DataStore dataStore;

    public void initialize(URL location, ResourceBundle resources) {
        stage=GUIState.getStage();
        stage.setMinWidth(950);
        stage.setMinHeight(700);

    }

    public void loginButtonClick(ActionEvent event) {
        //dataStore.setToken("asdfasdf");
//        teacherService.teacherLogin(new Callback<Teacher>() {
//            @Override
//            public void onResponse(Call call, Response response) {
//                System.out.println(((Teacher)response.body()).getToken());
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable throwable) {
//                System.out.println("请求失败");
//            }
//        },loginAccount.getText(),loginPassword.getText());

        //跳转时先关闭窗口，切换效果会自然一点
        stage.close();
        MainApp.showView(MainView.class);
    }

    public void registerButtonClick(ActionEvent actionEvent) {

    }


    public void shiftRegister(MouseEvent mouseEvent) {
        teaTitle.setText("教 师 注 册");
        loginContainer.setManaged(false);
        loginContainer.setVisible(false);
        registerContainer.setManaged(true);
        registerContainer.setVisible(true);
    }

    public void shiftLogin(MouseEvent mouseEvent) {
        teaTitle.setText("教 师 登 录");
        loginContainer.setManaged(true);
        loginContainer.setVisible(true);
        registerContainer.setManaged(false);
        registerContainer.setVisible(false);
    }
}
