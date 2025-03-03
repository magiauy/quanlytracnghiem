package com.sgu.quanlytracnghiem.DAO;

import com.sgu.quanlytracnghiem.DTO.Test;
import com.sgu.quanlytracnghiem.Interface.DAO.GenericDAO;

import java.sql.Connection;
import java.util.ArrayList;

public class Test_DAO implements GenericDAO<Test> {
    Connection connection = Connect.getInstance().getConnection();

    @Override
    public boolean insert(Test test) {
        return false;
    }

    @Override
    public boolean update(Test test) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Test getById(String id) {
        return null;
    }

    @Override
    public ArrayList<Test> getAll() {
        return null;
    }


}
