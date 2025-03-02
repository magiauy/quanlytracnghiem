package com.sgu.quanlytracnghiem.GUI;

import com.sgu.quanlytracnghiem.BUS.Auth_BUS;
import com.sgu.quanlytracnghiem.DTO.User;
import com.sgu.quanlytracnghiem.Interface.BUS.IAuth;
import com.sgu.quanlytracnghiem.Util.UI_Util;
import com.sgu.quanlytracnghiem.Util.ValidationUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
@Slf4j

public class Login {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    IAuth auth_bus = new Auth_BUS();

    @Getter
    private static  User user;

    @FXML
    public void initialize() {
        btnLogin.setOnAction(e -> login());
    }

    public void login() {
        String email = txtUsername.getText();
        String password = txtPassword.getText();
        if (ValidationUtil.isEmpty(txtUsername, txtPassword)) return;
        if(auth_bus.login(email, password)) {
            ValidationUtil.showInfoAlert("Đăng nhập thành công");
            user = auth_bus.getUser(email);
            openStage("Main.fxml");
        } else {
            ValidationUtil.showErrorAlert("Đăng nhập thất bại");
        }

    }

    public void openStage(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Login.class.getResource(fxmlFile));
            Parent root = loader.load();
            AnchorPane anchorPane = (AnchorPane) root;
            Stage stage = (Stage) btnLogin.getScene().getWindow();stage.close();
            stage.setTitle("Ứng dụng thi trắc nghiệm!"); // Set an appropriate title
            stage.setScene(new Scene(anchorPane));

            stage.show(); // Display the stage

        } catch (IOException e) {
            log.error("Error",e);
        }
    }
}
