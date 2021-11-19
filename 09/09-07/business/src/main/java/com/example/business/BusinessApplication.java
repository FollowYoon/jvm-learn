package com.example.business;

import com.example.business.service.BusinessService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@ImportResource({"classpath:spring-dubbo.xml"})
@SpringBootApplication
public class BusinessApplication implements ApplicationRunner {

    @Autowired
    BusinessService businessService;

    public static void main(String[] args) {
        SpringApplication.run(BusinessApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        businessService.doBusiness();
    }
}
