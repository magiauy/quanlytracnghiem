package com.sgu.quanlytracnghiem.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {
    @FXML
    private VBox vbox;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private Image imaAdd;

    @FXML
    public void initialize() {
        test();

    }

    public void test() {
        com.sgu.quanlytracnghiem.DTO.Test test = new com.sgu.quanlytracnghiem.DTO.Test(
                1, "Test", 60, 10, 10, 10, 100, java.time.LocalDate.now(), true, new java.util.ArrayList<>()
        );

        TestItem testItem = new TestItem(test);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TestItem.fxml"));
        loader.setController(testItem);

        try {
            Pane pane = loader.load(); // Load FXML trước khi lấy root
            vbox.getChildren().add(pane);
        } catch (Exception e) {
            log.error("Error: ", e);
        }
    }

}
