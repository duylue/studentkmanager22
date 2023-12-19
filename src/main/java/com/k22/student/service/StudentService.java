package com.k22.student.service;

import com.k22.student.config.JDBCCon;
import com.k22.student.model.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StudentService {
    public static ArrayList<Student> getList(){
        try {
            PreparedStatement statement = JDBCCon.connection().prepareStatement("SELECT * from students");
            ArrayList<Student> list = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id  = resultSet.getInt("sid");
                String name = resultSet.getString("sname");
                int cid = resultSet.getInt("cid");
                Student student =  new Student();
                student.setCid(cid);
                student.setSid(id);
                student.setSname(name);
                list.add(student);
            }
            return list;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
