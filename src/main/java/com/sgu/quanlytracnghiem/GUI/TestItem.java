package com.sgu.quanlytracnghiem.GUI;

import com.sgu.quanlytracnghiem.DTO.Topic;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import lombok.NonNull;

import java.util.ArrayList;


public class TestItem {

    @FXML
    public Label lblTitle;
    @FXML
    private Label lblTime;
    @FXML
    private Label lblStatus;
    @FXML
    private Label lblLimit;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblEasy;
    @FXML
    private Label lblMedium;
    @FXML
    private Label lblDiff;


    String testTitle;
    int testTime;
    int testLimit;
    java.time.LocalDate testDate;
    boolean testStatus;
    int num_easy;
    int num_medium;
    int num_diff;
    ArrayList<Topic> topics;

    @FXML
    public void initialize() {
        lblTitle.setText(testTitle);
        lblTime.setText(String.valueOf(testTime));
        lblLimit.setText(String.valueOf(testLimit));
        lblDate.setText(testDate.toString());
        lblStatus.setText(testStatus ? "Đã mở" : "Chưa mở");
        lblStatus.setStyle(testStatus ? "-fx-text-fill: green" : "-fx-text-fill: red");

        for (Topic topic : topics) {
            num_easy += topic.getNum_easy();
            num_medium += topic.getNum_medium();
            num_diff += topic.getNum_diff();
        }


        lblEasy.setText(String.valueOf(num_easy));
        lblMedium.setText(String.valueOf(num_medium));
        lblDiff.setText(String.valueOf(num_diff));
    }

    public TestItem(com.sgu.quanlytracnghiem.DTO.Test test){
        this.testTitle = test.getTestTitle();
        this.testTime = test.getTestTime();
        this.testLimit = test.getTestLimit();
        this.testDate = test.getTestDate();
        this.testStatus = test.getTestStatus();
        this.topics = test.getTopics();
    }
}
