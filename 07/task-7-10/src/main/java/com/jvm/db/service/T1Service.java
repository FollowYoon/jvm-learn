package com.jvm.db.service;

import com.jvm.db.dao.T1Dao;
import com.jvm.db.po.T1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qindong
 * @date 2021/11/4 10:36
 */
@Service
public class T1Service {

    @Autowired
    T1Dao t1Dao;

    public int save(Integer id){
        return t1Dao.save(new T1(id));
    }

    public T1 find(Integer id){
        return t1Dao.query(id);
    }

}
