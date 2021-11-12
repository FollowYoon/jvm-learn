package com.jvm.db.dao;

import com.jvm.db.po.OrderInfoBase;
import org.apache.ibatis.annotations.Param;

/**
 * @author qindong
 * @date 2021/11/4 10:09
 */
public interface OrderDao {

    int save(OrderInfoBase orderInfoBase);

    int update(OrderInfoBase orderInfoBase);

    int delete(@Param("id") Long id);

    OrderInfoBase query(@Param("id") Long id);

}
