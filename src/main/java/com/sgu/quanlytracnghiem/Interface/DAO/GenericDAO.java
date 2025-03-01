package com.sgu.quanlytracnghiem.Interface.DAO;

import java.util.ArrayList;

public interface GenericDAO <T> {
    boolean insert(T obj);
    boolean update(T obj);
    boolean delete(String id);
    T getById(String id);

    ArrayList<T> getAll();

}
