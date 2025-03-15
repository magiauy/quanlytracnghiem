package com.sgu.quanlytracnghiem.DAO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Result_DAOTest {

    @Test
    void getActiveResult() {
        Result_DAO dao = new Result_DAO();
        assertNotNull(dao.getActiveResult(3, "TEST2"));
    }
}