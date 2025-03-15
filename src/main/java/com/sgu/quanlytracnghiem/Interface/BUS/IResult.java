package com.sgu.quanlytracnghiem.Interface.BUS;

import com.sgu.quanlytracnghiem.DTO.Answers;
import com.sgu.quanlytracnghiem.DTO.Exam;
import com.sgu.quanlytracnghiem.DTO.Result;

public interface IResult {
    void updateResult( int index, Answers answer);

    void add(Result result);

    void submitResult(Result result);

    void setResult(Result result);
}
