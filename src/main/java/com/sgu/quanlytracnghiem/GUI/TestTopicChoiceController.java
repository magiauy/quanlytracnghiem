package com.sgu.quanlytracnghiem.GUI;

import com.sgu.quanlytracnghiem.BUS.Topic_BUS;
import com.sgu.quanlytracnghiem.DTO.Topic;
import com.sgu.quanlytracnghiem.Interface.BUS.CRUD;
import com.sgu.quanlytracnghiem.Interface.DAO.ITopic;
import com.sgu.quanlytracnghiem.Util.ValidationUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Optional;

public class TestTopicChoiceController {

    @FXML
    public TextField searchField;
    @FXML
    public TableView<Topic> topicTable;
    @FXML
    public TableColumn<Topic,String> colTpID;
    @FXML
    public TableColumn<Topic,String> colTpTitle;
    @FXML
    public Spinner<Integer> easySpinner;
    @FXML
    public Spinner<Integer> mediumSpinner;
    @FXML
    public Spinner<Integer> hardSpinner;
    @FXML
    public Button btnOK;
    CRUD<Topic> topicBUS;
    @Getter
    @Setter
    public static Topic selectedTopic;

    @Getter
    @Setter
    private static boolean isSaved;
    ITopic itopic;

    ArrayList<Topic> topics;
    @FXML
    public void initialize() {
        topicBUS = new Topic_BUS();
        itopic = new Topic_BUS();
        topics = itopic.getTopicQuestionCounts();

        colTpID.setCellValueFactory(new PropertyValueFactory<>("topicID"));
        colTpTitle.setCellValueFactory(new PropertyValueFactory<>("topicTitle"));

        //Loại bor Topic đã có trong global
        ObservableList<Topic> data = FXCollections.observableArrayList(topicBUS.getAll());

        // Remove topics that are already in the global test
        data.removeAll(AboutTestController.getGlobalTest().getTopics());

        for (Topic topic : AboutTestController.getGlobalTest().getTopics()) {
            data.remove(topicBUS.getByID(String.valueOf(topic.getTopicID())));
        }
        btnOK.setOnAction(event->{
            if (topicTable.getSelectionModel().getSelectedItem() != null) {
                isSaved = true;
                selectedTopic = topicTable.getSelectionModel().getSelectedItem();
                selectedTopic.setNum_easy(easySpinner.getValue());
                selectedTopic.setNum_medium(mediumSpinner.getValue());
                selectedTopic.setNum_diff(hardSpinner.getValue());
                ((Stage)btnOK.getScene().getWindow()).close();
            }else {
                ValidationUtil.showErrorAlert("Please choose a topic");
            }
        });
        topicTable.setDisable(AboutTestController.isTopicEditable());

        //add listener to the table
        topicTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Optional<Topic> selectedTopic = topics.stream()
                        .filter(topic -> topic.getTopicID() == newSelection.getTopicID())
                        .findFirst();

                int numEasy = selectedTopic.map(Topic::getNum_easy).orElse(0); // Nếu không tìm thấy, mặc định là 0
                int numMedium = selectedTopic.map(Topic::getNum_medium).orElse(0);
                int numHard = selectedTopic.map(Topic::getNum_diff).orElse(0);
                if (AboutTestController.isTopicEditable()){
                    //Select the topic that was selected before
                    data.add(topicBUS.getByID(String.valueOf(AboutTestController.getTopicSelected().getTopicID())));
                    topicTable.getSelectionModel().select(topicBUS.getByID(String.valueOf(AboutTestController.getTopicSelected().getTopicID())));


                    easySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, numEasy, AboutTestController.getTopicSelected().getNum_easy()));
                    mediumSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, numMedium, AboutTestController.getTopicSelected().getNum_medium()));
                    hardSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, numHard, AboutTestController.getTopicSelected().getNum_diff()));
                }else {
                    easySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, numEasy, 0));
                    mediumSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, numMedium, 0));
                    hardSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, numHard, 0));
                }
            }
        });





        if (AboutTestController.isTopicEditable()){
            //Select the topic that was selected before
            data.add(topicBUS.getByID(String.valueOf(AboutTestController.getTopicSelected().getTopicID())));
            topicTable.getSelectionModel().select(topicBUS.getByID(String.valueOf(AboutTestController.getTopicSelected().getTopicID())));
            easySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, AboutTestController.getTopicSelected().getNum_easy()));
            mediumSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, AboutTestController.getTopicSelected().getNum_medium()));
            hardSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, AboutTestController.getTopicSelected().getNum_diff()));
        }else {
            easySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
            mediumSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
            hardSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
        }
        topicTable.setItems(data);


    }

}
