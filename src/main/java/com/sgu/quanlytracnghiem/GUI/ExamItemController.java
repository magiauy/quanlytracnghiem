package com.sgu.quanlytracnghiem.GUI;

import com.sgu.quanlytracnghiem.DTO.Answers;
import com.sgu.quanlytracnghiem.DTO.Question;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ExamItemController {
    @FXML
    public Label questionLabel;
    @FXML
    public ImageView questionImage;
    @FXML
    public ScrollPane answerPane;
    @FXML
    public VBox answerContainer;
    ToggleGroup toggleGroup = new ToggleGroup();
    String questionContent;
    String questionPicture;
    int questionId;
    ArrayList<Answers> answers;
    public ExamItemController(Question question) {
        questionId = question.getQuestionID();
        questionContent = question.getQuestionContent();
        questionPicture = question.getQuestionPicture();
    }

    public void initialize() {
        questionLabel.setText(questionContent);
        if (questionPicture != null) {
            questionImage.setVisible(true);
        }
        answers = AboutExamController.answerBus.getAnswersByQuestionID(questionId);

        for (int i = 0; i < answers.size(); i++) {
            Answers answer = answers.get(i);
            HBox answerBox = createAnswerBox(answer,i);
            // Khi đủ 2 câu trả lời trên một hàng, thêm vào VBox và tạo hàng mới
                answerContainer.getChildren().add(answerBox);
            }

    }
    private HBox createAnswerBox(Answers answer,int i) {
        HBox answerBox = new HBox(5);
        answerBox.setStyle("-fx-border-color: #ccc; -fx-padding: 5; -fx-background-radius: 5;");
        answerBox.setAlignment(Pos.CENTER_LEFT); // Căn trái để dễ đọc

        // Label để hiển thị nội dung đáp án với wrap text
        Label answerLabel = new Label(answer.getAnswerContent());
        answerLabel.setWrapText(true);
        answerLabel.setMaxWidth(200); // Giới hạn chiều rộng, chữ sẽ tự động xuống dòng

        // RadioButton để chọn đáp án
        RadioButton radioButton = new RadioButton();
        radioButton.setToggleGroup(toggleGroup);
        radioButton.setSelected(answer.isAnswerCorrect());
        // VBox chứa RadioButton + Text (để wrap text hoạt động đúng)

        Label answerIndex = new Label(STR."\{(char) ('A' + i)}. ");
        answerIndex.setStyle("-fx-font-weight: bold;");


        answerBox.getChildren().addAll(answerIndex, radioButton, answerLabel);

        // Nếu có hình ảnh, thêm vào
        if (answer.getAwPicture() != null && !answer.getAwPicture().isEmpty()) {
            ImageView answerImage = new ImageView(new Image(answer.getAwPicture()));
            answerImage.setFitHeight(50);
            answerImage.setFitWidth(50);
            answerImage.setPreserveRatio(true);
            answerBox.getChildren().add(answerImage);
        }

        return answerBox;
    }
    }


