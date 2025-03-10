package com.sgu.quanlytracnghiem.GUI;

import com.sgu.quanlytracnghiem.App;
import com.sgu.quanlytracnghiem.BUS.User_BUS;
import com.sgu.quanlytracnghiem.DTO.User;
import com.sgu.quanlytracnghiem.Interface.BUS.CRUD;
import com.sgu.quanlytracnghiem.Util.UI_Util;
import com.sgu.quanlytracnghiem.Util.ValidationUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class InfoController {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtUID;
    @FXML
    private TextField txtUsername;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnChangePass;

    private CRUD<User> userBUS;
    @FXML
    public void initialize() {
        userBUS = new User_BUS();
        txtName.setText(Login.getUser().getFullName());
        txtEmail.setText(Login.getUser().getEmail());
        txtUID.setText(String.valueOf(Login.getUser().getId()));
        txtUsername.setText(Login.getUser().getUsername());
        btnSave.setOnAction(event -> save());
        btnChangePass.setOnAction(event -> changePass());
        checkChange();
        txtName.requestFocus();
        txtName.textProperty().addListener((observable, oldValue, newValue) -> checkChange());
    }

    private void save() {
        // TODO Auto-generated method stub
        if (ValidationUtil.isEmpty(txtName)) return;
        if (ValidationUtil.isInValidChar("Họ tên", txtName)) return;
        if (ValidationUtil.isFirstCharNotSpace("Họ tên",txtName))return;

        User newUser = new User();
        newUser.setId(Login.getUser().getId());
        newUser.setFullName(txtName.getText());
        newUser.setEmail(txtEmail.getText());
        newUser.setUsername(txtUsername.getText());
        newUser.setAdmin(Login.getUser().isAdmin());
        if (userBUS.update(newUser)){
            ValidationUtil.showInfoAlert("Cập nhật thông tin thành công");
            Login.getUser().setFullName(txtName.getText());
        }else {
            ValidationUtil.showErrorAlert("Cập nhật thông tin thất bại");
        }
    }

    private void changePass() {
        // TODO Auto-generated method stub
        UI_Util.openStage("Đổi mật khẩu","ChangePass.fxml", () -> {
            if (ChangePassController.isSave()){
                ChangePassController.setSave(false);
                ValidationUtil.showInfoAlert("Vui lòng đăng nhập lại để cập nhật thông tin");
                Stage stage = (Stage) btnSave.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("GUI/Login.fxml"));
                Scene scene ;
                try {
                    scene = new Scene(fxmlLoader.load());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setTitle("Trắc nghiệm SGU");
                stage.setScene(scene);
                stage.show();
            }
        });
    }

    //Disable Save button if have no change
    public void checkChange() {
        btnSave.setDisable(txtName.getText().equals(Login.getUser().getFullName()));
    }
}
