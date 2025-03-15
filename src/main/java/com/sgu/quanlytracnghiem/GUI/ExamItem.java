package com.sgu.quanlytracnghiem.GUI;

import com.sgu.quanlytracnghiem.BUS.CheckingExam;
import com.sgu.quanlytracnghiem.DTO.Result;
import com.sgu.quanlytracnghiem.DTO.Test;
import com.sgu.quanlytracnghiem.Interface.BUS.ResultChecking;
import com.sgu.quanlytracnghiem.Util.UI_Util;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ExamItem {

    @FXML
    private Button btnThi;
    @FXML
    private Button btnWatchResult;
    @FXML
    private Label lblLimit;
    @FXML
    private Label lblTime;
    @FXML
    private Pane paneExams;
    @FXML
    private Label lbExamName;

    private int time;
    private int limit;
    private String examName;

    private Test test;
    ResultChecking resultChecking;

    @Getter
    @Setter
    private static boolean isSubmit = false;

    public ExamItem(Test test) {
        this.examName = test.getTestTitle();
        this.time = test.getTestTime();
        this.limit = test.getTestLimit();
        this.test = new Test(test);
    }

    public void initialize() {
        resultChecking = new CheckingExam();
        btnThi.setDisable(!resultChecking.check(test, Login.getUser().getId()));
        if (resultChecking.getResult(test, Login.getUser().getId())!=null){
            btnThi.setDisable(false);
        }


        lbExamName.setText(examName);
        lblTime.setText(String.valueOf(time));
        lblLimit.setText(String.valueOf(limit));
        btnThi.setOnAction(event -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ExamForm.fxml"));
            Result result = resultChecking.getResult(test, Login.getUser().getId());
            ExamFormController controller ;
            if ((result != null)) {
                isSubmit = false;
                controller =  new ExamFormController(result,test);
            } else {
                isSubmit = true;
                controller = new ExamFormController(test);
            }
            fxmlLoader.setController(controller);
            UI_Util.openStage(fxmlLoader,"Thi",()->{
                if (ExamFormController.isSubmit()){
                    btnThi.setDisable(!resultChecking.check(test, Login.getUser().getId()));
                }
            });


        });
    }




}
