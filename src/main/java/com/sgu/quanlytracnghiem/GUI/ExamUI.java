package com.sgu.quanlytracnghiem.GUI;
import com.sgu.quanlytracnghiem.DTO.Exam;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
public class ExamUI {
    @FXML
    private Label lbDiem;
    @FXML
    private Button btnNop;
    @FXML
    private AnchorPane achorPaneExam;
    @FXML
    private AnchorPane  achorPaneExamQuestion;
    @FXML
    private Pane paneExams;
    @FXML
    private AnchorPane anchorPaneExams;
    @FXML
    private Button btnWatchResult;
    @FXML
    private Label lExamName1;
    @FXML
    private Label lbExamName2;
    @FXML
    private Button btnThi;
    @FXML
    private TextField txtTime;
    @FXML
    private TextField txtLimit;
    @FXML
    private TextField txtTrangThai;
    @FXML
    private ComboBox<String> cbTrangThai;
    @FXML
    private ComboBox<Integer> cbTime;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private Label lbTime;


}
