package com.sgu.quanlytracnghiem.BUS;

import com.sgu.quanlytracnghiem.DAO.User_DAO;
import com.sgu.quanlytracnghiem.DTO.User;
import com.sgu.quanlytracnghiem.Interface.BUS.CRUD;
import com.sgu.quanlytracnghiem.Interface.DAO.GenericDAO;

import java.util.ArrayList;

public class User_BUS implements CRUD<User> {
    ArrayList<User> users ;
    GenericDAO<User> user_dao = new User_DAO();
    public User_BUS() {
        users = user_dao.getAll();
    }

    public ArrayList<User> getAll() {
        return users;
    }

    public User getByID(String id) {
        for (User user : users) {
            if (user.getId() == Integer.parseInt(id)) {
                return user;
            }
        }
        return null;
    }

    public void add(User obj) {
        user_dao.insert(obj);
        users.add(obj);
    }

    public void update(User obj) {
        user_dao.update(obj);
        for (User user : users) {
            if (user.getId() == obj.getId()) {
                user.setUsername(obj.getUsername());
                user.setPassword(obj.getPassword());
                user.setEmail(obj.getEmail());
                user.setFullName(obj.getFullName());
                user.setAdmin(obj.isAdmin());
            }
        }
    }

    public void delete(String id) {
        user_dao.delete(id);
        for (User user : users) {
            if (user.getId() == Integer.parseInt(id)) {
                users.remove(user);
                break;
            }
        }
    }
}
