package com.k22.student.service.impl;

import com.k22.student.config.JDBCConnect;
import com.k22.student.model.Subject;
import com.k22.student.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    JDBCConnect jdbcConnect;

    @Override
    public void create(Subject subject) {
        try {
            PreparedStatement statement = jdbcConnect.connection().prepareStatement("insert into subjects (sj_name) values (?)");
            statement.setString(1, subject.getSjName());
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Subject subject) {
        try {
            PreparedStatement statement = jdbcConnect.connection().prepareStatement("update  subjects set sj_name = ?");
            statement.setString(1, subject.getSjName());
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Subject> getList() {
        try {
            PreparedStatement statement = jdbcConnect.connection().prepareStatement("select * from subjects");
            ArrayList<Subject> list = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Subject subject = new Subject();
                int id = resultSet.getInt("sj_id");
                String name = resultSet.getString("sj_name");
                subject.setSjId(id);
                subject.setSjName(name);
                list.add(subject);
            }
            return list;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = jdbcConnect.connection().prepareStatement("delete from  subjects where sj_id = ?");
            statement.setInt(1, id);
            statement.execute();
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }

    }
}
