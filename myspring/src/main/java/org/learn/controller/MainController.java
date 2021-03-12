package org.learn.controller;

import org.learn.pojo.Student;
import org.learn.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/test")
public class MainController {

    @Autowired
    private IStudentService studentService;

    @RequestMapping("test1")
    @ResponseBody
    public Object test(String name) {
        return "hello" + name;
    }

    @RequestMapping("allStudent")
    @ResponseBody
    public Object allStudent() {
        List<Student> students =  studentService.getAllStudent();
        for(Student student : students){
            System.out.println(student);
        }
        return "success1";
    }

    @RequestMapping("insert")
    @ResponseBody
    public Object insert() {
        studentService.insert();
        return "success1";
    }

    @RequestMapping("id")
    @ResponseBody
    public Object getDataById(String id) {
        Student student = studentService.getStuById(id);
        return student.toString();
    }

}
