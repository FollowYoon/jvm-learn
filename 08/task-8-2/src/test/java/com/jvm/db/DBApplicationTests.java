package com.jvm.db;

import com.jvm.db.dao.OrderDao;
import com.jvm.db.po.OrderInfoBase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DBApplicationTests {

    @Resource
    OrderDao orderDao;

    @Test
    void addTest() {
        OrderInfoBase orderInfoBase = new OrderInfoBase();
        orderInfoBase.setUserId(1);
        orderInfoBase.setOrderStatus(1);
        orderDao.save(orderInfoBase);

        orderInfoBase.setUserId(2);
        orderInfoBase.setOrderStatus(1);
        orderDao.save(orderInfoBase);
    }

    @Test
    void query() {
        OrderInfoBase orderInfoBase = orderDao.query(665957117219811329L);
        System.out.println(orderInfoBase.toString());
    }

    @Test
    void update() {
        OrderInfoBase orderInfoBase = new OrderInfoBase();
        orderInfoBase.setId(665957117219811329L);
        orderInfoBase.setOrderStatus(2);
        orderDao.update(orderInfoBase);
    }

    @Test
    void delete() {
        orderDao.delete(665957117219811329L);
    }


}
