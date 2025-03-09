package com.sgu.quanlytracnghiem.DAO;

import com.sgu.quanlytracnghiem.DTO.Logs;
import com.sgu.quanlytracnghiem.Interface.DAO.GenericDAO;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
@Slf4j
public class Logs_DAO implements GenericDAO<Logs> {
    Connection connection = Connect.getInstance().getConnection();


    @Override
        public boolean insert(Logs obj) {
            try {
                connection.setAutoCommit(false);
                String sql = "INSERT INTO logs (logsID,logUserID,logExID, logContent, logDate) VALUES (?, ?, ?, ?,?)";
                try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, obj.getLogsID());
                    preparedStatement.setInt(2, obj.getLogUserID());
                    preparedStatement.setString(3, obj.getLogExamID());
                    preparedStatement.setString(4, obj.getLogContent());
                    preparedStatement.setDate(5, Date.valueOf(obj.getLogTime()));

                    preparedStatement.executeUpdate();
                }
                connection.commit();
                return true;
            }
            catch (Exception e) {
                log.error("Failed to insert logs: ", e);
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    log.error("Failed to rollback: ", ex);
                }
            }

            return false;
        }

        @Override
        public boolean update(Logs obj) {
            try {
                connection.setAutoCommit(false);
                String sql = "UPDATE logs SET logUserID = ?, logExID = ?, logContent = ?, logDate = ? WHERE logsID = ?";
                try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, obj.getLogUserID());
                    preparedStatement.setString(2, obj.getLogExamID());
                    preparedStatement.setString(3, obj.getLogContent());
                    preparedStatement.setDate(4, Date.valueOf(obj.getLogTime()));
                    preparedStatement.setInt(5, obj.getLogsID());
                    preparedStatement.executeUpdate();
                }
                connection.commit();
                return true;
            }
            catch (Exception e) {
                log.error("Failed to update logs: ", e);
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
        public boolean delete(String id) {
            try {
                connection.setAutoCommit(false);
                String sql = "DELETE FROM logs WHERE logsID = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, id);
                    preparedStatement.executeUpdate();
                }
                connection.commit();
                return true;
            } catch (Exception e) {
                log.error("Failed to delete logs: ", e);
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
        public Logs getById(String id) {
        Logs logs = new Logs();
        //Non rollback
            try {
                String sql = "SELECT * FROM logs WHERE logsID = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, Integer.parseInt(id));
                    preparedStatement.executeQuery();
                }
            } catch (Exception e) {
                log.error("Failed to get logs by id: ", e);
            }
            return logs;
        }

        @Override
        public ArrayList<Logs> getAll() {
            ArrayList<Logs> logs = new ArrayList<>();
            try {
                String sql = "SELECT * FROM logs";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.executeQuery();
                    while (preparedStatement.getResultSet().next()) {
                        Logs log = new Logs();
                        log.setLogsID(preparedStatement.getResultSet().getInt("logsID"));
                        log.setLogUserID(preparedStatement.getResultSet().getInt("logUserID"));
                        log.setLogExamID(preparedStatement.getResultSet().getString("logExID"));
                        log.setLogContent(preparedStatement.getResultSet().getString("logContent"));
                        log.setLogTime(preparedStatement.getResultSet().getDate("logDate").toLocalDate());
                        logs.add(log);
                    }
                }
            } catch (Exception e) {
                log.error("Failed to get all logs: ", e);
            }
            return logs;
        }
}
