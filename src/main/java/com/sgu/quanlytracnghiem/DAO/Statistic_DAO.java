package com.sgu.quanlytracnghiem.DAO;

import com.sgu.quanlytracnghiem.Interface.DAO.IStatistic_DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Statistic_DAO implements IStatistic_DAO {

    Connection connection = Connect.getInstance().getConnection();
    @Override
    public ObservableList<PieChart.Data> pieChartDataTest(String testCode) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList() ;
        String sql = """
            SELECT 
                SUM(CASE WHEN r.rsMark >= 5 THEN 1 ELSE 0 END) AS above_5,
                SUM(CASE WHEN r.rsMark < 5 THEN 1 ELSE 0 END) AS below_5
            FROM result r
            JOIN exam e ON r.exID = e.exCode
            WHERE e.testCode = ?
            """;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, testCode);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            pieChartData.add(new PieChart.Data("Above 5", resultSet.getInt("above_5")));
            pieChartData.add(new PieChart.Data("Below 5", resultSet.getInt("below_5")));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pieChartData;
    }

    @Override
    public ObservableList<PieChart.Data> pieChartDataExam(String exCode) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        String sql = """
            
                SELECT 
                SUM(CASE WHEN r.rsMark >= 5 THEN 1 ELSE 0 END) AS above_5,
                SUM(CASE WHEN r.rsMark < 5 THEN 1 ELSE 0 END) AS below_5
            FROM result r
            WHERE r.exID = ?
            """;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, exCode);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            pieChartData.add(new PieChart.Data("Above 5", resultSet.getInt("above_5")));
            pieChartData.add(new PieChart.Data("Below 5", resultSet.getInt("below_5")));
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pieChartData;
        }
}
