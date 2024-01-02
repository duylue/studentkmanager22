package com.k22.student.service;

import com.k22.student.model.Subject;

import java.util.ArrayList;

public interface SubjectService {
    public void create (Subject subject);

    void update (Subject subject);

    ArrayList<Subject> getList();
    void delete(int id);
}
