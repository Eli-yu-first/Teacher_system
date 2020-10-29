package com.hnust;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitScan;
import com.hnust.config.SplashScreenCustom;
import com.hnust.view.LoginView;
import com.hnust.view.MainView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RetrofitScan("com.hnust.api")
public class MainApp extends AbstractJavaFxApplicationSupport {

    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(MainApp.class, MainView.class,new SplashScreenCustom(),args);
    }

}
