package com.sgu.quanlytracnghiem.BUS;

import com.sgu.quanlytracnghiem.DTO.Question;

import java.util.ArrayList;

public interface IQuestion {
    ArrayList<Question> getQuestionByTopic(int topicID);
}
