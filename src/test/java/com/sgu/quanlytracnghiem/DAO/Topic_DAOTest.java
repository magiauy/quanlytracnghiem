package com.sgu.quanlytracnghiem.DAO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Topic_DAOTest {

    @Test
    void getTopicQuestionCounts() {
        Topic_DAO dao = new Topic_DAO();
        System.out.println(dao.getTopicQuestionCounts());
    }
}