package com.example.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@MapperScan({"com.example.account.dao"})
@SpringBootApplication
@ImportResource({"classpath:spring-dubbo.xml"})
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(AccountApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.run(args);
    }

}
