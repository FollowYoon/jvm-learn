package com.example.account.dao;

import com.example.account.po.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author qindong
 * @date 2021/11/18 11:36
 */
public interface AccountDao {

    int pay(Account account);

}
