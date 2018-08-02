package com.readwrite.mapper;

import com.readwrite.entity.AccountDO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface AccountMapper {

    @Select("select * from account where user_id =#{userId}")
    AccountDO findByUserId(String userId);
}
