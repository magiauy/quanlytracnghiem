package com.sgu.quanlytracnghiem.BUS;

import com.sgu.quanlytracnghiem.DAO.Answers_DAO;
import com.sgu.quanlytracnghiem.DTO.Answers;
import com.sgu.quanlytracnghiem.Interface.BUS.CRUD;
import com.sgu.quanlytracnghiem.Interface.DAO.GenericDAO;

import java.util.ArrayList;

public class Answers_BUS implements CRUD<Answers> {
    ArrayList<Answers> answers = new ArrayList<>();
    GenericDAO<Answers> answers_dao;

    public Answers_BUS() {
        answers_dao = new Answers_DAO();
        answers = answers_dao.getAll();
    }

    @Override
    public boolean add(Answers obj) {
        if (answers_dao.insert(obj)) {
            answers.add(obj);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Answers obj) {
        if (answers_dao.update(obj)) {
            for (Answers answer : answers) {
                if (answer.getAnswerID() == (obj.getAnswerID())) {
                    answer.setAnswerContent(obj.getAnswerContent());
                    answer.setQuestionID(obj.getQuestionID());
                    answer.setAnswerContent(obj.getAnswerContent());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        if (answers_dao.delete(id)) {
            for (Answers answer : answers) {
                if (answer.getAnswerID() == Integer.parseInt(id)) {
                    answers.remove(answer);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Answers getByID(String id) {
        for (Answers answer : answers) {
            if (answer.getAnswerID() == Integer.parseInt(id)) {
                return answer;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Answers> getAll() {
        return answers;
    }
}
