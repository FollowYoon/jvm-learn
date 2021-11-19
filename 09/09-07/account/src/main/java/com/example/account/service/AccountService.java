package com.example.account.service;


import com.example.account.po.Account;
import org.dromara.hmily.annotation.Hmily;

/**
 * @author qindong
 * @date 2021/11/18 10:50
 */
public interface AccountService {

    @Hmily
    boolean pay(Account account);

}
