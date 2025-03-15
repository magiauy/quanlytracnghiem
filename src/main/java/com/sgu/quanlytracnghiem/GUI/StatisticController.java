package com.sgu.quanlytracnghiem.GUI;

import com.sgu.quanlytracnghiem.BUS.Exam_BUS;
import com.sgu.quanlytracnghiem.BUS.Statistic_BUS;
import com.sgu.quanlytracnghiem.BUS.Test_BUS;
import com.sgu.quanlytracnghiem.DTO.Exam;
import com.sgu.quanlytracnghiem.DTO.Result;
import com.sgu.quanlytracnghiem.DTO.Test;
import com.sgu.quanlytracnghiem.Interface.BUS.CRUD;
import com.sgu.quanlytracnghiem.Interface.BUS.IExam;
import com.sgu.quanlytracnghiem.Interface.BUS.IStatistic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.util.ArrayList;

public class StatisticController {
    @FXML
    public TableView<Test> testTableView;
    @FXML
    public ComboBox<Exam> testSelectionBox;
    @FXML
    public PieChart statisticsPieChart;
    @FXML
    TableColumn<Test, String> testCodeColumn;
    @FXML
    TableColumn<Test, String> testNameColumn;
    @FXML
    Label AboveAverage = new Label();
    @FXML
    Label BelowAverage = new Label();

    CRUD<Test> testCRUD;
    IExam examCRUD;
    CRUD<Result> resultCRUD;
    ArrayList<Exam> exams;
    IStatistic statistic;

    public void initialize() {
        testCRUD = new Test_BUS();
        examCRUD = new Exam_BUS();
        statistic = new Statistic_BUS();
        AboveAverage.setPadding(new Insets(0, 10, 0, 0));
        BelowAverage.setPadding(new Insets(0, 0, 0,10 ));


        setupTable();
        statisticsPieChart.setTitle("Statistics");
        // TODO
        testSelectionBox.setOnAction(event -> {
            Exam exam = testSelectionBox.getSelectionModel().getSelectedItem();
            ObservableList<PieChart.Data> data = statistic.pieChartDataExam(exam.getExamID());
            statisticsPieChart.setData(data);
            AboveAverage.setText(data.get(0).getName() + ": " +(int) data.get(0).getPieValue());
            BelowAverage.setText(data.get(1).getName() + ": " +(int) data.get(1).getPieValue());

        });
    }

    public void setupTable(){
        testCodeColumn.setCellValueFactory(new PropertyValueFactory<>("testCode"));
        testNameColumn.setCellValueFactory(new PropertyValueFactory<>("testTitle"));
        ObservableList<Test> data = FXCollections.observableArrayList(testCRUD.getAll());
        testTableView.setItems(data);
        testTableView.refresh();
        testTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                exams = examCRUD.getExamsByTest(newValue.getTestCode());
                testSelectionBox.getItems().clear();
                testSelectionBox.setItems(FXCollections.observableArrayList(exams));
                ObservableList<PieChart.Data> data2 = statistic.pieChartDataTest(newValue.getTestCode());

                statisticsPieChart.setData(data2);
                AboveAverage.setText(data2.get(0).getName() + ": " + data2.get(0).getPieValue());
                BelowAverage.setText(data2.get(1).getName() + ": " + data2.get(1).getPieValue());
            }
        });


    }

}
