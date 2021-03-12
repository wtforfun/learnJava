package com.neo.controller;

import com.neo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {


    @Autowired
    AccountService accountService;

    @RequestMapping("/reduce/{name}")

    public String index(@PathVariable("name") String name) {

        try {
            //下单
            accountService.reduce(name);
        }catch (Exception e){
            return "下单失败 库存或余额不足！";
        }

        return "下单成功，事务已提交！";
    }

}