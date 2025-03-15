package com.sgu.quanlytracnghiem.GUI;

import com.sgu.quanlytracnghiem.BUS.Answers_BUS;
import com.sgu.quanlytracnghiem.DTO.Answers;
import com.sgu.quanlytracnghiem.DTO.Logs;
import com.sgu.quanlytracnghiem.DTO.Question;
import com.sgu.quanlytracnghiem.Interface.BUS.IAnswers;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QAForm {
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

    @FXML
    public VBox questionVbox;
    int questionId;
    IAnswers answers_BUS;
int j;
Answers answer;

    private List<HBox> answerBoxes = new ArrayList<>();

    ArrayList<Answers> answers;
    public QAForm(Question question,int i) {
        questionId = question.getQuestionID();
        questionContent = question.getQuestionContent();
        questionPicture = question.getQuestionPicture();
        this.j = i;
    }
    public QAForm(Question question,int i,Answers choice) {
        questionId = question.getQuestionID();
        questionContent = question.getQuestionContent();
        questionPicture = question.getQuestionPicture();
        answer = choice;
        this.j = i;
    }

    public void initialize() {
        answers_BUS = new Answers_BUS();
        questionLabel.setText("Câu "+j+": "+questionContent);
        if (questionPicture != null) {
//            remove questionImage
            questionVbox.getChildren().remove(questionImage);
        }
        answers = answers_BUS.getAnswersByQuestionID(questionId);
        for (int i = 0; i < answers.size(); i++) {
            Answers answer = answers.get(i);
            HBox answerBox = createAnswerBox(answer,i);
            // Khi đủ 2 câu trả lời trên một hàng, thêm vào VBox và tạo hàng mới
            answerBoxes.add(answerBox); // Lưu để reset sau này

            answerContainer.getChildren().add(answerBox);
        }

    }
    private HBox createAnswerBox(Answers answer,int i) {

        HBox answerBox = new HBox(5);
        answerBox.setStyle(" -fx-padding: 5;");
        answerBox.setAlignment(Pos.CENTER_LEFT); // Căn trái để dễ đọc

        // Label để hiển thị nội dung đáp án với wrap text
        Label answerLabel = new Label(answer.getAnswerContent());
        answerLabel.setWrapText(true);
        answerLabel.setMaxWidth(200); // Giới hạn chiều rộng, chữ sẽ tự động xuống dòng

        // RadioButton để chọn đáp án
        RadioButton radioButton = new RadioButton();
        radioButton.setToggleGroup(toggleGroup);
        // VBox chứa RadioButton + Text (để wrap text hoạt động đúng)
        if (this.answer!=null) {
            if (answer.getAnswerID() == this.answer.getAnswerID()){
                answerBox.setStyle("-fx-background-color: rgba(144, 238, 144, 0.3); -fx-padding: 5;");
                radioButton.setSelected(true);
            }
        }
        radioButton.setOnAction(event -> {
            click(answerBox, radioButton,i);
        });

        answerBox.setOnMouseClicked(event -> {
            click(answerBox, radioButton,i);
        });

        Label answerIndex = new Label((char) ('A' + i) + ". ");
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


    public void click(HBox answerBox, RadioButton radioButton,int i) {
        ExamFormController.getResult().updateResult(j-1,answers.get(i));
        Logs log = new Logs();
        log.setLogContent("Chọn câu trả lời cho câu hỏi " + j+ " là " + (char) ('A' + i));
        log.setLogUserID(Login.getUser().getId());
        log.setLogTime(LocalDateTime.now());
        log.setLogExamID(ExamFormController.getExam().getExamID());
            ExamFormController.getLogs_CRUD().add(log);
        for (HBox box : answerBoxes) {
            box.setStyle("-fx-background-color: transparent; -fx-padding: 5;");
        }

        answerBox.setStyle("-fx-background-color: rgba(144, 238, 144, 0.3); -fx-padding: 5;");
        radioButton.setSelected(true);
    }
}


