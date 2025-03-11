package com.sgu.quanlytracnghiem.GUI;

import com.sgu.quanlytracnghiem.BUS.Question_BUS;
import com.sgu.quanlytracnghiem.BUS.Topic_BUS;
import com.sgu.quanlytracnghiem.DTO.Question;
import com.sgu.quanlytracnghiem.DTO.Topic;
import com.sgu.quanlytracnghiem.Interface.BUS.CRUD;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class QuestionUI {

    @FXML
    ImageView imgAdd;
    @FXML
    ImageView imgDelete;
    @FXML
    ImageView imgEdit;

    @FXML
    TextField txtSearch;
    @FXML
    Button btnSearch;

    @FXML
    TableView<Question> tblQuestion;
    @FXML
    TableColumn<Question, String> colQuestionID;
    @FXML
    TableColumn<Question, String> colQuestionContent;

    @FXML
    TableColumn<Question, String> colQuestionLevel;
    @FXML
    TableColumn<Question, String> colQuestionTopic;
    @FXML
    TableColumn<Question, String> colQuestionStatus;

    CRUD<Question> questionBUS ;
    CRUD<Topic> topicBUS;


    @FXML
    public void initialize() {
        questionBUS = new Question_BUS();
        topicBUS = new Topic_BUS();
        setupTable();
    }

    public void setupTable(){
        colQuestionID.setCellValueFactory(new PropertyValueFactory<>("questionID"));
        colQuestionContent.setCellValueFactory(new PropertyValueFactory<>("questionContent"));
        colQuestionLevel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuestionLevel().name()));
        colQuestionTopic.setCellValueFactory(cellData -> new SimpleStringProperty(topicBUS.getByID(String.valueOf(cellData.getValue().getTopicID())).getTopicTitle()));
        colQuestionStatus.setCellValueFactory(new PropertyValueFactory<>("questionStatus"));
        ObservableList<Question> data = FXCollections.observableArrayList(questionBUS.getAll());
        tblQuestion.setItems(data);
        tblQuestion.getSelectionModel().selectedItemProperty().addListener((this::onSelected));


    }

    private void onSelected(Observable observable) {
    }
}
