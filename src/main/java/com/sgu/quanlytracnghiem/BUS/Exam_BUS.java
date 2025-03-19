package com.sgu.quanlytracnghiem.BUS;

import com.sgu.quanlytracnghiem.DAO.Exam_DAO;
import com.sgu.quanlytracnghiem.DTO.*;
import com.sgu.quanlytracnghiem.Interface.BUS.CRUD;
import com.sgu.quanlytracnghiem.Interface.BUS.IExam;
import com.sgu.quanlytracnghiem.Interface.BUS.IQuestion;
import com.sgu.quanlytracnghiem.Interface.DAO.GenericDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Exam_BUS implements CRUD<Exam> , IExam {
    ArrayList<Exam> exams = new ArrayList<>();
    GenericDAO<Exam> exam_dao;
    IQuestion question;
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
                if (Objects.equals(exam.getExamID(), obj.getExamID())) {
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
                if (exam.getExamID().equals(id)) {
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
            if (exam.getExamID().equals(id)) {
                return exam;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Exam> getAll() {
        return exams;
    }

    @Override
    public void generateExam(Test test) {
        question = new Question_BUS();
        int n=10;
        for (int i=0 ; i < n; i++) {
            Exam exam = new Exam();
            exam.setTestID(test.getTestCode());
            //Đề A-Z
            exam.setExamOrder((char)('A' + i )+ "");
            exam.setExamID(exam.getTestID()+"_"+exam.getExamOrder());
            //Random câu hỏi bằng tổng số câu hỏi lấy ra từ Test
            exam.setQuestions(getRandomQuestion(test.getTopics()));
            System.out.println(exam);
            add(exam);
        }
    }
    public ArrayList<Question> getRandomQuestion(ArrayList<Topic> topics){
        ArrayList<Question> result = new ArrayList<>();
        ArrayList<Question> easyQuestions = new ArrayList<>();
        ArrayList<Question> mediumQuestions = new ArrayList<>();
        ArrayList<Question> diffQuestions = new ArrayList<>();


        for (Topic topic : topics) {
            //Lấy ra số lượng câu hỏi theo độ khó
            int num_easy = topic.getNum_easy();
            int num_medium = topic.getNum_medium();
            int num_diff = topic.getNum_diff();
            //Lấy ra danh sách câu hỏi theo độ khó
            ArrayList<Question> questions = question.getQuestionByTopic(topic.getTopicID());
            //Random câu hỏi
            for (int i = 0; i < num_easy; i++) {
                // Filter questions by topic and difficulty level (easy)
                List<Question> easyQuestionsByTopic = questions.stream()
                        .filter(q -> q.getTopicID() == topic.getTopicID() && q.getQuestionLevel() == QuestionLevel.EASY)
                        .toList();

                // Randomly select a question from the filtered list
                int index = (int) (Math.random() * easyQuestionsByTopic.size());
                easyQuestions.add(easyQuestionsByTopic.get(index));
                questions.remove(easyQuestionsByTopic.get(index));
            }
            for (int i = 0; i < num_medium; i++) {
                List<Question> mediumQuestionsByTopic = questions.stream()
                        .filter(q -> q.getTopicID() == topic.getTopicID() && q.getQuestionLevel() == QuestionLevel.MEDIUM)
                        .toList();

                int index = (int) (Math.random() * mediumQuestionsByTopic.size());
                mediumQuestions.add(mediumQuestionsByTopic.get(index));
                questions.remove(mediumQuestionsByTopic.get(index));
            }
            for (int i = 0; i < num_diff; i++) {
                List<Question> diffQuestionsByTopic = questions.stream()
                        .filter(q -> q.getTopicID() == topic.getTopicID() && q.getQuestionLevel() == QuestionLevel.DIFFICULT)
                        .toList();

                int index = (int) (Math.random() * diffQuestionsByTopic.size());
                diffQuestions.add(diffQuestionsByTopic.get(index));
                questions.remove(diffQuestionsByTopic.get(index));
            }

        }
        result.addAll(easyQuestions);
        result.addAll(mediumQuestions);
        result.addAll(diffQuestions);

        return result;
    }

    @Override
    public ArrayList<Exam> getExamsByTest(String testID) {
        ArrayList<Exam> examByTest = new ArrayList<>();
        for (Exam exam : exams) {
            if (exam.getTestID().equals(testID)) {
                examByTest.add(exam);
            }
        }
        return examByTest;
    }
    @Override
    public Exam getRandomExamByTest(String testID) {
        //Random
        ArrayList<Exam> exams = getExamsByTest(testID);
        int index = (int) (Math.random() * exams.size());
        return exams.get(index);

    }

    @Override
    public String getTestIDByExamID(String examID) {
        for (Exam exam : exams) {
            if (exam.getExamID().equals(examID)) {
                return exam.getTestID();
            }
        }
        return null;
    }



}
