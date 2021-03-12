package org.learn.mapper;

import org.learn.pojo.Student;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;

public interface StudentMapper {

    List<Student> selectAll();

    void insert(Student student);

    Student queryById(String id);

    void deleteById(String id);
}
