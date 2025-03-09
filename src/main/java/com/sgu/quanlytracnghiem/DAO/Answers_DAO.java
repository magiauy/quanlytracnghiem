package com.sgu.quanlytracnghiem.DAO;

import com.sgu.quanlytracnghiem.DTO.Answers;
import com.sgu.quanlytracnghiem.Interface.DAO.GenericDAO;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
@Slf4j

public class Answers_DAO implements GenericDAO<Answers> {
    Connection connection = Connect.getInstance().getConnection();


    @Override
    public boolean insert(Answers obj) {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO answers (awID,qID, awContent,awPicTures,isRight,awStatus) VALUES (?, ?, ?, ?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, obj.getAnswerID());
                ps.setInt(2, obj.getQuestionID());
                ps.setString(3, obj.getAnswerContent());
                ps.setString(4, obj.getAwPicture());
                ps.setBoolean(5, obj.isAnswerCorrect());
                ps.setBoolean(6, obj.isAnswerStatus());
                ps.executeUpdate();
            }
            connection.commit();
            return true;
        } catch (Exception e) {
            log.error("Failed to insert answers: ", e);
            try {
                connection.rollback();
            } catch (Exception ex) {
                log.error("Failed to rollback: ", ex);
            }
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (Exception e) {
                log.error("Failed to set auto commit: ", e);
            }
        }
    }
    @Override
    public boolean update(Answers obj) {
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE answers SET qID = ?, awContent = ?, awPicTures = ?, isRight = ?, awStatus = ? WHERE awID = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, obj.getQuestionID());
                ps.setString(2, obj.getAnswerContent());
                ps.setString(3, obj.getAwPicture());
                ps.setBoolean(4, obj.isAnswerCorrect());
                ps.setBoolean(5, obj.isAnswerStatus());
                ps.setInt(6, obj.getAnswerID());
                ps.executeUpdate();
            }
            connection.commit();
            return true;
        } catch (Exception e) {
            log.error("Failed to update answers: ", e);
            try {
                connection.rollback();
            } catch (Exception ex) {
                log.error("Failed to rollback: ", ex);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (Exception e) {
                log.error("Failed to set auto commit: ", e);
            }
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        try {
            connection.setAutoCommit(false);
            String sql = "DELETE FROM answers WHERE awID = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, id);
                ps.executeUpdate();
            }
            connection.commit();
            return true;
        } catch (Exception e) {
            log.error("Failed to delete answers: ", e);
            try {
                connection.rollback();
            } catch (Exception ex) {
                log.error("Failed to rollback: ", ex);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (Exception e) {
                log.error("Failed to set auto commit: ", e);
            }
        }
        return false;
    }

    @Override
    public Answers getById(String id) {
        Answers answers = new Answers();
        try {
            String sql = "SELECT * FROM answers WHERE awID = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, id);
                ps.executeQuery();
            }
        } catch (Exception e) {
            log.error("Failed to get answers by id: ", e);
        }
        return answers;
    }

    @Override
    public ArrayList<Answers> getAll() {
        ArrayList<Answers> answers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM answers";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.executeQuery();
                while (ps.getResultSet().next()) {
                    Answers answer = new Answers();
                    answer.setAnswerID(ps.getResultSet().getInt("awID"));
                    answer.setQuestionID(ps.getResultSet().getInt("qID"));
                    answer.setAnswerContent(ps.getResultSet().getString("awContent"));
                    answer.setAwPicture(ps.getResultSet().getString("awPicTures"));
                    answer.setAnswerCorrect(ps.getResultSet().getBoolean("isRight"));
                    answer.setAnswerStatus(ps.getResultSet().getBoolean("awStatus"));
                    answers.add(answer);
                }
            }
        } catch (Exception e) {
            log.error("Failed to get all answers: ", e);
        }
        return answers;
    }
}
