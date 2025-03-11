package com.sgu.quanlytracnghiem.GUI;

import com.sgu.quanlytracnghiem.DTO.Topic;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
@Slf4j
public class AboutTestController {
    @FXML
    private Label lblTestID;
    @FXML
    private Label lblStatus;
    @FXML
    private TextField txtTestCode;
    @FXML
    private TextField txtTime;
    @FXML
    private TextField txtLimit;
    @FXML
    private DatePicker dpDate;
    @FXML
    private TableView<Topic> tableTopic;
    @FXML
    private TableColumn<Topic, String> colTopicId;
    @FXML
    private TableColumn<Topic, String> colTopicName;
    @FXML
    private TableColumn<Topic, Integer> colNumEasy;
    @FXML
    private TableColumn<Topic, Integer> colNumMedium;
    @FXML
    private TableColumn<Topic, Integer> colNumDiff;
    @FXML
    private TableColumn<Topic, Integer> colTotalNum;
    @FXML
    private ImageView imgAdd;
    @FXML
    private ImageView imgEdit;
    @FXML
    private ImageView imgDelete;
    @FXML
    private ImageView imgBack;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnGenerate;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    public void initialize() {
        imgBack.setOnMouseClicked(event -> {
            openStage("Test.fxml");
        });
    }

    public void openStage(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Test_UI.class.getResource(fxmlFile));
            //Thay pane hiện tại bằng pane mới
            Pane root = loader.load();
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(root);
        } catch (IOException e) {
            log.error("Error", e);

        }
    }
}
