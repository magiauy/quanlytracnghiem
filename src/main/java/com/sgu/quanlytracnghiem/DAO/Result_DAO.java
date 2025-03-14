package com.sgu.quanlytracnghiem.DAO;

import com.sgu.quanlytracnghiem.DTO.Answers;
import com.sgu.quanlytracnghiem.DTO.Result;
import com.sgu.quanlytracnghiem.Interface.DAO.GenericDAO;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Result_DAO implements GenericDAO<Result> {
    Connection connection = Connect.getInstance().getConnection();
    GenericDAO<Answers> answers_dao = new Answers_DAO();


    @Override
        public boolean insert(Result obj) {
            try {
                connection.setAutoCommit(false);
                String sql = "INSERT INTO result (rsID,exID,userID,rsAnswers,rsMark,rsDate) VALUES (?, ?, ?, ?, ?,?)";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setInt(1, obj.getResultID());
                    ps.setString(2, obj.getExamID());
                    ps.setInt(3, obj.getUserID());
                    ps.setString(4, obj.getAnswers().toString());
                    ps.setBigDecimal(5, obj.getResultScore());
                    ps.setDate(6, Date.valueOf(obj.getResultDate()));
                    ps.executeUpdate();
                }
                connection.commit();

            } catch (SQLException e) {
                log.error("Failed to insert result: ", e);
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    log.error("Failed to rollback: ", ex);
                }
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    log.error("Failed to set auto commit: ", e);
                }
            }

        return false;
        }

        @Override
        public boolean update(Result obj) {
            try {
                connection.setAutoCommit(false);
                String sql = "UPDATE result SET exID = ?, userID = ?, rsAnswers = ?, rsMark = ?, rsDate = ? WHERE rsID = ?";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setString(1, obj.getExamID());
                    ps.setInt(2, obj.getUserID());
                    ps.setString(3, obj.getAnswers().toString());
                    ps.setBigDecimal(4, obj.getResultScore());
                    ps.setDate(5, Date.valueOf(obj.getResultDate()));
                    ps.setInt(6, obj.getResultID());
                    ps.executeUpdate();
                }
                connection.commit();
                return true;
            } catch (SQLException e) {
                log.error("Failed to update result: ", e);
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    log.error("Failed to rollback: ", ex);
                }
            } finally {
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
            try{
                connection.setAutoCommit(false);
                String sql = "DELETE FROM result WHERE rsID = ?";
                try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                    preparedStatement.setInt(1, Integer.parseInt(id));
                    preparedStatement.executeUpdate();
                }
                connection.commit();
                return true;
            } catch (Exception e) {
                log.error("Failed to delete result: ", e);
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    log.error("Failed to rollback: ", ex);
                }
            }


            return false;
        }

        @Override
        public Result getById(String id) {
        Result result = new Result();
        //Non rollback
            try {
                String sql = "SELECT * FROM result WHERE rsID = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, Integer.parseInt(id));
                    preparedStatement.executeQuery();
                    while (preparedStatement.getResultSet().next()) {
                        result.setResultID(preparedStatement.getResultSet().getInt("rsID"));
                        result.setExamID(preparedStatement.getResultSet().getString("exID"));
                        result.setUserID(preparedStatement.getResultSet().getInt("userID"));
                        result.setAnswers(getListAnswersByID(preparedStatement.getResultSet().getString("rsAnswers")));
                        result.setResultScore(preparedStatement.getResultSet().getBigDecimal("rsMark"));
                        result.setResultDate(preparedStatement.getResultSet().getDate("rsDate").toLocalDate());
                    }

                }
            } catch (SQLException e) {
                log.error("Failed to get result by id: ", e);
            }
            return result;
        }

        @Override
        public ArrayList<Result> getAll() {
        ArrayList<Result> results = new ArrayList<>();
        try {
            String sql = "SELECT * FROM result";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.executeQuery();
                while (preparedStatement.getResultSet().next()) {
                    Result result = new Result();
                    result.setResultID(preparedStatement.getResultSet().getInt("rsID"));
                    result.setExamID(preparedStatement.getResultSet().getString("exID"));
                    result.setUserID(preparedStatement.getResultSet().getInt("userID"));
                    result.setAnswers(getListAnswersByID(preparedStatement.getResultSet().getString("rsAnswers")));
                    result.setResultScore(preparedStatement.getResultSet().getBigDecimal("rsMark"));
                    result.setResultDate(preparedStatement.getResultSet().getDate("rsDate").toLocalDate());
                    results.add(result);
                }
            }
        } catch (SQLException e) {
            log.error("Failed to get all result: ", e);
        }
            return results;
        }
    public ArrayList<Answers> getListAnswersByID(String id){
        //id is 1,2,3,4,5
        ArrayList<Answers> answers = new ArrayList<>();
        String[] listID = id.split(",");
        for (String s : listID) {
            answers.add(answers_dao.getById(s));
        }
        return answers;
    }
    public String getUsernameById(String id) {
        String username = null;
        String sql = "SELECT username FROM user WHERE userID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                username = rs.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Hoặc log.error("Lỗi khi lấy username: ", e);
        }
        return username;
    }

    public String getQuestionContentById(String qID) {
        String qContent = null;
        String sql = "SELECT qContent FROM question WHERE qID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(qID));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                qContent = rs.getString("qContent");
            }
        } catch (SQLException e) {
            log.error("Failed to get question content by qID: ", e);
        }
        return qContent;
    }

    public ArrayList<Answers> getAnswersByQuestionId(String qID) {
        ArrayList<Answers> answersList = new ArrayList<>();
        String sql = "SELECT awID, awContent, awPictures, isRight, awStatus FROM answers WHERE qID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(qID));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Answers answer = new Answers();
                answer.setAnswerID(rs.getInt("awID"));
                answer.setAnswerContent(rs.getString("awContent"));
                answer.setAnswerPictures(rs.getString("awPictures"));
                answer.setIsRight(rs.getBoolean("isRight"));
                answer.setAnswerStatus(rs.getBoolean("awStatus"));
                answersList.add(answer);
            }
        } catch (SQLException e) {
            log.error("Failed to get answers by qID: ", e);
        }
        return answersList;
    }




}
