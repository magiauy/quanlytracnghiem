package com.sgu.quanlytracnghiem.BUS;

import com.sgu.quanlytracnghiem.DAO.Answers_DAO;
import com.sgu.quanlytracnghiem.DTO.Answers;
import com.sgu.quanlytracnghiem.Interface.BUS.CRUD;
import com.sgu.quanlytracnghiem.Interface.BUS.IAnswers;
import com.sgu.quanlytracnghiem.Interface.BUS.IdGenerate;
import com.sgu.quanlytracnghiem.Interface.DAO.GenericDAO;
import com.sgu.quanlytracnghiem.Interface.DAO.IAnswers_DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers_BUS implements CRUD<Answers>, IAnswers  {
    ArrayList<Answers> answers;
    GenericDAO<Answers> answers_dao;
    IAnswers_DAO answers_dao_impl;

    public Answers_BUS() {
        answers_dao = new Answers_DAO();
        answers = answers_dao.getAll();
        answers_dao_impl = new Answers_DAO();
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

    @Override
    public ArrayList<Answers> getAnswersByQuestionID(int questionID) {
        ArrayList<Answers> answersByQuestionID = new ArrayList<>();
        for (Answers answer : answers) {
            if (answer.getQuestionID() == questionID) {
                answersByQuestionID.add(answer);
            }
        }
        return answersByQuestionID;
    }

    @Override
    public void saveAnswersToDB(ArrayList<Answers> answers, int questionID) {
        // Danh sách ID mới sẽ chứa những answerID hợp lệ
        List<Integer> answerIDs = new ArrayList<>();

        for (Answers answer : answers) {
            if (answer.getAnswerID() == -1 ) {
                // Nếu chưa có ID => Thêm mới vào DB và lấy lại ID
                int newID = answers_dao_impl.insertInt(answer);
                answer.setAnswerID(newID); // Cập nhật lại ID mới vào danh sách
            } else {
                // Nếu đã có ID => Cập nhật vào DB
                answers_dao.update(answer);
            }
            answerIDs.add(answer.getAnswerID()); // Đảm bảo danh sách answerIDs đầy đủ
        }

        // Xoá các đáp án không còn trong danh sách (sau khi đã cập nhật đầy đủ ID)
        answers_dao_impl.deleteMissingAnswers(questionID, answerIDs);
    }
    

}
