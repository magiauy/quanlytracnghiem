package com.sgu.quanlytracnghiem.BUS;

import com.sgu.quanlytracnghiem.DAO.Statistic_DAO;
import com.sgu.quanlytracnghiem.Interface.BUS.IStatistic;
import com.sgu.quanlytracnghiem.Interface.DAO.IStatistic_DAO;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public class Statistic_BUS implements IStatistic {

    IStatistic_DAO statistic_dao;

    public Statistic_BUS() {
        statistic_dao = new Statistic_DAO();
    }

    @Override
    public ObservableList<PieChart.Data> pieChartDataTest(String testCode) {
        System.out.println(statistic_dao.pieChartDataTest(testCode));
        return statistic_dao.pieChartDataTest(testCode);
    }

    @Override
    public ObservableList<PieChart.Data> pieChartDataExam(String exCode) {
        return statistic_dao.pieChartDataExam(exCode);
    }

}
