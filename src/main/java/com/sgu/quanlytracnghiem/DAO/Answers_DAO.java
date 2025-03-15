package com.sgu.quanlytracnghiem.DAO;

import com.sgu.quanlytracnghiem.DTO.Answers;
import com.sgu.quanlytracnghiem.Interface.DAO.GenericDAO;
import com.sgu.quanlytracnghiem.Interface.DAO.IAnswers_DAO;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j

public class Answers_DAO implements GenericDAO<Answers>, IAnswers_DAO {
    Connection connection = Connect.getInstance().getConnection();



    @Override
    public boolean insert(Answers obj) {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO answers (qID, awContent,awPicTures,isRight,awStatus) VALUES (?, ?, ?, ?,?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, obj.getQuestionID());
                ps.setString(2, obj.getAnswerContent());
                ps.setString(3, obj.getAwPicture());
                ps.setBoolean(4, obj.isAnswerCorrect());
                ps.setBoolean(5, obj.isAnswerStatus());
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
                ResultSet rs = ps.getResultSet();
                if (rs.next()) {
                    answers.setAnswerID(rs.getInt("awID"));
                    answers.setQuestionID(rs.getInt("qID"));
                    answers.setAnswerContent(rs.getString("awContent"));
                    answers.setAwPicture(rs.getString("awPicTures"));
                    answers.setAnswerCorrect(rs.getBoolean("isRight"));
                    answers.setAnswerStatus(rs.getBoolean("awStatus"));
                }
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

    @Override
    public boolean deleteMissingAnswers(int questionID, List<Integer> answerIDs) {
        String sql = "DELETE FROM answers WHERE qID = ? AND awID NOT IN (" +
                answerIDs.stream().map(id -> "?").collect(Collectors.joining(",")) + ")";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, questionID);
            for (int i = 0; i < answerIDs.size(); i++) {
                stmt.setInt(i + 2, answerIDs.get(i)); // Bắt đầu từ vị trí 2
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to delete answers: ", e);
        }
        return false;
    }

    @Override
    public int insertInt(Answers obj) {
        //insert xong return về ID đã được AI lên
        int generatedID = -1; // Mặc định là -1 để kiểm tra lỗi
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO answers (qID, awContent,awPicTures,isRight,awStatus) VALUES (?, ?, ?, ?,?)";
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, obj.getQuestionID());
                ps.setString(2, obj.getAnswerContent());
                ps.setString(3, obj.getAwPicture());
                ps.setBoolean(4, obj.isAnswerCorrect());
                ps.setBoolean(5, obj.isAnswerStatus());
                int affectedRows = ps.executeUpdate();
                if (affectedRows > 0) {
                    // Lấy ID vừa được tạo
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            generatedID = rs.getInt(1); // Lấy ID mới
                        }
                    }
                }
            }
            connection.commit();
        } catch (Exception e) {
            log.error("Failed to insert answers: ", e);
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
        return generatedID; // Trả về ID mới, nếu thất bại trả về -1
    }
}
