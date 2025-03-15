package com.sgu.quanlytracnghiem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("GUI/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Trắc nghiệm SGU");
        stage.setScene(scene);
        stage.show();

    }
}
