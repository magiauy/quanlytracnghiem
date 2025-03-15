package com.sgu.quanlytracnghiem.BUS;

import com.sgu.quanlytracnghiem.DAO.Result_DAO;
import com.sgu.quanlytracnghiem.DTO.Result;
import com.sgu.quanlytracnghiem.DTO.Test;
import com.sgu.quanlytracnghiem.Interface.BUS.ResultChecking;
import com.sgu.quanlytracnghiem.Interface.DAO.IResult_DAO;

public class CheckingExam implements ResultChecking {
    IResult_DAO result_dao;

    public CheckingExam(){
        result_dao = new Result_DAO();
    }

    @Override
    public boolean check(Test test, int userID) {
        return test.getTestLimit() > result_dao.getAttemptCount(userID, test.getTestCode());
    }

    @Override
    public Result getResult(Test test, int userID) {
        return result_dao.getActiveResult(userID, test.getTestCode());
    }
}
