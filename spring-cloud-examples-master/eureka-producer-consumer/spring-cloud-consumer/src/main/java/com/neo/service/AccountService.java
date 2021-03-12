package com.neo.service;

import com.neo.entity.Account;
import com.neo.mapper.ConsumerMapper;
import com.neo.remote.StroageService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {


    @Autowired
    ConsumerMapper consumerMapper;

    @Autowired
    StroageService stroageService;

    @GlobalTransactional
    public void reduce(String name){
        //账户扣钱
        String user_id = "U100000";
        BigDecimal money = new BigDecimal(2000);
        Account account = consumerMapper.selectByUserId(user_id);
        account.setUserId(user_id);
        account.setMoney(account.getMoney().subtract(money));
        consumerMapper.updateById(account);

        //库存减数量(调用库存远程服务)
        String msg = stroageService.deduct(name);
        System.out.println("调用库存远程服务 返回值:" + msg);

        //余额校验
        BigDecimal zero = new BigDecimal (0);
        Account acc = selectByUserId(user_id);
        if(acc.getMoney().compareTo(zero) == -1){
            throw new RuntimeException("账户余额不足");
        }
    }

    public Account selectByUserId(String user_id){
       return  consumerMapper.selectByUserId(user_id);
    }
}
