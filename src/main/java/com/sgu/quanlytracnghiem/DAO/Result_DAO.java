package com.sgu.quanlytracnghiem.DAO;

import com.sgu.quanlytracnghiem.DTO.Answers;
import com.sgu.quanlytracnghiem.DTO.Result;
import com.sgu.quanlytracnghiem.Interface.BUS.IdGenerate;
import com.sgu.quanlytracnghiem.Interface.DAO.GenericDAO;
import com.sgu.quanlytracnghiem.Interface.DAO.IResult_DAO;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Result_DAO implements GenericDAO<Result>, IdGenerate , IResult_DAO {
    Connection connection = Connect.getInstance().getConnection();
    GenericDAO<Answers> answers_dao = new Answers_DAO();


    @Override
        public boolean insert(Result obj) {
            try {
                connection.setAutoCommit(false);
                String sql = "INSERT INTO result (rsID,exID,userID,rsAnswers,rsMark,rsDate,isSubmit) VALUES (?, ?,?, ?, ?, ?,?)";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setInt(1, obj.getResultID());
                    ps.setString(2, obj.getExamID());
                    ps.setInt(3, obj.getUserID());
                    ps.setString(4, obj.getAnswers().toString());
                    ps.setBigDecimal(5, obj.getResultScore());
                    ps.setTimestamp(6, Timestamp.valueOf(obj.getResultDate()));
                    ps.setInt(7, obj.isSubmit()?1:0);
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
                String sql = "UPDATE result SET exID = ?, userID = ?, rsAnswers = ?, rsMark = ?, rsDate = ? , isSubmit = ? WHERE rsID = ?";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setString(1, obj.getExamID());
                    ps.setInt(2, obj.getUserID());
                    ps.setString(3, obj.getAnswers().toString());
                    ps.setBigDecimal(4, obj.getResultScore());
                    ps.setTimestamp(5, Timestamp.valueOf(obj.getResultDate()));
                    ps.setInt(6, obj.isSubmit()?1:0);
                    ps.setInt(7, obj.getResultID());

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
                        result.setResultDate(preparedStatement.getResultSet().getTimestamp("rsDate").toLocalDateTime());
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
                    result.setResultDate(preparedStatement.getResultSet().getTimestamp("rsDate").toLocalDateTime());
                    results.add(result);
                }
            }
        } catch (SQLException e) {
            log.error("Failed to get all result: ", e);
        }
            return results;
        }
    public ArrayList<Answers> getListAnswersByID(String id) {
        ArrayList<Answers> answers = new ArrayList<>();
        if (id == null || id.isEmpty()) {
            return answers;
        }
        // Loại bỏ khoảng trắng thừa và dấu ngoặc vuông
        id = id.trim();
        if (id.startsWith("[") && id.endsWith("]")) {
            id = id.substring(1, id.length() - 1);
        }
        // Tách các id theo dấu phẩy và loại bỏ khoảng trắng ở mỗi phần tử
        String[] listID = id.split(",");
        for (String s : listID) {
            s = s.trim();
            if (!s.isEmpty()) {
                if (s.equals("null")) {
                    answers.add(null);
                }else {
                    Answers answer = answers_dao.getById(s);
                    answers.add(answer);
                }
            }else {
                answers.add(null);
            }
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
          log.error("Lỗi khi lấy username: ", e);
        }
        return username;
    }

    @Override
    public int generateId() {
        try {
            String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = 'result'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            return rs.next() ? rs.getInt(1) : -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getAttemptCount(int userId, String testCode) {
        int attemptCount = 0;
        String sql = """
            SELECT e.testCode, r.userID, COUNT(r.exID) AS attempt_count
            FROM exam e
            LEFT JOIN result r ON e.exCode = r.exID
            WHERE r.userID = ? AND e.testCode = ?
            GROUP BY e.testCode, r.userID
        """;

        try{
             PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, userId);
            stmt.setString(2, testCode);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    attemptCount = rs.getInt("attempt_count");
                }
            }
        } catch (Exception e) {
            log.error("Failed to get attempt count: ", e);
        }
        return attemptCount;
    }
    public Result getActiveResult(int userId, String testCode) {
        Result resultObj = null;
        String sql = """
            SELECT 
                r.rsDate, 
                r.rsID,
                r.exID,
                r.rsAnswers,
                t.testTime,
                (r.rsDate + INTERVAL t.testTime MINUTE) AS expiryDate
            FROM result r
            JOIN exam e ON r.exID = e.exCode
            JOIN test t ON e.testCode = t.testCode
            WHERE t.testCode = ? 
              AND r.userID = ?
              AND r.isSubmit = 0
              AND (r.rsDate + INTERVAL t.testTime MINUTE) > NOW()
            ORDER BY r.rsDate DESC
            LIMIT 1
            """;

        // Sử dụng connection đã có
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, testCode);
            stmt.setInt(2, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    resultObj = new Result();
                    resultObj.setResultID(rs.getInt("rsID"));
                    resultObj.setExamID(rs.getString("exID"));
                    Timestamp ts = rs.getTimestamp("rsDate");
                    if (ts != null) {
                        resultObj.setResultDate(ts.toLocalDateTime());
                    }
                    resultObj.setUserID(userId);
                    resultObj.setSubmit(false);  // Vì isSubmit = 0 trong truy vấn

                    // Lấy dữ liệu answers và chuyển đổi sang ArrayList<Answers>
                    String answersStr = rs.getString("rsAnswers");
                    resultObj.setAnswers(getListAnswersByID(answersStr));

                    // Nếu cần, có thể xử lý các trường khác như resultScore (nếu có)
                }
            }
        } catch (SQLException e) {
            log.error("Failed to get active result: ", e);
        }
        return resultObj;
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
                answer.setAwPicture(rs.getString("awPictures"));
                answer.setAnswerCorrect(rs.getBoolean("isRight"));
                answer.setAnswerStatus(rs.getBoolean("awStatus"));
                answersList.add(answer);
            }
        } catch (SQLException e) {
            log.error("Failed to get answers by qID: ", e);
        }
        return answersList;
    }




}
