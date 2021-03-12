package org.learn.transaction.impl;

import org.learn.mapper.StudentMapper;
import org.learn.transaction.StudentServiceB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceBImpl implements StudentServiceB{

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void delete(String id) {
        studentMapper.deleteById(id);
    }
}
