package com.sgu.quanlytracnghiem.BUS;

import com.sgu.quanlytracnghiem.DAO.Topic_DAO;
import com.sgu.quanlytracnghiem.DTO.Topic;
import com.sgu.quanlytracnghiem.Interface.BUS.CRUD;
import com.sgu.quanlytracnghiem.Interface.DAO.GenericDAO;
import com.sgu.quanlytracnghiem.Interface.DAO.ITopic;

import java.util.ArrayList;

public class Topic_BUS implements CRUD<Topic> , ITopic {
    ArrayList<Topic> topics ;
    GenericDAO<Topic> topic_dao;
    ITopic itopic ;

    public Topic_BUS() {
        topic_dao = new Topic_DAO();
        topics = topic_dao.getAll();
    }

    @Override
    public boolean add(Topic obj) {
        if (topic_dao.insert(obj)) {
            topics.add(obj);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Topic obj) {
        if (topic_dao.update(obj)) {
            for (Topic topic : topics) {
                if (topic.getTopicID() == (obj.getTopicID())) {
                    topic.setTopicTitle(obj.getTopicTitle());
                    topic.setTopicParentID(obj.getTopicParentID());
                    topic.setTopicStatus(obj.getTopicStatus());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        if (topic_dao.delete(id)) {
            for (Topic topic : topics) {
                if (topic.getTopicID() == Integer.parseInt(id)) {
                    topics.remove(topic);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Topic getByID(String id) {
        for (Topic topic : topics) {
            if (topic.getTopicID() == Integer.parseInt(id)) {
                return topic;
            }
        }
        return null;
    }

    public ArrayList<Topic> getAll() {
        return topics;
    }

    @Override
    public ArrayList<Topic> getTopicQuestionCounts(){
        itopic = new Topic_DAO();
        return itopic.getTopicQuestionCounts();
    }
}
