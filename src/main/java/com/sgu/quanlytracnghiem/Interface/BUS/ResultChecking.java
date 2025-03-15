package com.sgu.quanlytracnghiem.Interface.BUS;

import com.sgu.quanlytracnghiem.DTO.Result;
import com.sgu.quanlytracnghiem.DTO.Test;
import com.sgu.quanlytracnghiem.DTO.User;

public interface ResultChecking {
    boolean check(Test test , int userID);

    Result getResult(Test test , int userID);

}
