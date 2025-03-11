package com.sgu.quanlytracnghiem.GUI;

import com.sgu.quanlytracnghiem.BUS.Answers_BUS;
import com.sgu.quanlytracnghiem.BUS.Question_BUS;
import com.sgu.quanlytracnghiem.BUS.Topic_BUS;
import com.sgu.quanlytracnghiem.DTO.Answers;
import com.sgu.quanlytracnghiem.DTO.Question;
import com.sgu.quanlytracnghiem.DTO.QuestionLevel;
import com.sgu.quanlytracnghiem.DTO.Topic;
import com.sgu.quanlytracnghiem.Interface.BUS.CRUD;
import com.sgu.quanlytracnghiem.Interface.BUS.IAnswers;
import com.sgu.quanlytracnghiem.Interface.BUS.IdGenerate;
import com.sgu.quanlytracnghiem.Util.ValidationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class QuestionSubUI {

    @FXML
    public ComboBox<QuestionLevel> difficultyComboBox;
    @FXML
    public Button chooseTopicButton;
    @FXML
    public Label selectedTopicLabel;
    @FXML
    public TextArea questionContent;
    @FXML
    public ImageView questionImage;
    @FXML
    public ScrollPane answerPane;
    @FXML
    public Button addAnswerButton;
    @FXML
    public Button okButton;
    VBox vBox = new VBox();


    private CRUD<Topic> topicCRUD;
    private CRUD<Answers> answersCRUD;
    private IAnswers answersBUS;
    ToggleGroup group = new ToggleGroup();
    ArrayList<Answers> answers = new ArrayList<>();
    IdGenerate idGenerate ;
    int questionID;
    int topicID;

    int i=0;
    @FXML
    public void initialize() {
        difficultyComboBox.getItems().addAll(QuestionLevel.values());
        topicCRUD = new Topic_BUS();
        answersBUS = new Answers_BUS();
        answersCRUD = new Answers_BUS();
        idGenerate = new Question_BUS();
        addAnswerButton.setOnAction(this::AnswerItem);
        okButton.setOnAction(this::saveQuestion);
        if (QuestionUI.isEditable()){
            questionID = QuestionUI.getSelectedQuestion().getQuestionID();
            topicID = QuestionUI.getSelectedQuestion().getTopicID();
            answers = answersBUS.getAnswersByQuestionID(QuestionUI.getSelectedQuestion().getQuestionID());

            loadQuestion(QuestionUI.getSelectedQuestion());
        }else {
            questionID = idGenerate.generateId();
        }
        answerPane.setContent(vBox);

    }

    private void saveQuestion(ActionEvent actionEvent) {
        if (ValidationUtil.isEmptyComboBox(difficultyComboBox)) { return; }
        if (ValidationUtil.isAreaEmpty("Nội dung câu hỏi",questionContent)) { return; }
        if (answers.isEmpty()) {
            ValidationUtil.showErrorAlert("Vui lòng thêm câu trả lời");
            return; }
        boolean choice = false;
        for (Answers a : answers) {
            if (a.getAnswerContent().equals(questionContent.getText())) {
                ValidationUtil.showErrorAlert("Câu trả lời không được trùng với nội dung câu hỏi");
                return;
            }
            if (a.getAnswerContent().isEmpty()){
                ValidationUtil.showErrorAlert("Câu trả lời không được để trống");
                return;
            }
            if (a.isAnswerCorrect()){
                choice = true;
            }
        }
        if (!choice){
            ValidationUtil.showErrorAlert("Vui lòng chọn câu trả lời đúng");
            return;
        }

        Question question ;
        if (QuestionUI.isEditable()){
            question = QuestionUI.getSelectedQuestion();
            question.setTopicID(topicID);

        }else {
            question = new Question();
            question.setQuestionID(questionID);
            question.setQuestionStatus(true);
        }
        question.setTopicID(1);
        question.setQuestionLevel(difficultyComboBox.getValue());
        question.setQuestionContent(questionContent.getText());

        if (questionImage.getImage()!=null) {
            question.setQuestionPicture(questionImage.getImage().getUrl());
        }
        if (QuestionUI.isEditable()) {
            QuestionUI.getQuestionBUS().update(question);
            answersBUS.saveAnswersToDB(answers, question.getQuestionID());
        }else {
            QuestionUI.getQuestionBUS().add(question);
            answers.forEach(answer -> answersCRUD.add(answer));

        }
        ValidationUtil.showInfoAlert("Câu hỏi đã được cập nhật");
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    public void loadQuestion(Question question) {
        difficultyComboBox.setValue(question.getQuestionLevel());
        selectedTopicLabel.setText(topicCRUD.getByID(String.valueOf(topicID)).getTopicTitle());
        questionContent.setText(question.getQuestionContent());
        if (question.getQuestionPicture() != null) {
            Image image = new Image(question.getQuestionPicture());
            questionImage.setImage(image);
        }
        for (Answers answer : answers) {
            HBox hBox = new HBox();
            Label label = new Label((char)('A'+i++) + "");
            hBox.setAlignment(Pos.CENTER); // Căn giữa theo chiều dọc

            VBox labelBox = new VBox(label);
            labelBox.setAlignment(Pos.CENTER);
            labelBox.setPrefHeight(20);
            TextField textField = new TextField();
            //Fit the textfield to the remaining space
            textField.prefWidthProperty().bind(answerPane.widthProperty().subtract(60));
            textField.textProperty().addListener((obs, oldText, newText) -> {
                answer.setAnswerContent(newText); // Cập nhật nội dung vào đối tượng answer
            });

            textField.setText(answer.getAnswerContent());
            RadioButton radioButton = new RadioButton();
            radioButton.setToggleGroup(group);
            radioButton.setSelected(answer.isAnswerCorrect());
            radioButton.setOnAction(event ->{
                answers.forEach(a->a.setAnswerCorrect(false));
                answer.setAnswerCorrect(true);
            });
            Label remove = new Label("X");
            remove.setOnMouseClicked(e->{
                vBox.getChildren().remove(hBox);
            });

            hBox.getChildren().addAll(labelBox, textField, radioButton,remove);
            hBox.setSpacing(5);

            vBox.getChildren().add(hBox);
        }
    }


    private void AnswerItem(ActionEvent event) {
        Answers answer = new Answers();
        answer.setQuestionID(questionID); // Liên kết với câu hỏi
        answer.setAnswerID(-1); // ID tạm
        answer.setAnswerContent(""); // Nội dung mới
        answer.setAnswerCorrect(false); // Mặc định sai
        answers.add(answer); // Thêm vào danh sách chính

        HBox hBox = new HBox();
        Label label = new Label((char) ('A' + answers.size() - 1) + "");
        hBox.setAlignment(Pos.CENTER);

        VBox labelBox = new VBox(label);
        labelBox.setAlignment(Pos.CENTER);
        labelBox.setPrefHeight(20);
        TextField textField = new TextField();

        // Liên kết dữ liệu khi sửa
        textField.textProperty().addListener((obs, oldText, newText) -> {
            answer.setAnswerContent(newText);
        });

        //Fit the textfield to the remaining space
        textField.prefWidthProperty().bind(answerPane.widthProperty().subtract(60));
        RadioButton radioButton = new RadioButton();
        radioButton.setToggleGroup(group);
        radioButton.setOnAction(e -> {
            answers.forEach(a -> a.setAnswerCorrect(false));
            answer.setAnswerCorrect(true);
        });

        // Nút xoá
        Label remove = new Label("X");
        remove.setOnMouseClicked(e -> {
            vBox.getChildren().remove(hBox);
            answers.remove(answer);
        });

        hBox.getChildren().addAll(labelBox, textField, radioButton, remove);
        hBox.setSpacing(5);
        vBox.getChildren().add(hBox);
    }

}
