package com.jvm.task58atutoconfig;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * @author qindong
 * @date 2021/10/15 14:40
 */
@Configuration
@Data
public class StudentConfiguration {

    private int id;

    private String name = "stu";

}
