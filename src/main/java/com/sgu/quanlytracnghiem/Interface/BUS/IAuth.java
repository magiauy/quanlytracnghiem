package com.sgu.quanlytracnghiem.Interface.BUS;

import com.sgu.quanlytracnghiem.DTO.User;

public interface IAuth {
    boolean login(String email, String password);

    User getUser(String email);

    boolean changePassword(String email, String password, String newPassword);
}
