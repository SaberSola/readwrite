package com.readwrite.Service;


import com.readwrite.conf.annotation.SlaveDataSource;
import com.readwrite.entity.AccountDO;
import com.readwrite.mapper.AccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;


    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);



    @SlaveDataSource
    public AccountDO findByUserId(String userId) {
        final AccountDO accountDO = accountMapper.findByUserId(userId);
        return accountDO;
    }

}
