package com.sgu.quanlytracnghiem.DAO;

import com.sgu.quanlytracnghiem.DTO.User;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class User_DAOTest {

    @Test
    @Order(1)
    void insert() {
        User user = new User(2, "admin", "admin@123", "admin@gmail.com", "admin", true);

        User_DAO user_dao = new User_DAO();
        assertTrue(user_dao.insert(user));

    }

    @Test
    @Order(2)
    void login(){
        User_DAO user_dao = new User_DAO();
        assertTrue(user_dao.checkLogin("admin@gmail.com", "admin@123"));
    }

    @Test
    @Order(3)
    void update() {

        User_DAO user_dao = new User_DAO();
        User user = new User(2, "admin2", "admin@123", "admin@gmail.com", "admin", true);
        assertTrue(user_dao.update(user));

    }

    @Test
    @Order(4)
    void delete() {
        User_DAO user_dao = new User_DAO();
        assertTrue(user_dao.delete("2"));
    }

}