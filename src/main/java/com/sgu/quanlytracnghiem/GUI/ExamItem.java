package com.sgu.quanlytracnghiem.GUI;

import com.sgu.quanlytracnghiem.DTO.Test;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ExamItem {

    @FXML
    private Button btnThi;
    @FXML
    private Button btnWatchResult;
    @FXML
    private TextField txtTime;
    @FXML
    private TextField txtLimit;
    @FXML
    private TextField txtTrangThai;
    @FXML
    private Pane paneExams;
    @FXML
    private Label lbExamName;

    private int time;
    private int limit;
    private int trangThai;
    private String examName;

    public ExamItem(Test test) {
        this.examName = test.getTestTitle();
        this.time = test.getTestTime();
        this.limit = test.getTestLimit();
        this.trangThai = test.getTestStatus();
        loadFXML();  // Load FXML khi khởi tạo
    }

    private void loadFXML() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sgu/quanlytracnghiem/GUI/ExamItem.fxml"));
        loader.setController(this);  // Gán controller cho ExamItem này
        try {
            this.paneExams = loader.load();  // Load layout vào Pane
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        lbExamName.setText(examName);
        txtTime.setText(String.valueOf(time));
        txtLimit.setText(String.valueOf(limit));
        txtTrangThai.setText(trangThai == 1 ? "Đã mở" : "Chưa mở");
    }

    public Pane getPaneExams() {
        return paneExams;  // Trả về Pane của ExamItem
    }
}
