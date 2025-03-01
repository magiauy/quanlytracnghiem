package com.sgu.quanlytracnghiem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(App.class.getResource("GUI/Login.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("GUI/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Quản lý trắc nghiệm");
        stage.setScene(scene);
        stage.show();

    }
}
