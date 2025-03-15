package com.sgu.quanlytracnghiem.GUI;

import com.sgu.quanlytracnghiem.App;
import com.sgu.quanlytracnghiem.BUS.Question_BUS;
import com.sgu.quanlytracnghiem.BUS.Topic_BUS;
import com.sgu.quanlytracnghiem.BUS.User_BUS;
import com.sgu.quanlytracnghiem.DTO.Question;
import com.sgu.quanlytracnghiem.DTO.User;
import com.sgu.quanlytracnghiem.Interface.BUS.CRUD;
import com.sgu.quanlytracnghiem.Interface.BUS.IExcelImport;
import com.sgu.quanlytracnghiem.Util.UI_Util;
import com.sgu.quanlytracnghiem.Util.ValidationUtil;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;


public class UserController {
    @FXML
    private Button btnSearch;

    @FXML
    private ImageView imgAdd;

    @FXML
    private ImageView imgDelete;

    @FXML
    private ImageView imgEdit;

    @FXML
    private TableView<User> tblUser;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableColumn<User, Integer> userID;

    @FXML
    private TableColumn<User, String> userMail;

    @FXML
    private TableColumn<User, String> userName;

    @FXML
    private TableColumn<User, String> userFullname;

    @FXML
    private TableColumn<User, String> role;

    @FXML
    private Button btnImportExcel;

    CRUD <User> userBus;
    IExcelImport excelImport;

    public void initialize() {
        excelImport = new User_BUS();
        userBus = new User_BUS();
        setupTable();
        btnImportExcel.setOnAction(event -> {
            importExcel();
        });
    }

    public void setupTable(){
        userID.setCellValueFactory(new PropertyValueFactory<>("id"));
        userFullname.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        userMail.setCellValueFactory(new PropertyValueFactory<>("email"));
        userName.setCellValueFactory(new PropertyValueFactory<>("username"));
        role.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isAdmin() ? "Admin" : "User"));

        ObservableList<User> data = FXCollections.observableArrayList(userBus.getAll());
        tblUser.setItems(data);
        tblUser.getSelectionModel().selectedItemProperty().addListener((this::onSelected));
        tblUser.refresh();

    }

    private void onSelected(Observable observable) {
    }

    public void importExcel(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        fileChooser.setTitle("Chọn file Excel");
        fileChooser.setInitialDirectory(null);
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                excelImport.importExcel(file);
            } catch (Exception e) {
                ValidationUtil.showErrorAlert("Lỗi", "Lỗi import file");
            }finally {
                ValidationUtil.showInfoAlert("Thông báo", "Import thành công");
                userBus = new User_BUS();
                setupTable();

            }
        }

    }



}
