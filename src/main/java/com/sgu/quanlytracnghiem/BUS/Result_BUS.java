package com.sgu.quanlytracnghiem.BUS;

import com.sgu.quanlytracnghiem.DTO.Answers;
import com.sgu.quanlytracnghiem.DTO.Result;
import com.sgu.quanlytracnghiem.Interface.BUS.CRUD;
import com.sgu.quanlytracnghiem.Interface.DAO.GenericDAO;
import com.sgu.quanlytracnghiem.DAO.Result_DAO;

import java.util.ArrayList;
import java.util.List;

public class Result_BUS implements CRUD<Result> {
    ArrayList<Result> results = new ArrayList<>();
    static GenericDAO<Result> result_dao;

    public Result_BUS() {
        result_dao = new Result_DAO();
        results = result_dao.getAll();
    }

    public static String getUsernameById(String s) {
        return ((Result_DAO) result_dao).getUsernameById(s);
    }


    @Override
    public boolean add(Result obj) {
        if (result_dao.insert(obj)) {
            results.add(obj);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Result obj) {
        if (result_dao.update(obj)) {
            for (Result result : results) {
                if (result.getResultID() == (obj.getResultID())) {
                    result.setExamID(obj.getExamID());
                    result.setUserID(obj.getUserID());
                    result.setResultScore(obj.getResultScore());
                    result.setResultDate(obj.getResultDate());
                    result.setAnswers(obj.getAnswers());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        if (result_dao.delete(id)) {
            for (Result result : results) {
                if (result.getResultID() == Integer.parseInt(id)) {
                    results.remove(result);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Result getByID(String id) {
        for (Result result : results) {
            if (result.getResultID() == Integer.parseInt(id)) {
                return result;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Result> getAll() {
        return results;
    }

    public static String getQuestionContentById(String qID) {
        return ((Result_DAO) result_dao).getQuestionContentById(qID);
    }
    public static List<Answers> getAnswersByQuestionId(String questionID) {
        return ((Result_DAO) result_dao).getAnswersByQuestionId(questionID);
    }

}
