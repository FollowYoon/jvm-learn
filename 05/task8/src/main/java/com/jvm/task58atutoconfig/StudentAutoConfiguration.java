package com.jvm.task58atutoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author qindong
 * @date 2021/10/17 16:46
 */
@Configuration
@Import(StudentConfiguration.class)
@EnableConfigurationProperties(StudentProperties.class)
public class StudentAutoConfiguration {

    @Autowired
    StudentProperties properties;

    @Autowired
    StudentConfiguration studentConfiguration;

    @Bean
    public StudentInfo create() {
        return new StudentInfo(studentConfiguration.getName() + "-" + properties.getName());
    }

}
