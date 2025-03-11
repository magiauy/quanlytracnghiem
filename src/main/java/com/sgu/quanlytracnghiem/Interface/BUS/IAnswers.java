package com.sgu.quanlytracnghiem.Interface.BUS;

import com.sgu.quanlytracnghiem.DTO.Answers;

import java.util.ArrayList;

public interface IAnswers {
    ArrayList<Answers> getAnswersByQuestionID(int questionID);
    void saveAnswersToDB(ArrayList<Answers> answers, int questionID);
}
