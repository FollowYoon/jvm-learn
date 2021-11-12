package com.jvm.db;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author qindong
 * @date 2021/11/12 15:19
 */
@MapperScan({"com.jvm.db.dao"})
@SpringBootApplication
public class DBApplication {

    public static void main(String[] args) {
        SpringApplication.run(DBApplication.class, args);
    }

}
