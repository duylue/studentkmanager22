package com.k22.student.service;

import com.k22.student.model.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class StudentService2 implements StudentServiceIf {

    @Override
    public void create(Student student) {
        System.out.println(123333);
    }

    @Override
    public ArrayList<Student> getList() {
        return null;
    }

    @Override
    public void update(Student student) {

    }

    @Override
    public void delete(int id) {

    }
}
