package com.sgu.quanlytracnghiem.BUS;

import com.sgu.quanlytracnghiem.DAO.User_DAO;
import com.sgu.quanlytracnghiem.Interface.BUS.IAuth;

public class Auth_BUS implements IAuth {
    User_DAO user_dao = new User_DAO();
    public boolean login(String username, String password) {
        return user_dao.checkLogin(username, password);
    }

}
