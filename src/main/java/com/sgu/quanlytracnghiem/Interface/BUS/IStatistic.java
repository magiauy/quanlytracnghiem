package com.sgu.quanlytracnghiem.Interface.BUS;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public interface IStatistic {
    ObservableList<PieChart.Data> pieChartDataTest(String testCode);
    ObservableList<PieChart.Data> pieChartDataExam(String exCode);
}
