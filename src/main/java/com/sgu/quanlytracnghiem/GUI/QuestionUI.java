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
import lombok.Getter;
import lombok.Setter;

import static com.sgu.quanlytracnghiem.Util.UI_Util.openStage;

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

    @Getter
    public static CRUD<Question> questionBUS ;

    CRUD<Topic> topicBUS;

    @Getter
    @Setter
    private static Question selectedQuestion;
    @Getter
    @Setter
    private static boolean editable = false;

    @FXML
    public void initialize() {
        questionBUS = new Question_BUS();
        topicBUS = new Topic_BUS();
        setupTable();
        imgAdd.setOnMouseClicked(e->{
            editable = false;
            openStage("Quản lý câu hỏi","QuestionSubUI.fxml", ()->{
                System.out.println("RunCallBack");
                ObservableList<Question> data = FXCollections.observableArrayList(questionBUS.getAll());
                tblQuestion.setItems(data);
                tblQuestion.refresh();
            });
        });

        imgEdit.setOnMouseClicked(e->{
            editable = true;
            if (tblQuestion.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Please select a question to edit");
                alert.showAndWait();
                return;
            }
            selectedQuestion = tblQuestion.getSelectionModel().getSelectedItem();
            openStage("Quản lý câu hỏi","QuestionSubUI.fxml", ()-> {
                System.out.println("RunCallBack");
                editable = false;
                selectedQuestion = null;
                ObservableList<Question> data = FXCollections.observableArrayList(questionBUS.getAll());
                tblQuestion.setItems(data);
                tblQuestion.refresh();
            });
        });


    }

    public void setupTable(){
        colQuestionID.setCellValueFactory(new PropertyValueFactory<>("questionID"));
        colQuestionContent.setCellValueFactory(new PropertyValueFactory<>("questionContent"));
        colQuestionLevel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuestionLevel().name()));
        colQuestionTopic.setCellValueFactory(cellData -> new SimpleStringProperty(topicBUS.getByID(String.valueOf(cellData.getValue().getTopicID())).getTopicTitle()));
        colQuestionStatus.setCellValueFactory(new PropertyValueFactory<>("questionStatus"));
        ObservableList<Question> data = FXCollections.observableArrayList(questionBUS.getAll());
        tblQuestion.setItems(data);
        tblQuestion.refresh();


    }

}
