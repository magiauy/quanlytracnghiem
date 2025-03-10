package com.sgu.quanlytracnghiem.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class Main {

    @FXML
    private Button btnExam;
    @FXML
    private Button btnResult;
    @FXML
    private Button btnQuestion;
    @FXML
    private Button btnInfo;
    @FXML
    private Button btnTest;
    @FXML
    private Button btnUser;
    @FXML
    private Button btnStatistic;
    @FXML
    private Label lblLogout;
    @FXML
    private Label lblUsername;

    @FXML
    private ImageView ivLogo;
    @FXML
    private ImageView ivLogout;

    @FXML
    Pane pane;

    private final Map<Button,String> buttonMap = new java.util.HashMap<>();

    @FXML
    public void initialize() {
        setupButton();
    formatButton(btnResult, btnQuestion, btnInfo, btnTest, btnUser, btnStatistic);
    btnExam.setOnMouseEntered(e -> btnExam.setStyle("-fx-background-color: #28a4ff; -fx-border-color: black; -fx-border-width: 1 0 1 0"));
    btnExam.setOnMouseExited(e -> btnExam.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-border-width: 1 0 1 0"));
    if (!Login.getUser().isAdmin()){
        hideButton(btnQuestion, btnStatistic,btnUser,btnTest);
    }
    lblUsername.setText(Login.getUser().getUsername());
    buttonEvent(btnExam, btnResult, btnQuestion, btnInfo, btnTest, btnUser, btnStatistic);
    loadFXML("UserHome.fxml");

    ivLogo.setOnMouseClicked(e -> loadFXML("UserHome.fxml"));
//    ivLogout.setOnMouseClicked(e -> loadFXML("UserHome.fxml"));
}

   public void formatButton(Button ... buttons){
    String hover = "-fx-background-color: #28a4ff; -fx-border-color: black; -fx-border-width: 0 0 1 0;";
    String normal = "-fx-background-color: transparent; -fx-border-color: black; -fx-border-width: 0 0 1 0";

    String firstHover = "-fx-background-color: #28a4ff; -fx-border-color: black; -fx-border-width: 1 0 1 0";
    String firstNormal = "-fx-background-color: #28a4ff; -fx-border-color: black; -fx-border-width: 1 0 1 0";

        for(Button button : buttons){
            if (button != null) {
                if (button==btnExam){
                    button.setOnMouseEntered(e -> button.setStyle(firstHover));
                    button.setOnMouseExited(e -> button.setStyle(firstNormal));
                }else {
                    button.setOnMouseEntered(e -> button.setStyle(hover));
                    button.setOnMouseExited(e -> button.setStyle(normal));
                }
            } else {
                System.out.println("Button is null");
            }
        }
    }

    public void setupButton(){
        buttonMap.put(btnExam, "Exam.fxml");
        buttonMap.put(btnResult, "Result.fxml");
        buttonMap.put(btnQuestion, "Question.fxml");
        buttonMap.put(btnInfo, "Info.fxml");
        buttonMap.put(btnTest, "Test.fxml");
        buttonMap.put(btnUser, "User.fxml");
        buttonMap.put(btnStatistic, "Statistic.fxml");

    }

    public void hideButton(Button ... buttons){
        for(Button button : buttons){
            if (button != null) {
                button.setVisible(false);
            } else {
                System.out.println("Button is null");
            }
        }
    }

    public void buttonEvent(Button ... buttons){
        for(Button button : buttons){
            if (button != null) {
                button.setOnAction(e -> {
                    try {
                        pane.getChildren().clear();
                        pane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource(buttonMap.get(button)))));
                        buttonClick(button);
                    }
                    catch (IOException ex) {
                        log.error("Failed to load fxml: ", ex);
                    }
                });
            } else {
                System.out.println("Button is null");
            }
        }
    }

    public void buttonClick(Button button){
        String style;
        if (button == btnExam){
            style = "-fx-background-color: #28a4ff; -fx-border-color: black; -fx-border-width: 1 0 1 0";
        }else {
            style = "-fx-background-color: #28a4ff; -fx-border-color: black; -fx-border-width: 0 0 1 0";
        }
        for (Button b : buttonMap.keySet()){
            if (b == button){
                b.setStyle(style);
                //Lock style
                b.setOnMouseEntered(null);
                b.setOnMouseExited(null);
            }else {
                if (b==btnExam) {
                    b.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-border-width: 1 0 1 0");
                }
                else {
                    b.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-border-width: 0 0 1 0");
                }
                formatButton(b);
            }
        }


    }
    public void loadFXML(String fxmlFile) {
        try {
            // Create a new FXMLLoader
            FXMLLoader loader = new FXMLLoader();
            // Set the URL for the FXMLLoader to point to the new FXML file
            loader.setLocation(getClass().getResource(fxmlFile));
            // Load the FXML content
            Pane newContent = loader.load();
            // Clear the main pane and add the new content
            pane.getChildren().clear();
            pane.getChildren().add(newContent);
        } catch (IOException e) {
            log.error("Error: ", e);
        }catch (ClassCastException e) {
            log.error("ClassCastException: ", e);
        }
    }

}
