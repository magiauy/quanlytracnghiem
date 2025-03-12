package com.sgu.quanlytracnghiem.DAO;

import com.sgu.quanlytracnghiem.DTO.Question;
import com.sgu.quanlytracnghiem.DTO.QuestionLevel;
import com.sgu.quanlytracnghiem.Interface.BUS.IdGenerate;
import com.sgu.quanlytracnghiem.Interface.DAO.GenericDAO;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
@Slf4j

public class Question_DAO implements GenericDAO<Question>, IdGenerate {
    Connection connection = Connect.getInstance().getConnection();
    public boolean insert(Question obj) {
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO question (qID, qContent, qPictures,qTopicID,qLevel,qStatus) VALUES (?, ?, ?, ?, ?, ?)";

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, obj.getQuestionID());
                preparedStatement.setString(2, obj.getQuestionContent());
                preparedStatement.setString(3, obj.getQuestionPicture());
                preparedStatement.setInt(4, obj.getTopicID());
                preparedStatement.setString(5, obj.getQuestionLevel().toString());
                preparedStatement.setInt(6, obj.isQuestionStatus() ? 1 : 0);
                preparedStatement.executeUpdate();
            }
            connection.commit();
            return true;
        }
        catch (Exception e) {
            log.error("Failed to insert question: ", e);
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

    public boolean update(Question obj) {

        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE question SET qContent = ?, qPictures = ?, qTopicID = ?, qLevel = ?, qStatus = ? WHERE qID = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, obj.getQuestionContent());
                preparedStatement.setString(2, obj.getQuestionPicture());
                preparedStatement.setInt(3, obj.getTopicID());
                preparedStatement.setString(4, obj.getQuestionLevel().toString());
                preparedStatement.setInt(5, obj.isQuestionStatus() ? 1 : 0);
                preparedStatement.setInt(6, obj.getQuestionID());
                preparedStatement.executeUpdate();
            }
            connection.commit();
            return true;
        }
        catch (Exception e) {
            log.error("Failed to update question: ", e);
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

    public boolean delete(String id) {
        try {
            connection.setAutoCommit(false);
            String sql = "DELETE FROM question WHERE qID = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, id);
                preparedStatement.executeUpdate();
            }
            connection.commit();
            return true;
        }
        catch (Exception e) {
            log.error("Failed to delete question: ", e);
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

    public Question getById(String id) {
        Question question = new Question();
        try {
            String sql = "SELECT * FROM question WHERE qID = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, id);
                preparedStatement.executeQuery();
                ResultSet resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    question.setQuestionID(resultSet.getInt("qID"));
                    question.setQuestionContent(resultSet.getString("qContent"));
                    question.setQuestionPicture(resultSet.getString("qPictures"));
                    question.setTopicID(resultSet.getInt("qTopicID"));
                    question.setQuestionLevel(getQuestionLevel(resultSet.getString("qLevel")));
                    question.setQuestionStatus(resultSet.getInt("qStatus") == 1);
                }
            }
        }
        catch (Exception e) {
            log.error("Failed to get question by id: ", e);
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                log.error("Failed to set auto commit: ", e);
            }
        }
        return question;
    }

    public ArrayList<Question> getAll() {
        ArrayList<Question> questions = new ArrayList<>();
        try {
            String sql = "SELECT * FROM question";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.executeQuery();
                ResultSet resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    Question question = new Question();
                    question.setQuestionID(resultSet.getInt("qID"));
                    question.setQuestionContent(resultSet.getString("qContent"));
                    question.setQuestionPicture(resultSet.getString("qPictures"));
                    question.setTopicID(resultSet.getInt("qTopicID"));
                    question.setQuestionLevel(getQuestionLevel(resultSet.getString("qLevel")));
                    question.setQuestionStatus(resultSet.getInt("qStatus") == 1);
                    questions.add(question);
                }

            }
        }
        catch (Exception e) {
            log.error("Failed to get all questions: ", e);
        }
        return questions;
    }

    public QuestionLevel getQuestionLevel(String level) {
        return switch (level) {
            case "easy" -> QuestionLevel.EASY;
            case "medium" -> QuestionLevel.MEDIUM;
            case "diff" -> QuestionLevel.DIFFICULT;
            default -> null;
        };
    }

    public int generateId() {
        try {
            String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = 'question'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs.next() ? rs.getInt(1) : -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
