package com.example.business.service.impl;

import com.example.account.po.Account;
import com.example.account.service.AccountService;
import com.example.business.service.BusinessService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qindong
 * @date 2021/11/18 14:08
 */
@Service("businessService")
@Slf4j
public class BusinessServiceImpl implements BusinessService {

    @Resource
    AccountService accountService;

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public void doBusiness() {
        Account accountA = new Account();
        accountA.setId(1);
        accountA.setUsBalance(-1);
        accountA.setCnyBalance(7);
        boolean resA = accountService.pay(accountA);
        log.info("resA:" + resA);


        Account accountB = new Account();
        accountB.setId(2);
        accountB.setUsBalance(1);
        accountB.setCnyBalance(-7);
        boolean resB = accountService.pay(accountB);
        log.info("resA:" + resB);
    }

    public void confirm() {
        log.info("business confirm");
    }

    public void cancel() {
        log.info("business cancel");
    }

}
