package com.jvm.db.service;

import com.jvm.db.dao.T1Dao;
import com.jvm.db.datasource.DS;
import com.jvm.db.datasource.DSNames;
import com.jvm.db.po.T1;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qindong
 * @date 2021/11/4 10:36
 */
@Service
public class T1Service {

    @Resource
    T1Dao t1Dao;

    /**
     *  默认获取master数据源，无需加注解
     */
    public int save(Integer id){
        return t1Dao.save(new T1(id));
    }

    /**
     * 使用注解配置slave数据源
     * @param id
     * @return
     */
    @DS(name = DSNames.SLAVE)
    public T1 find(Integer id){
        return t1Dao.query(id);
    }

}
