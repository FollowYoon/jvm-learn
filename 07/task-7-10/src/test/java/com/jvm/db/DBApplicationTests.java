package com.jvm.db;

import com.jvm.db.po.T1;
import com.jvm.db.service.T1Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DBApplicationTests {

    @Autowired
    T1Service t1Service;

    @Test
    public void test(){
        int i = t1Service.save(6);
        System.out.println("save() result:" + i);

        T1 t1 = t1Service.find(6);
        System.out.println("find() result:" + t1.toString());
    }

}
