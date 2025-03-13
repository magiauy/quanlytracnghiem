package com.sgu.quanlytracnghiem.GUI;

import com.sgu.quanlytracnghiem.BUS.Exam_BUS;
import com.sgu.quanlytracnghiem.BUS.Test_BUS;
import com.sgu.quanlytracnghiem.DTO.Test;
import com.sgu.quanlytracnghiem.DTO.Exam;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ExamUI {

    @FXML
    private TextField txtSearch;  // TextField để tìm kiếm bài thi
    @FXML
    private ComboBox<String> cbTrangThai;  // ComboBox chọn trạng thái bài thi
    @FXML
    private ComboBox<Integer> cbTime;  // ComboBox chọn thời gian bài thi
    @FXML
    private AnchorPane anchorPaneExams;  // AnchorPane chứa danh sách các ExamItem
    @FXML
    private VBox vbox;  // VBox chứa các ExamItem

    Exam_BUS examBUS = new Exam_BUS();
    Test_BUS test_BUS = new Test_BUS();

    public void displayExamItems() {
        // Lấy tất cả các exam và test từ dữ liệu
        ArrayList<Exam> examList = examBUS.getAll();
        ArrayList<Test> testList = test_BUS.getAll();  // Lấy danh sách tất cả các test

        VBox vbox = new VBox();  // Tạo một VBox để chứa tất cả các ExamItem

        // Duyệt qua danh sách exams
        for (Exam exam : examList) {
            // Kiểm tra TestID giữa exam và test
            for (Test test : testList) {
                if (exam.getTestID().equals(test.getTestID())) {
                    // Tạo ExamItem cho test trùng
                    ExamItem examItem = new ExamItem(test);
                    examItem.initialize();
                    vbox.getChildren().add(examItem.getPaneExams());  // Thêm vào VBox
                }
            }
        }

        anchorPaneExams.getChildren().clear();  // Xóa các thành phần cũ trong AnchorPane
        anchorPaneExams.getChildren().add(vbox);  // Thêm VBox chứa các ExamItem vào AnchorPane
    }

    @FXML
    public void initialize() {
        displayExamItems();  // Hiển thị ExamItems khi giao diện được khởi tạo
    }
}
