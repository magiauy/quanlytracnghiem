package com.sgu.quanlytracnghiem.GUI;

import com.sgu.quanlytracnghiem.BUS.Result_BUS;
import com.sgu.quanlytracnghiem.DTO.Result;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ResultSub {

    @FXML
    private Label lbExamName2, lbDiem;

    @FXML
    private TextField txtUserID, txtUserName, txtExamID, txtExamCode, txtExamDate;

    private Result_BUS resultBus = new Result_BUS();

    public void setExamData(Result result) {
        txtUserID.setText(String.valueOf(result.getUserID()));
        txtUserName.setText(Result_BUS.getUsernameById(String.valueOf(result.getUserID())));
        txtExamID.setText(result.getExamID());
        txtExamCode.setText(String.valueOf(result.getResultID()));
        txtExamDate.setText(result.getResultDate().toString());
        lbDiem.setText("Điểm: " + result.getResultScore().toString());
        txtUserID.setEditable(false);
        txtUserName.setEditable(false);
        txtExamID.setEditable(false);
        txtExamCode.setEditable(false);
        txtExamDate.setEditable(false);
    }
}
