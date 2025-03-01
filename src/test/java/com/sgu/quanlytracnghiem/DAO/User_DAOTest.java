package com.sgu.quanlytracnghiem.DAO;

import com.sgu.quanlytracnghiem.DTO.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class User_DAOTest {

    @Test
    void insert() {
        User user = new User(1, "admin", "admin@123", "admin@gmail.com", "admin", true);

        User_DAO user_dao = new User_DAO();
        assertTrue(user_dao.insert(user));

    }

    @Test
    void login(){
        User_DAO user_dao = new User_DAO();
        assertTrue(user_dao.checkLogin("admin@gmail.com", "admin@123"));
    }

    @Test
    void update() {

        User_DAO user_dao = new User_DAO();
        User user = new User(1, "admin2", "admin@123", "admin@gmail.com", "admin", true);
        assertTrue(user_dao.update(user));

    }

    @Test
    void delete() {
        User_DAO user_dao = new User_DAO();
        assertTrue(user_dao.delete("1"));
    }

}