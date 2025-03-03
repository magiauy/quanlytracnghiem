package com.sgu.quanlytracnghiem.BUS;

import com.sgu.quanlytracnghiem.DAO.Test_DAO;
import com.sgu.quanlytracnghiem.DTO.Test;
import com.sgu.quanlytracnghiem.Interface.BUS.CRUD;
import com.sgu.quanlytracnghiem.Interface.DAO.GenericDAO;

import java.util.ArrayList;

public class Test_BUS implements CRUD<Test> {
    ArrayList<Test> tests ;
    GenericDAO<Test> test_dao = new Test_DAO();

    public Test_BUS() {
        tests = test_dao.getAll();
    }


    @Override
    public boolean add(Test obj) {
        if (test_dao.insert(obj)){
            tests.add(obj);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Test obj) {
        if (test_dao.update(obj)){
            for (Test test : tests){
                if (test.getTestID()==(obj.getTestID())){
                    test.setTestTitle(obj.getTestTitle());
                    test.setTestTime(obj.getTestTime());
                    test.setTestDate(obj.getTestDate());
                    test.setTestStatus(obj.getTestStatus());
                    test.setTestLimit(obj.getTestLimit());
                    test.setNum_easy(obj.getNum_easy());
                    test.setNum_medium(obj.getNum_medium());
                    test.setNum_diff(obj.getNum_diff());
                    test.setTopics(obj.getTopics());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        if (test_dao.delete(id)){
            for (Test test : tests){
                if (test.getTestID()==Integer.parseInt(id)){
                    tests.remove(test);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Test getByID(String id) {
        for (Test test : tests){
            if (test.getTestID()==Integer.parseInt(id)){
                return test;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Test> getAll() {
        return tests;
    }
}
