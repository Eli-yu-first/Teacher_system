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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.ResourceBundle;


@FXMLController
public class LoginController implements Initializable {

    public TextField loginAccount;
    public PasswordField loginPassword;
    public TextField loginCode;
    public AnchorPane loginWindow;
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

}
