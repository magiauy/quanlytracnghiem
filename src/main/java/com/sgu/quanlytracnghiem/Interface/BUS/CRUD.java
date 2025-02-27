package com.sgu.quanlytracnghiem.Interface.BUS;

import java.util.ArrayList;

public interface CRUD <T> {
     ArrayList<T> getAll();
     T getByID(String id);
     void add(T obj);
     void update(T obj);
     void delete(String id);

}
