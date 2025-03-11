package com.sgu.quanlytracnghiem.Interface.DAO;

import com.sgu.quanlytracnghiem.DTO.Answers;

import java.util.List;

public interface IAnswers_DAO {
    boolean deleteMissingAnswers(int questionID, List<Integer> answerID);
    int insertInt(Answers answers);
}
