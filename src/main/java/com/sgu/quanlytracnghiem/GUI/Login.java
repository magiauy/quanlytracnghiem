package com.sgu.quanlytracnghiem.GUI;

import com.sgu.quanlytracnghiem.BUS.Auth_BUS;
import com.sgu.quanlytracnghiem.Interface.BUS.IAuth;
import com.sgu.quanlytracnghiem.Util.ValidationUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    IAuth auth_bus = new Auth_BUS();

    @FXML
    public void initialize() {
        btnLogin.setOnAction(e -> login());
    }

    public void login() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        if (ValidationUtil.isEmpty(txtUsername, txtPassword)) return;
        if(auth_bus.login(username, password)) {
            ValidationUtil.showInfoAlert("Đăng nhập thành công");
        } else {
            ValidationUtil.showErrorAlert("Đăng nhập thất bại");
        }

    }
}
