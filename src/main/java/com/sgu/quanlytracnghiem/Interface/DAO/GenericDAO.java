package com.sgu.quanlytracnghiem.Interface.DAO;

public interface GenericDAO <T> {
    void insert(T obj);
    void update(T obj);
    void delete(String id);
    T getById(String id);

}
