package com.sgu.quanlytracnghiem.GUI;

import com.sgu.quanlytracnghiem.BUS.Test_BUS;
import com.sgu.quanlytracnghiem.BUS.Topic_BUS;
import com.sgu.quanlytracnghiem.DTO.Test;
import com.sgu.quanlytracnghiem.DTO.Topic;
import com.sgu.quanlytracnghiem.Interface.BUS.CRUD;
import com.sgu.quanlytracnghiem.Interface.BUS.IdGenerate;
import com.sgu.quanlytracnghiem.Util.ValidationUtil;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.sgu.quanlytracnghiem.Util.UI_Util.openStage;

@Slf4j
public class AboutTestController {
    @FXML
    public TextField txtTitle;
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

    Map<Integer,String> status;
    CRUD<Topic> topic_Crud;

    ArrayList<Topic> topic_list;

    @Getter
    @Setter
    private static boolean isTopicEditable;

    @Getter
    @Setter
    private static Topic topicSelected;

    @Getter
    @Setter
    private static Test globalTest;

    IdGenerate idGenerate;
    @FXML
    public void initialize() {
        topic_Crud = new Topic_BUS();
        status = new HashMap<>();
        topic_list = topic_Crud.getAll();
        idGenerate = new Test_BUS();
        status.put(0, "Đã đóng");
        status.put(1, "Đang mở");
        status.put(2,"Nháp");
        status.put(3,"Đã tạo");
        createTable();
        imgBack.setOnMouseClicked(event -> {
            openStageLocal("Test.fxml");
        });

        if (Test_UI.isEditable()) {
            globalTest = new Test(Test_UI.getTestSelected());
            Test_UI.setTestSelected(null);
            Test_UI.setEditable(false);
            setUpEdit();
        }
        else {
            globalTest = new Test();
            globalTest.setTopics(new ArrayList<>());
            lblTestID.setText(String.valueOf(idGenerate.generateId()));
        }

        imgAdd.setOnMouseClicked(event -> {
            isTopicEditable = false;
            openStage("Chọn topic","TestTopicChoice.fxml",()->{
                if (TestTopicChoiceController.isSaved()) {
                    globalTest.getTopics().add(TestTopicChoiceController.getSelectedTopic());
                    ObservableList<Topic> data = FXCollections.observableArrayList(globalTest.getTopics());
                    tableTopic.setItems(data);
                    tableTopic.refresh();
                    TestTopicChoiceController.setSaved(false);
                }
            });
        });
        imgEdit.setOnMouseClicked(event -> {
            imgEdit();

        });
        imgDelete.setOnMouseClicked(event -> {
           if (ValidationUtil.showConfirmAlert("Bạn có chắc muốn xoá không")) {
                globalTest.getTopics().remove(tableTopic.getSelectionModel().getSelectedItem());
                ObservableList<Topic> data = FXCollections.observableArrayList(globalTest.getTopics());
                tableTopic.setItems(data);
                tableTopic.refresh();
           }
        });

        btnSave.setOnMouseClicked(event -> {
            globalTest.setTestLimit(Integer.parseInt(txtLimit.getText()));
            globalTest.setTestTime(Integer.parseInt(txtTime.getText()));
            globalTest.setTestCode(txtTestCode.getText());
            globalTest.setTestDate(dpDate.getValue());
            globalTest.setTestTitle(txtTitle.getText());
            globalTest.setTestStatus(2);
            if (Test_UI.isEditable()) {

                Test_UI.getTestCRUD().update(globalTest);
            }else {
                Test_UI.getTestCRUD().add(globalTest);
            }
        });
        btnGenerate.setOnMouseClicked(event -> {
            if (ValidationUtil.showConfirmAlert("Bạn có chắc muốn tạo đề không")) {
                globalTest.setTestStatus(3);
                Test_UI.getTestCRUD().update(globalTest);
            }
        });
    }

    private void imgEdit() {
        isTopicEditable = true;
        if (tableTopic.getSelectionModel().getSelectedItem() == null) {
            ValidationUtil.showErrorAlert("Please choose a topic");
            return;
        }
        topicSelected = tableTopic.getSelectionModel().getSelectedItem();
        openStage("Chọn topic","TestTopicChoice.fxml",()->{
            if (TestTopicChoiceController.isSaved()) {
                globalTest.getTopics().forEach(Topic -> {
                    if (Topic.getTopicID() == topicSelected.getTopicID()) {
                        System.out.println(Topic.getTopicID());
                        Topic.setNum_easy(TestTopicChoiceController.getSelectedTopic().getNum_easy());
                        Topic.setNum_medium(TestTopicChoiceController.getSelectedTopic().getNum_medium());
                        Topic.setNum_diff(TestTopicChoiceController.getSelectedTopic().getNum_diff());
                    }
                });
                ObservableList<Topic> data = FXCollections.observableArrayList(globalTest.getTopics());
                tableTopic.setItems(data);
                tableTopic.refresh();
                TestTopicChoiceController.setSaved(false);
            }
        });
    }

    public void setUpEdit(){
        txtTitle.setText(globalTest.getTestTitle());
        lblTestID.setText(String.valueOf(globalTest.getTestID()));
        lblStatus.setText(status.get(globalTest.getTestID()));
        txtTestCode.setText(globalTest.getTestCode());
        txtTime.setText(String.valueOf(globalTest.getTestTime()));
        txtLimit.setText(String.valueOf(globalTest.getTestLimit()));
        dpDate.setValue(globalTest.getTestDate());
        ObservableList<Topic> data = FXCollections.observableArrayList(globalTest.getTopics());
        tableTopic.setItems(data);
    }

    public void createTable(){
        colTopicId.setCellValueFactory(new PropertyValueFactory<>("topicID"));
        colTopicName.setCellValueFactory(cellData -> new SimpleStringProperty(topic_Crud.getByID(String.valueOf(cellData.getValue().getTopicID())).getTopicTitle()));
        colNumEasy.setCellValueFactory(new PropertyValueFactory<>("num_easy"));
        colNumMedium.setCellValueFactory(new PropertyValueFactory<>("num_medium"));
        colNumDiff.setCellValueFactory(new PropertyValueFactory<>("num_diff"));
        colTotalNum.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().totalNumProperty()).asObject());
    }


    public void openStageLocal(String fxmlFile) {
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
