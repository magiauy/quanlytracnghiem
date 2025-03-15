package com.sgu.quanlytracnghiem.GUI;

import com.sgu.quanlytracnghiem.BUS.Result_BUS;
import com.sgu.quanlytracnghiem.DTO.Answers;
import com.sgu.quanlytracnghiem.DTO.Result;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultSub {

    @FXML
    private Label lbExamName2, lbDiem;

    @FXML
    private VBox vboxQuestions;

    @FXML
    private TextField txtUserID, txtUserName, txtExamID, txtExamCode, txtExamDate;

    private Result_BUS resultBus = new Result_BUS();

    public void setExamData(Result result) {
        // Thiết lập thông tin người dùng
        txtUserID.setText(String.valueOf(result.getUserID()));
        txtUserName.setText(Result_BUS.getUsernameById(String.valueOf(result.getUserID())));
        txtExamID.setText(result.getExamID());
        txtExamCode.setText(String.valueOf(result.getResultID()));
        txtExamDate.setText(result.getResultDate().toString());
        lbDiem.setText("Điểm: " + result.getResultScore());

        // Đặt các TextField thành chỉ đọc
        txtUserID.setEditable(false);
        txtUserName.setEditable(false);
        txtExamID.setEditable(false);
        txtExamCode.setEditable(false);
        txtExamDate.setEditable(false);

        lbExamName2.setText("Bài thi: " + result.getExamID());

        // Xóa nội dung cũ trong VBox trước khi hiển thị mới
        vboxQuestions.getChildren().clear();

        // Nhóm câu trả lời theo câu hỏi
        Map<Integer, List<Answers>> groupedAnswers = new HashMap<>();
        for (Answers answer : result.getAnswers()) {
            groupedAnswers.computeIfAbsent(answer.getQuestionID(), k -> new ArrayList<>()).add(answer);
        }

        // Hiển thị từng câu hỏi và danh sách câu trả lời đầy đủ của nó
        for (Integer questionID : groupedAnswers.keySet()) {
            // Lấy nội dung câu hỏi
            String questionContent = Result_BUS.getQuestionContentById(String.valueOf(questionID));

            // Lấy danh sách tất cả câu trả lời (không chỉ những câu mà thí sinh đã chọn)
            List<Answers> allAnswers = Result_BUS.getAnswersByQuestionId(String.valueOf(questionID));

            // Kiểm tra xem thí sinh đã trả lời đúng hay không
            boolean isQuestionCorrect = groupedAnswers.get(questionID).stream()
                    .anyMatch(a -> a.isAnswerCorrect() && a.isAnswerStatus());

            // Label hiển thị câu hỏi
            Label lblQuestion = new Label("Câu hỏi " + questionID + ": " + questionContent);
            lblQuestion.setFont(Font.font(18));
            lblQuestion.setStyle("-fx-padding: 10px; -fx-background-radius: 10px; " +
                    (isQuestionCorrect ? "-fx-background-color: #d4edda;" : "-fx-background-color: #f8d7da;"));

            VBox vboxAnswers = new VBox(5); // Chứa danh sách câu trả lời

            // Hiển thị tất cả các câu trả lời của câu hỏi
            for (Answers answer : allAnswers) {
                HBox hboxAnswer = new HBox(10);
                Label lblAnswer = new Label(answer.getAnswerContent());
                lblAnswer.setFont(Font.font(16));

                Label lblStatus = new Label();
                lblStatus.setFont(Font.font(16));

                // Nếu câu trả lời là đúng, hiển thị dấu tích xanh
                if (answer.isAnswerCorrect()) {
                    lblStatus.setText("✔");
                    lblStatus.setTextFill(Color.GREEN);
                }
                // Nếu thí sinh chọn sai câu trả lời, hiển thị dấu X đỏ
                else if (groupedAnswers.get(questionID).stream().anyMatch(a -> a.getAnswerID() == answer.getAnswerID() && a.isAnswerStatus())) {
                    lblStatus.setText("✖");
                    lblStatus.setTextFill(Color.RED);
                }

                hboxAnswer.getChildren().addAll(lblAnswer, lblStatus);
                vboxAnswers.getChildren().add(hboxAnswer);
            }

            // Tạo hộp chứa câu hỏi và danh sách câu trả lời
            VBox questionBox = new VBox(10, lblQuestion, vboxAnswers);
            questionBox.setStyle("-fx-padding: 10px; -fx-border-color: black; -fx-border-radius: 10px;");

            vboxQuestions.getChildren().add(questionBox);
        }
    }
}
