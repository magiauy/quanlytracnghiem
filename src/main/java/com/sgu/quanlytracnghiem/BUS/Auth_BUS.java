package com.sgu.quanlytracnghiem.BUS;

import com.sgu.quanlytracnghiem.DAO.User_DAO;
import com.sgu.quanlytracnghiem.DTO.User;
import com.sgu.quanlytracnghiem.Interface.BUS.IAuth;
import com.sgu.quanlytracnghiem.Interface.DAO.IAuth_DAO;

public class Auth_BUS implements IAuth {
    IAuth_DAO user_dao = new User_DAO();
    public boolean login(String username, String password) {
        return user_dao.checkLogin(username, password);
    }

    public User getUser(String email) {
        return user_dao.getUser(email);
    }

}
