package com.sgu.quanlytracnghiem.Util;

import com.sgu.quanlytracnghiem.GUI.Home;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class UI_Util {
    public static void openStage(String fxmlFile,Runnable onCloseCallback) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Home.class.getResource(fxmlFile));
            Parent root = loader.load();
            AnchorPane anchorPane = (AnchorPane) root;
            Stage stage = new Stage();
            stage.setTitle("Your Dialog Title"); // Set an appropriate title
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setOnHidden(event -> onCloseCallback.run());// Set the onCloseCallback
            stage.getIcons().add(new javafx.scene.image.Image("img/logo.png"));
            stage.setScene(new Scene(anchorPane));

            stage.showAndWait(); // Display the stage

        } catch (IOException e) {
            log.error("Error",e);
        }
    }
    public static void configureButton(Button button, EventHandler<ActionEvent> eventHandler) {
    button.setOnAction(eventHandler);
}
}
