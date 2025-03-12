package com.sgu.quanlytracnghiem.GUI;

import com.sgu.quanlytracnghiem.DTO.Topic;
import com.sgu.quanlytracnghiem.Interface.BUS.CRUD;
import com.sgu.quanlytracnghiem.BUS.Topic_BUS;
import com.sgu.quanlytracnghiem.Util.ValidationUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

public class ChoiceTopicController {
    @FXML
    public TextField searchField;
    @FXML
    public TableView<Topic> topicTable;
    @FXML
    public TableColumn<Topic,String> colTpID;
    @FXML
    public TableColumn<Topic,String> colTpTitle;
    @FXML
    public Button btnOK;
    CRUD<Topic> topicBUS;
    @Getter
    @Setter
    public static Topic selectedTopic;
    @FXML
    public void initialize() {
        topicBUS = new Topic_BUS();

        colTpID.setCellValueFactory(new PropertyValueFactory<>("topicID"));
        colTpTitle.setCellValueFactory(new PropertyValueFactory<>("topicTitle"));
        ObservableList<Topic> data = FXCollections.observableArrayList(topicBUS.getAll());
        topicTable.setItems(data);

        btnOK.setOnAction(event->{
            if (topicTable.getSelectionModel().getSelectedItem() != null) {
                selectedTopic = topicTable.getSelectionModel().getSelectedItem();
                ((Stage)btnOK.getScene().getWindow()).close();
            }else {
                ValidationUtil.showErrorAlert("Please choose a topic");
            }
        });

    }
}
