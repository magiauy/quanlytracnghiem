package com.sgu.quanlytracnghiem.BUS;

import com.sgu.quanlytracnghiem.DTO.Answers;
import com.sgu.quanlytracnghiem.DTO.Result;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TempResult_BUSTest {

    @Test
    void updateResult() {
        int n = 10; // Số lượng phần tử

        ArrayList<Answers> list = new ArrayList<>(Collections.nCopies(n, null));

        TempResult_BUS tempResult_bus = new TempResult_BUS();
        Result result = new Result();
        result.setExamID("1");
        result.setUserID(1);
        result.setResultDate(LocalDateTime.now());
        result.setAnswers(list);
        Answers answer = new Answers();
        answer.setAnswerID(1);
        answer.setQuestionID(1);
        answer.setAnswerContent("A");
        answer.setAnswerCorrect(true);


    }
}