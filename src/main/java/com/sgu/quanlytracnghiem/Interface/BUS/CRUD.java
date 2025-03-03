package com.sgu.quanlytracnghiem.Interface.BUS;

import java.util.ArrayList;

public interface CRUD <T> {
     ArrayList<T> getAll();
     T getByID(String id);
     boolean add(T obj);
     boolean update(T obj);
     boolean delete(String id);

}
