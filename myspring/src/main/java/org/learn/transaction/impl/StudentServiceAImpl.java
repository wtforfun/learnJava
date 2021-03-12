package org.learn.transaction.impl;

import org.learn.mapper.StudentMapper;
import org.learn.pojo.Student;
import org.learn.transaction.StudentServiceA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceAImpl implements StudentServiceA{

    @Autowired
    private StudentMapper studentMapper;

    @Transactional
    @Override
    public void add(Student student) {
        studentMapper.insert(student);
        delete(student.getSno());
    }

    public void delete(String id){
//        studentMapper.deleteById(id);
        if(1>0)throw new RuntimeException("111");
    }
}
