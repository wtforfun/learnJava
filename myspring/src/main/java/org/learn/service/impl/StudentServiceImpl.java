package org.learn.service.impl;

import org.learn.mapper.StudentMapper;
import org.learn.pojo.Student;
import org.learn.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Transactional
    @Override
    public List<Student> getAllStudent() {
        return studentMapper.selectAll();
    }

    @Transactional
    @Override
    public void insert() {
        Student student = new Student("4","3","2","1");
        studentMapper.insert(student);
        if(1>0)throw new RuntimeException("error");
        System.out.println("success");

    }

    @Override
    public Student getStuById(String id) {
        return studentMapper.queryById(id);
    }


}
