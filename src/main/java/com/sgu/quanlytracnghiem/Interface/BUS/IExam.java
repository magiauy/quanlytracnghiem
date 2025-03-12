package com.sgu.quanlytracnghiem.Interface.BUS;

import com.sgu.quanlytracnghiem.DTO.Exam;
import com.sgu.quanlytracnghiem.DTO.Question;
import com.sgu.quanlytracnghiem.DTO.Test;

import java.util.ArrayList;

public interface IExam {
    void generateExam(Test test);
    ArrayList<Exam> getExamsByTest(String testCode);

}
