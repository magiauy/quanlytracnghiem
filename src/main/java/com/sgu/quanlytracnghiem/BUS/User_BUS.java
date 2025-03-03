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

    public boolean add(User obj) {
        if (user_dao.insert(obj)) {
            users.add(obj);
            return true;
        }
        return false;
    }

    public boolean update(User obj) {
        if (user_dao.update(obj)) {
            for (User user : users) {
                if (user.getId() == obj.getId()) {
                    user.setUsername(obj.getUsername());
                    user.setPassword(obj.getPassword());
                    user.setEmail(obj.getEmail());
                    user.setFullName(obj.getFullName());
                    user.setAdmin(obj.isAdmin());
                    return true;
                }
            }
        }
        return false;
    }

    public boolean delete(String id) {
        if (user_dao.delete(id)) {
            for (User user : users) {
                if (user.getId() == Integer.parseInt(id)) {
                    users.remove(user);
                    return true;
                }
            }
        }
        return false;
    }
}
