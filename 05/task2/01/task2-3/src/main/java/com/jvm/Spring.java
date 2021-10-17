package com.jvm;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 通过 @ComponentScan 扫描
 *
 * @author qindong
 * @date 2021/10/15 14:47
 */
@ComponentScan()
public class Spring {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Student.class);
        Student student = (Student) context.getBean("student123");
        System.out.println(student.toString());
    }

}
