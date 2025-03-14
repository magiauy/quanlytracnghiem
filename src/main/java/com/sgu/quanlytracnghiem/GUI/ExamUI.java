package com.sgu.quanlytracnghiem.GUI;

import com.sgu.quanlytracnghiem.BUS.Exam_BUS;
import com.sgu.quanlytracnghiem.BUS.Test_BUS;
import com.sgu.quanlytracnghiem.DTO.Test;
import com.sgu.quanlytracnghiem.DTO.Exam;
import com.sgu.quanlytracnghiem.Interface.BUS.CRUD;
import com.sgu.quanlytracnghiem.Interface.BUS.IExam;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.ArrayList;

@Slf4j
public class ExamUI {

    @FXML
    private TextField txtSearch;  // TextField để tìm kiếm bài thi
    @FXML
    private ComboBox<String> cbTrangThai;  // ComboBox chọn trạng thái bài thi
    @FXML
    private ComboBox<Integer> cbTime;  // ComboBox chọn thời gian bài thi
    @FXML
    private VBox vbox;  // VBox chứa các ExamItem

    CRUD<Test> test_BUS = new Test_BUS();

    public void displayExamItems() {
        // Lấy tất cả các exam và test từ dữ liệu
        ArrayList<Test> examList ;
        ArrayList<Test> testList = test_BUS.getAll();  // Lấy danh sách tất cả các test
        testList.forEach(this::loadTestItem);
        vbox.setSpacing(10);
    }

    @FXML
    public void initialize() {
        displayExamItems();  // Hiển thị ExamItems khi giao diện được khởi tạo
    }

    public void loadTestItem(Test test){
        ExamItem examItem = new ExamItem(test);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ExamItem.fxml"));
        loader.setController(examItem);

        try {
            Pane pane = loader.load(); // Load FXML trước khi lấy root
            //Border , padding
            pane.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-radius: 5; -fx-padding: 10; -fx-background-radius: 5;-fx-end-margin: 10");
            vbox.getChildren().add(pane);
        } catch (Exception e) {
            log.error("Error: ", e);
        }
    }


}
