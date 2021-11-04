package com.jvm.db.dao;

import com.jvm.db.po.T1;
import org.apache.ibatis.annotations.Param;

/**
 * @author qindong
 * @date 2021/11/4 10:09
 */
public interface T1Dao {

    int save(T1 t1);

    T1 query(@Param("id") Integer id);

}
