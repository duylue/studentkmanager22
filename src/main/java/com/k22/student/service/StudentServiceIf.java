package com.k22.student.service;

import com.k22.student.model.Student;

import java.util.ArrayList;

public interface StudentServiceIf {
    void create(Student student);
    ArrayList<Student> getList();

    void update(Student student);
    void delete(int id);
}
