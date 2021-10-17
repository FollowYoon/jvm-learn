package com.jvm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 通过xml定义
 *
 * @author qindong
 * @date 2021/10/15 14:47
 */
public class Spring {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        Student student = (Student) applicationContext.getBean("student123");
        System.out.println(student.toString());

    }

}
