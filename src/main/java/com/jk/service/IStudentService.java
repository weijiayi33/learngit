package com.jk.service;

import com.jk.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> querymyb();

    void deletestu(String id);

    String savestu(Student student);

    List<Student> querymongo();

    void updatestu(Student student);
}
