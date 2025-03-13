package com.sgu.quanlytracnghiem.GUI;

import com.sgu.quanlytracnghiem.BUS.Answers_BUS;
import com.sgu.quanlytracnghiem.BUS.Exam_BUS;
import com.sgu.quanlytracnghiem.DTO.Answers;
import com.sgu.quanlytracnghiem.DTO.Exam;
import com.sgu.quanlytracnghiem.DTO.Question;
import com.sgu.quanlytracnghiem.Interface.BUS.IAnswers;
import com.sgu.quanlytracnghiem.Interface.BUS.IExam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class AboutExamController {
    @FXML
    public ComboBox<String> cbTestCode;
    @FXML
    public TableView<Question> tableQuestion;
    @FXML
    public TableColumn<Question,String> colQuestionID;
    @FXML
    public TableColumn<Question,String> colQuestionText;
    @FXML
    public Pane paneDetail;
    ArrayList<Exam> exams = new ArrayList<>();

    IExam examBus;
    public static IAnswers answerBus;


    @FXML
    public void initialize() {
        setUpTable();
        examBus = new Exam_BUS();
        answerBus = new Answers_BUS();
        exams = examBus.getExamsByTest(AboutTestController.getGlobalTest().getTestCode());

        for (Exam exam : exams) {
            cbTestCode.getItems().add(exam.getExamOrder());
        }
        cbTestCode.setOnAction(event ->{
            tableQuestion.getItems().clear();
            System.out.println(cbTestCode.getSelectionModel().getSelectedIndex());
            System.out.println(exams.get(cbTestCode.getSelectionModel().getSelectedIndex()).getQuestions());
            ObservableList<Question> questions = FXCollections.observableArrayList(exams.get(cbTestCode.getSelectionModel().getSelectedIndex()).getQuestions());
            tableQuestion.setItems(questions);
            tableQuestion.refresh();
        });
        cbTestCode.getSelectionModel().select(0);
        ObservableList<Question> questions = FXCollections.observableArrayList(exams.getFirst().getQuestions());
        tableQuestion.setItems(questions);
        tableQuestion.refresh();


    }

    public void setUpTable(){
        colQuestionID.setCellValueFactory(new PropertyValueFactory<>("questionID"));
        colQuestionText.setCellValueFactory(new PropertyValueFactory<>("questionContent"));
        tableQuestion.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ExamQuestionItem.fxml"));
                ExamItemController controller = new ExamItemController(newSelection);
                loader.setController(controller);
                try {
                    paneDetail.getChildren().clear();
                    paneDetail.getChildren().add(loader.load());
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        });


    }



}
