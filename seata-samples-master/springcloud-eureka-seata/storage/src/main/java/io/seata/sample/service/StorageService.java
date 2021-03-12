package io.seata.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author jimin.jm@alibaba-inc.com
 * @date 2019/06/14
 */
@Service
public class StorageService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void deduct(String commodityCode, int count) {
        //更新库存
        jdbcTemplate.update("update storage_tbl set count = count - ? where commodity_code = ?",
            new Object[] {count, commodityCode});

//        尝试删除操作能否回滚
        jdbcTemplate.update("delete from storage_tbl WHERE commodity_code = 'C200000'");
    }
}
