package com.sgu.quanlytracnghiem.Interface.DAO;

import com.sgu.quanlytracnghiem.DTO.Result;

public interface IResult_DAO {
    int getAttemptCount(int userID, String testCode);
    Result getActiveResult (int userID, String testCode);
}
