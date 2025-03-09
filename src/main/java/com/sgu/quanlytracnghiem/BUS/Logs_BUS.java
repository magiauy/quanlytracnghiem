package com.sgu.quanlytracnghiem.BUS;

import com.sgu.quanlytracnghiem.DAO.Logs_DAO;
import com.sgu.quanlytracnghiem.Interface.BUS.CRUD;
import com.sgu.quanlytracnghiem.DTO.Logs;
import com.sgu.quanlytracnghiem.Interface.DAO.GenericDAO;

import java.util.ArrayList;

public class Logs_BUS implements CRUD<Logs> {
    ArrayList<Logs> logs = new ArrayList<>();
    GenericDAO<Logs> logs_dao;

    public Logs_BUS() {
        logs_dao = new Logs_DAO();
        logs = logs_dao.getAll();
    }


    @Override
    public boolean add(Logs obj) {
        if (logs_dao.insert(obj)) {
            logs.add(obj);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Logs obj) {
        if (logs_dao.update(obj)) {
            for (Logs log : logs) {
                if (log.getLogsID() == (obj.getLogsID())) {
                    log.setLogContent(obj.getLogContent());
                    log.setLogTime(obj.getLogTime());
                    log.setLogUserID(obj.getLogUserID());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        if (logs_dao.delete(id)) {
            for (Logs log : logs) {
                if (log.getLogsID() == Integer.parseInt(id)) {
                    logs.remove(log);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Logs getByID(String id) {
        for (Logs log : logs) {
            if (log.getLogsID() == Integer.parseInt(id)) {
                return log;
            }
        }
        return null;
    }
    @Override
    public ArrayList<Logs> getAll() {
        return logs;
    }
}
