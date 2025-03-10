package com.sgu.quanlytracnghiem.GUI;

import com.sgu.quanlytracnghiem.BUS.Auth_BUS;
import com.sgu.quanlytracnghiem.DTO.User;
import com.sgu.quanlytracnghiem.Interface.BUS.IAuth;
import com.sgu.quanlytracnghiem.Util.ValidationUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

public class ChangePassController {
    @FXML
    private PasswordField txtOldPass;
    @FXML
    private PasswordField txtNewPass;
    @FXML
    private PasswordField txtConfirmPass;
    @FXML
    private TextField txtNewPassVisible;
    @FXML
    private TextField txtConfirmPassVisible;
    @FXML
    private CheckBox chkShowPass;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;

    @Getter
    @Setter
    private static boolean isSave = false;
    IAuth auth;

    @FXML
    public void initialize() {
        setup();
        btnSave.setOnAction(event -> btnSave());
        btnCancel.setOnAction(event -> btnCancel());
        auth = new Auth_BUS();
    }

    private void btnSave() {
        if (ValidationUtil.isEmpty(txtOldPass,txtConfirmPass,txtNewPass)) return;
        if (!ValidationUtil.isValidPassword(txtNewPass)) return;

        if (!txtNewPass.getText().equals(txtConfirmPass.getText())) {
            ValidationUtil.showErrorAlert("Mật khẩu mới không khớp");
            return;
        }


        if (auth.changePassword(Login.getUser().getEmail(), txtOldPass.getText(), txtNewPass.getText())) {
            ValidationUtil.showInfoAlert("Đổi mật khẩu thành công");
            isSave = true;
            Stage stage = (Stage) btnSave.getScene().getWindow();
            stage.close();

        } else {
            ValidationUtil.showErrorAlert("Đổi mật khẩu thất bại");
        }

    }

    private void btnCancel() {
        isSave = false;
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();

    }


    public void setup() {
        txtNewPassVisible.managedProperty().bind(chkShowPass.selectedProperty());
        txtNewPassVisible.visibleProperty().bind(chkShowPass.selectedProperty());
        txtConfirmPassVisible.managedProperty().bind(chkShowPass.selectedProperty());
        txtConfirmPassVisible.visibleProperty().bind(chkShowPass.selectedProperty());
        txtNewPass.managedProperty().bind(chkShowPass.selectedProperty().not());
        txtNewPass.visibleProperty().bind(chkShowPass.selectedProperty().not());
        txtConfirmPass.managedProperty().bind(chkShowPass.selectedProperty().not());
        txtConfirmPass.visibleProperty().bind(chkShowPass.selectedProperty().not());
        txtNewPassVisible.textProperty().bindBidirectional(txtNewPass.textProperty());
        txtConfirmPassVisible.textProperty().bindBidirectional(txtConfirmPass.textProperty());
        txtOldPass.requestFocus();
        chkShowPass.setOnAction(event->{
            txtNewPassVisible.managedProperty().unbind();
            txtNewPassVisible.visibleProperty().unbind();
            txtNewPass.managedProperty().unbind();
            txtNewPass.visibleProperty().unbind();
            txtConfirmPassVisible.managedProperty().unbind();
            txtConfirmPassVisible.visibleProperty().unbind();
            txtConfirmPass.managedProperty().unbind();
            txtConfirmPass.visibleProperty().unbind();
            if (chkShowPass.isSelected()) {
                txtNewPass.setVisible(false);
                txtNewPass.setManaged(false);
                txtNewPassVisible.setVisible(true);
                txtNewPassVisible.setManaged(true);
                txtConfirmPass.setVisible(false);
                txtConfirmPass.setManaged(false);
                txtConfirmPassVisible.setVisible(true);
                txtConfirmPassVisible.setManaged(true);
            } else {
                txtNewPass.setVisible(true);
                txtNewPass.setManaged(true);
                txtNewPassVisible.setVisible(false);
                txtNewPassVisible.setManaged(false);
                txtConfirmPass.setVisible(true);
                txtConfirmPass.setManaged(true);
                txtConfirmPassVisible.setVisible(false);
                txtConfirmPassVisible.setManaged(false);
            }
        });
    }
}
