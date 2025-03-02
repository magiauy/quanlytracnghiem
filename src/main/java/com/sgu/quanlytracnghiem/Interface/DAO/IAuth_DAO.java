package com.sgu.quanlytracnghiem.Interface.DAO;

import com.sgu.quanlytracnghiem.DTO.User;

public interface IAuth_DAO {
    boolean checkLogin(String email, String password);

    User getUser(String email);
}
