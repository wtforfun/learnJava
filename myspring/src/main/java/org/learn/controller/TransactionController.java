package org.learn.controller;

import org.learn.pojo.Student;
import org.learn.transaction.StudentServiceA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private StudentServiceA studentServiceA;

    @RequestMapping("/test1")
    @ResponseBody
    public Object test1(){
        Student student = new Student("1","2","3","4");
        studentServiceA.add(student);
        return "success";
    }
}
