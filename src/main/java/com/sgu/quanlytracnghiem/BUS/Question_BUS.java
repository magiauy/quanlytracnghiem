package com.sgu.quanlytracnghiem.BUS;

import com.sgu.quanlytracnghiem.DTO.Question;
import com.sgu.quanlytracnghiem.Interface.BUS.CRUD;
import com.sgu.quanlytracnghiem.DAO.Question_DAO;
import com.sgu.quanlytracnghiem.Interface.BUS.IQuestion;
import com.sgu.quanlytracnghiem.Interface.BUS.IdGenerate;
import com.sgu.quanlytracnghiem.Interface.DAO.GenericDAO;

import java.util.ArrayList;

public class Question_BUS implements CRUD<Question> , IdGenerate , IQuestion {

    ArrayList<Question> questions ;
    GenericDAO<Question> question_dao = new Question_DAO();
    IdGenerate idGenerate = new Question_DAO();

    public Question_BUS() {
        questions = question_dao.getAll();
    }

    @Override
    public boolean add(Question obj) {
        if (question_dao.insert(obj)) {
            questions.add(obj);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Question obj) {
        if (question_dao.update(obj)) {
            for (Question question : questions) {
                if (question.getQuestionID() == obj.getQuestionID()) {
                    question.setQuestionContent(obj.getQuestionContent());
                    question.setQuestionPicture(obj.getQuestionPicture());
                    question.setTopicID(obj.getTopicID());
                    question.setQuestionLevel(obj.getQuestionLevel());
                    question.setQuestionStatus(obj.isQuestionStatus());

                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean delete(String id) {

        if (question_dao.delete(id)) {
            for (Question question : questions) {
                if (question.getQuestionID() == Integer.parseInt(id)) {
                    questions.remove(question);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Question getByID(String id) {

        for (Question question : questions) {
            if (question.getQuestionID() == Integer.parseInt(id)) {
                return question;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Question> getAll() {
        return questions;
    }

    @Override
    public int generateId() {
        return idGenerate.generateId();
    }

    @Override
    public ArrayList<Question> getQuestionByTopic(int topicID) {
        ArrayList<Question> questionByTopic = new ArrayList<>();
        for (Question question : questions) {
            if (question.getTopicID() == topicID) {
                questionByTopic.add(question);
            }
        }
        return questionByTopic;
    }
}
