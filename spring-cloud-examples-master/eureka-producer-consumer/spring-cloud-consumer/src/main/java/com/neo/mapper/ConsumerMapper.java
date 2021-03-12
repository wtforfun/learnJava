package com.neo.mapper;

import com.neo.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ConsumerMapper {

    Account selectByUserId(@Param("userId") String userId);

    int updateById(Account account);

    void deleteById(Account account);
}
