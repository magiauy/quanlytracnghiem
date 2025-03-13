package com.sgu.quanlytracnghiem.GUI;

import com.sgu.quanlytracnghiem.DTO.Test;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ExamItem {

    @FXML
    private Button btnThi;
    @FXML
    private Button btnWatchResult;
    @FXML
    private Label lblLimit;
    @FXML
    private Label lblTime;
    @FXML
    private Pane paneExams;
    @FXML
    private Label lbExamName;

    private int time;
    private int limit;
    private String examName;

    public ExamItem(Test test) {
        this.examName = test.getTestTitle();
        this.time = test.getTestTime();
        this.limit = test.getTestLimit();
    }

    public void initialize() {
        lbExamName.setText(examName);
        lblTime.setText(String.valueOf(time));
        lblLimit.setText(String.valueOf(limit));
    }

}
