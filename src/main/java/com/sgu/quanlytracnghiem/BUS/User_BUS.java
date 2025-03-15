package com.sgu.quanlytracnghiem.BUS;

import com.sgu.quanlytracnghiem.DAO.User_DAO;
import com.sgu.quanlytracnghiem.DTO.User;
import com.sgu.quanlytracnghiem.Interface.BUS.CRUD;
import com.sgu.quanlytracnghiem.Interface.BUS.IExcelImport;
import com.sgu.quanlytracnghiem.Interface.DAO.GenericDAO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

@Slf4j
@Service
public class User_BUS implements CRUD<User> , IExcelImport {
    ArrayList<User> users ;
    GenericDAO<User> user_dao = new User_DAO();
    public User_BUS() {
        users = user_dao.getAll();
    }

    public ArrayList<User> getAll() {
        return users;
    }

    public User getByID(String id) {
        for (User user : users) {
            if (user.getId() == Integer.parseInt(id)) {
                return user;
            }
        }
        return null;
    }

    public boolean add(User obj) {
        if (user_dao.insert(obj)) {
            users.add(obj);
            return true;
        }
        return false;
    }

    public boolean update(User obj) {
        if (user_dao.update(obj)) {
            for (User user : users) {
                if (user.getId() == obj.getId()) {
                    user.setUsername(obj.getUsername());
                    user.setPassword(obj.getPassword());
                    user.setEmail(obj.getEmail());
                    user.setFullName(obj.getFullName());
                    user.setAdmin(obj.isAdmin());
                    return true;
                }
            }
        }
        return false;
    }

    public boolean delete(String id) {
        if (user_dao.delete(id)) {
            for (User user : users) {
                if (user.getId() == Integer.parseInt(id)) {
                    users.remove(user);
                    return true;
                }
            }
        }
        return false;
    }

    @Async
    public void importExcel(File file) {
        try(InputStream fis = new FileInputStream(file)) {
            Workbook workbook = new XSSFWorkbook(fis);
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);
                for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                    Row row = sheet.getRow(j);
                    User user = new User();
                    user.setFullName(row.getCell(0).getStringCellValue());
                    user.setEmail(row.getCell(1).getStringCellValue());
                    user.setUsername(row.getCell(2).getStringCellValue());
                    user.setPassword(row.getCell(3).getStringCellValue());
                    user.setAdmin(false);
                    add(user);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }



    }
}
