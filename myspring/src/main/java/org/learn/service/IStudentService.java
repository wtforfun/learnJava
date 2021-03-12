package org.learn.service;

import org.learn.pojo.Student;

import java.util.List;

public interface IStudentService {

    List<Student> getAllStudent();

    void insert();

    Student getStuById(String id);
}
