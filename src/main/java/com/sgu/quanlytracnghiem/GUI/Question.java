package com.sgu.quanlytracnghiem.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class Question {
    @FXML
    private ComboBox<String> level;

    @FXML
    public void initialize() {
        level.getItems().addAll("Dễ", "Trung bình", "Khó");
        level.setValue("Dễ");


    }
}
