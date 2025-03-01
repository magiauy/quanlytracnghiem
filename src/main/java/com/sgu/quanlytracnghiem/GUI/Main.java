package com.sgu.quanlytracnghiem.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
    private Label lblLogout;
    @FXML
    private Button btnTest;
    @FXML
    private Button btnUser;
    @FXML
    private Button btnStatistic;

    @FXML
    public void initialize() {
    formatButton(btnResult, btnQuestion, btnInfo, btnTest, btnUser, btnStatistic);
    btnExam.setOnMouseEntered(e -> btnExam.setStyle("-fx-background-color: #28a4ff; -fx-border-color: black; -fx-border-width: 1 0 1 0"));
    btnExam.setOnMouseExited(e -> btnExam.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-border-width: 1 0 1 0"));



}

   public void formatButton(Button ... buttons){
    String hover = "-fx-background-color: #28a4ff; -fx-border-color: black; -fx-border-width: 0 0 1 0;";
    String normal = "-fx-background-color: transparent; -fx-border-color: black; -fx-border-width: 0 0 1 0";
    for(Button button : buttons){
        if (button != null) {
            button.setOnMouseEntered(e -> button.setStyle(hover));
            button.setOnMouseExited(e -> button.setStyle(normal));
        } else {
            System.out.println("Button is null");
        }
    }
}

}
