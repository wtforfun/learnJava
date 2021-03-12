package com.neo.controller;

import com.neo.entity.Storage;
import com.neo.mapper.StorageMapper;
import io.seata.rm.datasource.StatementProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {


    @Autowired
    StorageMapper storageMapper;
	
    @RequestMapping("/deduct")
    public String deduct(@RequestParam String name) {

        //减库存
            String commodityCode = "C100000";
            Storage storage = storageMapper.findByCommodityCode(commodityCode);
            //库存 减 10
            int count = storage.getCount() - 10;
            storage.setCount(count);


            storageMapper.updateById(storage);

            //删除 测试是否能回滚
//            storageMapper.deleteById("C200000");

            if(count < 0){
                throw new RuntimeException("库存不足");
            }

        return name + " 库存扣除成功";
    }
}