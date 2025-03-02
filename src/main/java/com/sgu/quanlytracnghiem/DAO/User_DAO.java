package com.sgu.quanlytracnghiem.DAO;

import com.sgu.quanlytracnghiem.DTO.User;
import com.sgu.quanlytracnghiem.Interface.DAO.GenericDAO;
import com.sgu.quanlytracnghiem.Interface.DAO.IAuth_DAO;
import com.sgu.quanlytracnghiem.Util.PasswordUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;


@Slf4j
public class User_DAO implements GenericDAO<User>, IAuth_DAO {
    Connection connection = Connect.getInstance().getConnection();

    @Override
    public boolean insert(User obj) {
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO user (userID,userName, userEmail, userPassword, userFullName, isAdmin) VALUES (?, ?, ?, ?, ?, ?)";

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, obj.getId());
                preparedStatement.setString(2, obj.getUsername());
                preparedStatement.setString(3, obj.getEmail());
                preparedStatement.setString(4, PasswordUtil.hashPassword(obj.getPassword()));
                preparedStatement.setString(5, obj.getFullName());
                preparedStatement.setBoolean(6, obj.isAdmin());
                preparedStatement.executeUpdate();
            }
            connection.commit();
            return true;
        }
        catch (Exception e) {
            log.error("Failed to insert user: ", e);
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
    public boolean update(User obj) {
        // TODO Auto-generated method stub
        try{
            connection.setAutoCommit(false);

            String sql = "UPDATE user SET userName = ?, userEmail = ?, userFullName = ?, isAdmin = ? WHERE userID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1, obj.getUsername());
                preparedStatement.setString(2, obj.getEmail());
                preparedStatement.setString(3, obj.getFullName());
                preparedStatement.setBoolean(4, obj.isAdmin());
                preparedStatement.setInt(5, obj.getId());
                preparedStatement.executeUpdate();
            }
            connection.commit();
            return true;

        } catch (Exception e) {
            log.error("Failed to update user: ", e);
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

            String sql = "DELETE FROM user WHERE userID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, Integer.parseInt(id));
                preparedStatement.executeUpdate();
            }
            connection.commit();
            return true;
        } catch (Exception e) {
            log.error("Failed to delete user: ", e);
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
    public User getById(String id) {
        // TODO Auto-generated method stub
        try {
            String sql = "SELECT * FROM user WHERE userID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("userID"));
                user.setUsername(resultSet.getString("userName"));
                user.setEmail(resultSet.getString("userEmail"));
                user.setFullName(resultSet.getString("userFullName"));
                user.setAdmin(resultSet.getBoolean("isAdmin"));
                return user;
            }
        } catch (Exception e) {
            log.error("Failed to get user by id: ", e);
        }


        return null;
    }

    @Override
    public ArrayList<User> getAll() {
        // TODO Auto-generated method stub
        ArrayList<User> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM user";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("userID"));
                user.setUsername(resultSet.getString("userName"));
                user.setEmail(resultSet.getString("userEmail"));
                user.setFullName(resultSet.getString("userFullName"));
                user.setAdmin(resultSet.getBoolean("isAdmin"));
                users.add(user);
            }
        } catch (Exception e) {
            log.error("Failed to get all users: ", e);
        }
        return users;
    }

    public boolean checkLogin(String email, String password) {
        try {
            String sql = "SELECT * FROM user WHERE userEmail = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String hashedPassword = resultSet.getString("userPassword");
                return PasswordUtil.checkPassword(password, hashedPassword);
            }
        } catch (Exception e) {
            log.error("Failed to check login: ", e);
        }
        return false;
    }

    public boolean updatePassword(String email, String password) {
        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE user SET userPassword = ? WHERE userEmail = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, PasswordUtil.hashPassword(password));
                preparedStatement.setString(2, email);
                preparedStatement.executeUpdate();
            }
            connection.commit();
            return true;
        } catch (Exception e) {
            log.error("Failed to update password: ", e);
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

    public User getUser(String email) {
        try {
            String sql = "SELECT * FROM user WHERE userEmail = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("userID"));
                user.setUsername(resultSet.getString("userName"));
                user.setEmail(resultSet.getString("userEmail"));
                user.setFullName(resultSet.getString("userFullName"));
                user.setAdmin(resultSet.getBoolean("isAdmin"));
                return user;
            }
        } catch (Exception e) {
            log.error("Failed to get user by email: ", e);
        }
        return null;
    }
}
