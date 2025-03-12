package com.sgu.quanlytracnghiem.DAO;

import com.sgu.quanlytracnghiem.DTO.Topic;
import com.sgu.quanlytracnghiem.Interface.DAO.GenericDAO;
import com.sgu.quanlytracnghiem.Interface.DAO.ITopic;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
@Slf4j
public class Topic_DAO implements GenericDAO<Topic>, ITopic {

    Connection connection = Connect.getInstance().getConnection();

    @Override
    public boolean insert(Topic obj) {
        // TODO Auto-generated method stub
        try{
             connection.setAutoCommit(false);
             String sql = "INSERT INTO topic (tpID, tpTitle, tpParent,tpStatus) VALUES (?, ?, ?, ?)";
             try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                 preparedStatement.setInt(1, obj.getTopicID());
                 preparedStatement.setString(2, obj.getTopicTitle());
                 preparedStatement.setInt(3, obj.getTopicParentID());
                 preparedStatement.setBoolean(4, obj.getTopicStatus());
                 preparedStatement.executeUpdate();
             }
             connection.commit();
            return true;
        }
        catch (Exception e) {
             log.error("Failed to insert topic: ", e);
             try {
                 connection.rollback();
             } catch (SQLException ex) {
                 log.error("Failed to rollback: ", ex);
             }
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                log.error("Failed to set auto commit: ", e);
            }
        }


        return false;
    }

    @Override
    public boolean update(Topic obj) {
        // TODO Auto-generated method stub
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE topic SET tpTitle = ?, tpParent = ?, tpStatus = ? WHERE tpID = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, obj.getTopicTitle());
                preparedStatement.setInt(2, obj.getTopicParentID());
                preparedStatement.setBoolean(3, obj.getTopicStatus());
                preparedStatement.setInt(4, obj.getTopicID());
                preparedStatement.executeUpdate();
            }
            connection.commit();
            return true;
        }
        catch (Exception e) {
            log.error("Failed to update topic: ", e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error("Failed to rollback: ", ex);
            }
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                log.error("Failed to set auto commit: ", e);
            }
        }


        return false;
    }

    @Override
    public boolean delete(String id) {
        // TODO Auto-generated method stub
        try {
            connection.setAutoCommit(false);
            String sql = "DELETE FROM topic WHERE tpID = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, id);
                preparedStatement.executeUpdate();
            }
            connection.commit();
            return true;
        }
        catch (Exception e) {
            log.error("Failed to delete topic: ", e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error("Failed to rollback: ", ex);
            }
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                log.error("Failed to set auto commit: ", e);
            }
        }


        return false;
    }

    @Override
    public Topic getById(String id) {
        // TODO Auto-generated method stub
        try {
            String sql = "SELECT * FROM topic WHERE tpID = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, id);
                preparedStatement.executeQuery();
            }
        }
        catch (Exception e) {
            log.error("Failed to get topic by id: ", e);
        }
        return null;
    }

    @Override
    public ArrayList<Topic> getAll() {
        // TODO Auto-generated method stub
        ArrayList<Topic> topics = new ArrayList<>();
        try {
            String sql = "SELECT * FROM topic";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Topic topic = new Topic();
                topic.setTopicID(resultSet.getInt("tpID"));
                topic.setTopicTitle(resultSet.getString("tpTitle"));
                topic.setTopicParentID(resultSet.getInt("tpParent"));
                topic.setTopicStatus(resultSet.getBoolean("tpStatus"));
                topics.add(topic);
            }
        }

        catch (Exception e) {
            log.error("Failed to get all topics: ", e);
        }

        return topics;
    }

    @Override
    public ArrayList<Topic> getTopicQuestionCounts() {
        ArrayList<Topic> topicQuestionCounts = new ArrayList<>();
        String sql = "SELECT t.tpID, t.tpTitle, " +
                "SUM(IF(q.qLevel = 'Easy', 1, 0)) AS SoCauDe, " +
                "SUM(IF(q.qLevel = 'Medium', 1, 0)) AS SoCauVua, " +
                "SUM(IF(q.qLevel = 'Diff', 1, 0)) AS SoCauKho " +
                "FROM topic t " +
                "LEFT JOIN Question q ON t.tpID = q.qTopicID " +
                "GROUP BY t.tpTitle";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Topic topicQuestionCount = new Topic();
                topicQuestionCount.setTopicID(resultSet.getInt("tpID"));
                topicQuestionCount.setTopicTitle(resultSet.getString("tpTitle"));
                topicQuestionCount.setNum_easy(resultSet.getInt("SoCauDe"));
                topicQuestionCount.setNum_medium(resultSet.getInt("SoCauVua"));
                topicQuestionCount.setNum_diff(resultSet.getInt("SoCauKho"));
                topicQuestionCounts.add(topicQuestionCount);
            }
        } catch (Exception e) {
            log.error("Failed to get topic question counts: ", e);
        }

        return topicQuestionCounts;
    }

}
