package com.sgu.quanlytracnghiem.BUS;

import com.sgu.quanlytracnghiem.DAO.Test_DAO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exam_BUSTest {

    @Test
    void generateExam() {
        Exam_BUS exam = new Exam_BUS();
        Test_DAO dao = new Test_DAO();
        com.sgu.quanlytracnghiem.DTO.Test test = dao.getById("3");
        System.out.println(test);
        exam.generateExam(test);
    }
}