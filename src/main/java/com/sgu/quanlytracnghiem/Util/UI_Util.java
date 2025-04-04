package com.sgu.quanlytracnghiem.Util;

import com.sgu.quanlytracnghiem.GUI.Main;
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
    public static void openStage(String title,String fxmlFile,Runnable onCloseCallback) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(fxmlFile));
            Parent root = loader.load();
            AnchorPane anchorPane = (AnchorPane) root;
            Stage stage = new Stage();
            stage.setTitle(title); // Set an appropriate title
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setOnHidden(event -> onCloseCallback.run());// Set the onCloseCallback
            stage.setScene(new Scene(anchorPane));

            stage.showAndWait(); // Display the stage

        } catch (IOException e) {
            log.error("Error",e);
        }
    }

    public static void openStage(FXMLLoader loader,String title,Runnable onCloseCallback) {
        try {
            Parent root = loader.load();
            AnchorPane anchorPane = (AnchorPane) root;
            Stage stage = new Stage();
            stage.setTitle(title); // Set an appropriate title
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setOnHidden(event -> onCloseCallback.run());// Set the onCloseCallback
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
