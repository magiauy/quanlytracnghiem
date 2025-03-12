package com.sgu.quanlytracnghiem.GUI;

import com.sgu.quanlytracnghiem.BUS.Result_BUS;
import com.sgu.quanlytracnghiem.DTO.Result;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class ResultUI {

    @FXML
    private TableView<Result> tblResult;

    @FXML
    private TableColumn<Result, Integer> colUserID;

    @FXML
    private TableColumn<Result, String> userName;

    @FXML
    private TableColumn<Result, String> colExamID;

    @FXML
    private TableColumn<Result, String> colExamCode;

    @FXML
    private TableColumn<Result, LocalDate> colExamDate;

    @FXML
    private TableColumn<Result, BigDecimal> colResultScore;

    @FXML
    private TextField txtSearch, txtMinScore, txtMaxScore;

    @FXML
    private ComboBox<String> cbSearchBy, cbFilterTopic;

    @FXML
    private RadioButton rbHighestScore, rbLowestScore;

    @FXML
    private Button btnSearch;

    private Result_BUS resultBus;
    private ObservableList<Result> resultList;

    @FXML
    public void initialize() {
        resultBus = new Result_BUS();
        resultList = FXCollections.observableArrayList(resultBus.getAll());

        colUserID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getUserID()).asObject());
        userName.setCellValueFactory(cellData ->
                new SimpleStringProperty(Result_BUS.getUsernameById(String.valueOf(cellData.getValue().getUserID())))
        );
        colExamID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExamID()));
        colExamCode.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getResultID())));
        colExamDate.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getResultDate()));
        colResultScore.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getResultScore()));

        tblResult.setItems(resultList);

        // Thêm sự kiện double-click vào bảng
        tblResult.setRowFactory(tv -> {
            TableRow<Result> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2 && (!row.isEmpty())) {
                    Result selectedResult = row.getItem();
                    openExamUI(selectedResult);
                }
            });
            return row;
        });
    }

    @FXML
    private void handleSearch() {
        String keyword = txtSearch.getText().trim().toLowerCase();
        String searchBy = cbSearchBy.getValue();
        String filterTopic = cbFilterTopic.getValue();
        boolean isHighest = rbHighestScore.isSelected();
        boolean isLowest = rbLowestScore.isSelected();
        BigDecimal minScore = txtMinScore.getText().isEmpty() ? BigDecimal.ZERO : new BigDecimal(txtMinScore.getText());
        BigDecimal maxScore = txtMaxScore.getText().isEmpty() ? new BigDecimal(100) : new BigDecimal(txtMaxScore.getText());

        ArrayList<Result> filteredResults = new ArrayList<>();

        for (Result result : resultBus.getAll()) {
            boolean matchesSearch = keyword.isEmpty() || result.getExamID().toLowerCase().contains(keyword);
            boolean matchesScoreRange = result.getResultScore().compareTo(minScore) >= 0 && result.getResultScore().compareTo(maxScore) <= 0;

            if (matchesSearch && matchesScoreRange) {
                filteredResults.add(result);
            }
        }

        if (isHighest) {
            filteredResults.sort((r1, r2) -> r2.getResultScore().compareTo(r1.getResultScore()));
        } else if (isLowest) {
            filteredResults.sort((r1, r2) -> r1.getResultScore().compareTo(r2.getResultScore()));
        }

        resultList.setAll(filteredResults);
    }

    // Mở ExamUI và truyền dữ liệu
    private void openExamUI(Result selectedResult) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ExamResult.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Chi tiết bài thi");

            Scene scene = new Scene(loader.load());
            stage.setScene(scene);

            // Truyền dữ liệu vào ExamUI
            ResultSub controller = loader.getController();
            controller.setExamData(selectedResult);

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
