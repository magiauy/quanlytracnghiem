package com.sgu.quanlytracnghiem.DAO;

import com.sgu.quanlytracnghiem.DTO.Exam;
import com.sgu.quanlytracnghiem.DTO.Question;
import com.sgu.quanlytracnghiem.Interface.DAO.GenericDAO;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Slf4j
public class Exam_DAO implements GenericDAO<Exam> {
    Connection connection = Connect.getInstance().getConnection();
    GenericDAO<Question> question_dao = new Question_DAO();


    @Override
        public boolean insert(Exam obj) {
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO exam (exCode, testCode, exOrder, ex_Questions) VALUES (?, ?, ?, ?)";

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, obj.getExamID());
                preparedStatement.setString(2, obj.getTestID());
                preparedStatement.setString(3, obj.getExamOrder());
                preparedStatement.setString(4, getQuestionArr(obj.getQuestions()));
                preparedStatement.executeUpdate();
            }
            connection.commit();
            return true;
        }
        catch (Exception e) {
            log.error("Failed to insert exam: ", e);
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
        public boolean update(Exam obj) {
            try {
                connection.setAutoCommit(false);
                String sql = "UPDATE exam SET exCode = ?, testCode = ?, exOrder = ?, ex_Questions = ? WHERE exID = ?";
                try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, obj.getExamID());
                    preparedStatement.setString(2, obj.getTestID());
                    preparedStatement.setString(3, obj.getExamOrder());
                    preparedStatement.setString(4, getQuestionArr(obj.getQuestions()));
                    preparedStatement.setString(5, obj.getExamID());
                    preparedStatement.executeUpdate();
                }
                connection.commit();
                return true;
            }
            catch (Exception e) {
                log.error("Failed to update exam: ", e);
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
            try {
                connection.setAutoCommit(false);
                String sql = "DELETE FROM exam WHERE exCode = ?";
                try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, id);
                    preparedStatement.executeUpdate();
                }
                connection.commit();
                return true;
            }
            catch (Exception e) {
                log.error("Failed to delete exam: ", e);
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    log.error("Failed to rollback: ", ex);
                }finally {
                    try {
                        connection.setAutoCommit(true);
                    } catch (SQLException ex) {
                        log.error("Failed to set auto commit: ", ex);
                    }
                }
            }
            return false;
        }

        @Override
        public Exam getById(String id) {
        Exam exam = new Exam();
        try {
            String sql = "SELECT * FROM exam WHERE exCode = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, id);
                preparedStatement.executeQuery();
                while (preparedStatement.getResultSet().next()) {
                    exam.setExamID(preparedStatement.getResultSet().getString("exCode"));
                    exam.setTestID(preparedStatement.getResultSet().getString("testCode"));
                    exam.setExamOrder(preparedStatement.getResultSet().getString("exOrder"));
                    exam.setQuestions(getQuestionList(preparedStatement.getResultSet().getString("ex_Questions")));
                }
            }
        } catch (Exception e) {
            log.error("Failed to get exam by ID: ", e);
        }
            return exam;
        }

        @Override
        public ArrayList<Exam> getAll() {
        ArrayList<Exam> exams = new ArrayList<>();

        try {
            String sql = "SELECT * FROM exam";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.executeQuery();
                while (preparedStatement.getResultSet().next()) {
                    Exam exam = new Exam();
                    exam.setExamID(preparedStatement.getResultSet().getString("exCode"));
                    exam.setTestID(preparedStatement.getResultSet().getString("testCode"));
                    exam.setExamOrder(preparedStatement.getResultSet().getString("exOrder"));
                    exam.setQuestions(getQuestionList(preparedStatement.getResultSet().getString("ex_Questions")));
                    exams.add(exam);
                }
            }
        } catch (Exception e) {
            log.error("Failed to get all exams: ", e);
        }
            return exams;
        }

        public String getQuestionArr(ArrayList<Question> questions){
            return questions.stream()
                    .map(Question::toString)
                    .collect(Collectors.joining(","));
        }

        public ArrayList<Question> getQuestionList(String questions){
            ArrayList<Question> questionList = new ArrayList<>();
            String[] questionArr = questions.split(",");
            for (String question : questionArr) {
                Question q = question_dao.getById(question);
                questionList.add(q);
            }
            return questionList;
        }

}
