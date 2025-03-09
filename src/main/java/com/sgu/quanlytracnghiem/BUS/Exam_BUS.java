package com.sgu.quanlytracnghiem.BUS;

import com.sgu.quanlytracnghiem.DAO.Exam_DAO;
import com.sgu.quanlytracnghiem.DTO.Exam;
import com.sgu.quanlytracnghiem.Interface.BUS.CRUD;
import com.sgu.quanlytracnghiem.Interface.DAO.GenericDAO;

import java.util.ArrayList;

public class Exam_BUS implements CRUD<Exam> {
    ArrayList<Exam> exams = new ArrayList<>();
    GenericDAO<Exam> exam_dao;

    public Exam_BUS() {
        exam_dao = new Exam_DAO();
        exams = exam_dao.getAll();
    }


    @Override
    public boolean add(Exam obj) {
        if (exam_dao.insert(obj)) {
            exams.add(obj);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Exam obj) {
        if (exam_dao.update(obj)) {
            for (Exam exam : exams) {
                if (exam.getExamID() == (obj.getExamID())) {
                    exam.setExamOrder(obj.getExamOrder());
                    exam.setQuestions(obj.getQuestions());
                    exam.setTestID(obj.getTestID());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        if (exam_dao.delete(id)) {
            for (Exam exam : exams) {
                if (exam.getExamID() == Integer.parseInt(id)) {
                    exams.remove(exam);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Exam getByID(String id) {
        for (Exam exam : exams) {
            if (exam.getExamID() == Integer.parseInt(id)) {
                return exam;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Exam> getAll() {
        return exams;
    }
}
