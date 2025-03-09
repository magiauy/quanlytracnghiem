package com.sgu.quanlytracnghiem.GUI;

import com.sgu.quanlytracnghiem.DTO.Test;
import com.sgu.quanlytracnghiem.DTO.Topic;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class Test_UI {
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
        //Tăng khoảng cách giữa các Vbox
        vbox.setSpacing(10);
    }

    //Is selected


    public void test() {
        ArrayList<Topic> topics = new ArrayList<>();
        topics.add(new Topic(1, "T001", 0, true));
        topics.add(new Topic(2, "T002", 0, true));
        topics.add(new Topic(3, "T003", 0, true));
        topics.getFirst().setNum_easy(10);
        topics.getFirst().setNum_medium(10);
        topics.getFirst().setNum_diff(10);

        topics.get(1).setNum_easy(10);
        topics.get(1).setNum_medium(10);
        topics.get(1).setNum_diff(10);

        topics.getLast().setNum_easy(10);
        topics.getLast().setNum_medium(10);
        topics.getLast().setNum_diff(10);



        new Test();
        Test test = Test.builder()
                .testID(1)
                .testCode("T001")
                .testTitle("Test 1")
                .testDate(java.time.LocalDate.now())
                .testLimit(10)
                .testTime(10)
                .testStatus(true)
                .topics(topics)

                .build();
        loadTestItem(test);

        Test test2 = Test.builder()
                .testID(2)
                .testCode("T002")
                .testTitle("Test 2")
                .testDate(java.time.LocalDate.now())
                .testLimit(10)
                .testTime(10)
                .testStatus(false)
                .topics(topics)
                .build();

        loadTestItem(test2);


    }

    public void loadTestItem(Test test){
        TestItem testItem = new TestItem(test);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TestItem.fxml"));
        loader.setController(testItem);

        try {
            Pane pane = loader.load(); // Load FXML trước khi lấy root

            //Border , padding
            pane.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-radius: 5; -fx-padding: 10; -fx-background-radius: 5;-fx-end-margin: 10");


            pane.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, e -> {
                paneSelected(pane);
            });
            vbox.getChildren().add(pane);
        } catch (Exception e) {
            log.error("Error: ", e);
        }
    }

    public void paneSelected(Pane pane){
        for (int i = 0; i < vbox.getChildren().size(); i++) {
            Pane p = (Pane) vbox.getChildren().get(i);
            if (p == pane){
                p.setStyle("-fx-background-color: #28a4ff;-fx-border-color: black; -fx-border-width: 1; -fx-border-radius: 5; -fx-padding: 10; -fx-background-radius: 5");

            }else {
                p.setStyle("-fx-background-color: transparent;-fx-border-color: black; -fx-border-width: 1; -fx-border-radius: 5; -fx-padding: 10; -fx-background-radius: 5");
            }
        }
    }

}
