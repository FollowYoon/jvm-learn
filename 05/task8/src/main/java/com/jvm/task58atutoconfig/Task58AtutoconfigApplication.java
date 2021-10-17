package com.jvm.task58atutoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Task58AtutoconfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(Task58AtutoconfigApplication.class, args);
    }

    @Autowired
    StudentInfo studentInfo;

    @Bean
    public void print() {
        System.out.println(studentInfo.getInfo());
    }

}
