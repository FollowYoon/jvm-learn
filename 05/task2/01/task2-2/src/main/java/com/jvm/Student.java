package com.jvm;

import org.springframework.stereotype.Component;

/**
 * @author qindong
 * @date 2021/10/15 14:40
 */
@Component("student123")
public class Student {

    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
