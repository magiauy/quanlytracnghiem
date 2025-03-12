package com.sgu.quanlytracnghiem.DAO;

import com.sgu.quanlytracnghiem.DTO.Test;
import com.sgu.quanlytracnghiem.DTO.Topic;
import com.sgu.quanlytracnghiem.Interface.BUS.IdGenerate;
import com.sgu.quanlytracnghiem.Interface.DAO.GenericDAO;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
@Slf4j

public class Test_DAO implements GenericDAO<Test> , IdGenerate {
    Connection connection = Connect.getInstance().getConnection();

    @Override
    public boolean insert(Test test) {
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO test (testCode, testTitle, testTime, testLimit,testDate,testStatus) VALUES (?, ?,?,?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, test.getTestCode());
                preparedStatement.setString(2, test.getTestTitle());
                preparedStatement.setInt(3, test.getTestTime());
                preparedStatement.setInt(4, test.getTestLimit());
                preparedStatement.setDate(5, Date.valueOf(test.getTestDate()));
                preparedStatement.setInt(6, test.getTestStatus());
                preparedStatement.executeUpdate();
                try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                    if (rs.next()) {
                        test.setTestID(rs.getInt(1));
                    }
                }
            }
            connection.commit();
            test_topic(test);
            return true;
        } catch (Exception e) {
            log.error("Failed to insert test: ", e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error("Failed to rollback: ", ex);
            }
            return false;
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                log.error("Failed to set auto commit: ", e);
            }
        }

    }

    private void test_topic(Test test) throws SQLException {
        String sql;
        for (Topic topic : test.getTopics()) {
            sql = "INSERT INTO test_topic (testID, tpID,num_easy,num_medium,num_diff) VALUES (?, ?,?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, test.getTestID());
                preparedStatement.setInt(2, topic.getTopicID());
                preparedStatement.setInt(3, topic.getNum_easy());
                preparedStatement.setInt(4, topic.getNum_medium());
                preparedStatement.setInt(5, topic.getNum_diff());
                preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public boolean update(Test test) {
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE test SET testCode = ?, testTitle = ?, testTime = ?, testLimit = ?, testDate = ?, testStatus = ? WHERE testID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, test.getTestCode());
                preparedStatement.setString(2, test.getTestTitle());
                preparedStatement.setInt(3, test.getTestTime());
                preparedStatement.setInt(4, test.getTestLimit());
                preparedStatement.setDate(5, Date.valueOf(test.getTestDate()));
                preparedStatement.setInt(6, test.getTestStatus());
                preparedStatement.setInt(7, test.getTestID());
                preparedStatement.executeUpdate();
            }
            sql = "DELETE FROM test_topic WHERE testID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, test.getTestID());
                preparedStatement.executeUpdate();
            }
            test_topic(test);
            connection.commit();
            return true;
        } catch (Exception e) {
            log.error("Failed to update test: ", e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error("Failed to rollback: ", ex);
            }
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        try {
            connection.setAutoCommit(false);
            String sql = "DELETE FROM test WHERE testID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, id);
                preparedStatement.executeUpdate();
            }
            connection.commit();
            return true;
        } catch (Exception e) {
            log.error("Failed to delete test: ", e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error("Failed to rollback: ", ex);
            }
            return false;
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                log.error("Failed to set auto commit: ", e);
            }
        }
    }

    @Override
    public Test getById(String id) {
        Test test = new Test();
        try {
            String sql = "SELECT * FROM test WHERE testID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, id);
                preparedStatement.executeQuery();
                while (preparedStatement.getResultSet().next()) {
                    getTest(preparedStatement, test);
                    test.setTopics(getTopic(test.getTestID()));
                }
            }
        } catch (Exception e) {
            log.error("Failed to get test by id: ", e);
        }
        return test;
    }

    @Override
    public ArrayList<Test> getAll() {
        ArrayList<Test> tests = new ArrayList<>();
        try{
            String sql = "SELECT * FROM test";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.executeQuery();
                while (preparedStatement.getResultSet().next()) {
                    Test test = new Test();
                    getTest(preparedStatement, test);
                    test.setTopics(getTopic(test.getTestID()));
                    tests.add(test);
                }
            }
        } catch (Exception e) {
            log.error("Failed to get all tests: ", e);
        }

        return tests;
    }

    private void getTest(PreparedStatement preparedStatement, Test test) throws SQLException {
        test.setTestID(preparedStatement.getResultSet().getInt("testID"));
        test.setTestCode(preparedStatement.getResultSet().getString("testCode"));
        test.setTestTitle(preparedStatement.getResultSet().getString("testTitle"));
        test.setTestTime(preparedStatement.getResultSet().getInt("testTime"));
        test.setTestLimit(preparedStatement.getResultSet().getInt("testLimit"));
        test.setTestDate(preparedStatement.getResultSet().getDate("testDate").toLocalDate());
        test.setTestStatus(preparedStatement.getResultSet().getInt("testStatus"));
    }

    private ArrayList<Topic> getTopic(int testId){
        try {
            ArrayList<Topic> topics = new ArrayList<>();
            String sql = "SELECT * FROM test_topic WHERE testID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, testId);
                preparedStatement.executeQuery();
                while (preparedStatement.getResultSet().next()) {
                    Topic topic = new Topic();
                    topic.setTopicID(preparedStatement.getResultSet().getInt("tpID"));
                    topic.setNum_easy(preparedStatement.getResultSet().getInt("num_easy"));
                    topic.setNum_medium(preparedStatement.getResultSet().getInt("num_medium"));
                    topic.setNum_diff(preparedStatement.getResultSet().getInt("num_diff"));
                    topics.add(topic);
                }
            }
            return topics;
        } catch (Exception e) {
            log.error("Failed to get topics: ", e);
            return null;
        }
    }

    public int generateId() {
        try {
            String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = 'test'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs.next() ? rs.getInt(1) : -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
