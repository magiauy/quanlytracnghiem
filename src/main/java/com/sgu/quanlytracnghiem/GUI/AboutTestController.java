package com.sgu.quanlytracnghiem.GUI;

import com.sgu.quanlytracnghiem.BUS.Exam_BUS;
import com.sgu.quanlytracnghiem.BUS.Test_BUS;
import com.sgu.quanlytracnghiem.BUS.Topic_BUS;
import com.sgu.quanlytracnghiem.DTO.Test;
import com.sgu.quanlytracnghiem.DTO.Topic;
import com.sgu.quanlytracnghiem.Interface.BUS.CRUD;
import com.sgu.quanlytracnghiem.Interface.BUS.IExam;
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
    private Button btnViewExam;
    @FXML
    private Button btnDeploy;

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
    IExam examBUS;
    @FXML
    public void initialize() {
        topic_Crud = new Topic_BUS();
        status = new HashMap<>();
        topic_list = topic_Crud.getAll();
        examBUS = new Exam_BUS();
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
            globalTest.setTestStatus(2);
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
        loadStatus();
        btnSave.setPrefWidth(180);
        btnSave.setText(globalTest.getTestStatus()==2?"Lưu nháp:":"Lưu");
        btnSave.setOnMouseClicked(event -> {
            globalTest.setTestLimit(Integer.parseInt(txtLimit.getText()));
            globalTest.setTestTime(Integer.parseInt(txtTime.getText()));
            globalTest.setTestCode(txtTestCode.getText());
            globalTest.setTestDate(dpDate.getValue());
            globalTest.setTestTitle(txtTitle.getText());
            if (Test_UI.isEditable()) {
                if (Test_UI.getTestCRUD().update(globalTest)) {
                    ValidationUtil.showInfoAlert("Cập nhật thành công");
                } else {
                    ValidationUtil.showErrorAlert("Cập nhật thất bại");
                }
            }else {
                globalTest.setTestStatus(2);
                if (Test_UI.getTestCRUD().add(globalTest)){
                    ValidationUtil.showInfoAlert("Thêm thành công");
                }else {
                    ValidationUtil.showErrorAlert("Thêm thất bại");
                }
            }
        });
        btnGenerate.setOnMouseClicked(event -> {
            if (ValidationUtil.showConfirmAlert("Bạn có chắc muốn tạo đề không")) {
                globalTest.setTestStatus(3);
                Test_UI.getTestCRUD().update(globalTest);
                examBUS.generateExam(globalTest);
                ValidationUtil.showInfoAlert("Tạo đề thành công");
                btnSave.setDisable(false);
                txtTestCode.setDisable(true );
            }
        });

        btnViewExam.setOnAction(event ->{

            openStage("Xem đề thi","AboutExam.fxml",()->{

            });
        });

        btnDeploy.setPrefWidth(190);
        btnDeploy.setOnAction(event -> {
            if (btnDeploy.getText().equals("Mở đề")) {
                globalTest.setTestStatus(1);
            }else {
                globalTest.setTestStatus(0);

            }
            Test_UI.getTestCRUD().update(globalTest);
            loadStatus();
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

    public void loadStatus(){
        switch (globalTest.getTestStatus()) {
            case 0:
                lblStatus.setText("Đã đóng");
                lblStatus.setStyle("-fx-text-fill: red");
                txtTestCode.setDisable(true);
                txtTime.setDisable(true);
                txtLimit.setDisable(true);
                dpDate.setDisable(true);
                btnDeploy.setText("Mở đề");
                break;
            case 1:
                lblStatus.setText("Đang mở");
                lblStatus.setStyle("-fx-text-fill: green");
                txtTestCode.setDisable(true);
                txtTime.setDisable(true);
                dpDate.setDisable(true);
                btnDeploy.setText("Khoá đề");
                btnGenerate.setDisable(true);
                //Hide
                imgAdd.setDisable(true);
                imgEdit.setDisable(true);
                imgDelete.setDisable(true);


                break;
            case 2:
                lblStatus.setText("Nháp");
                lblStatus.setStyle("-fx-text-fill: #FFC107");
                btnViewExam.setDisable(true);
                btnDeploy.setDisable(true);
                break;
            case 3:
                lblStatus.setText("Đã tạo");
                lblStatus.setStyle("-fx-text-fill: green");
                txtTestCode.setDisable(true);
                btnGenerate.setDisable(true);

                break;

        }
    }

}
