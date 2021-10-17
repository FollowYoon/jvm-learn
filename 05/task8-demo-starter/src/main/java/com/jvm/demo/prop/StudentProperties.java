package com.jvm.demo.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author qindong
 * @date 2021/10/17 16:50
 */
@Data
@ConfigurationProperties(prefix = "student")
public class StudentProperties {

    private String name = "zhangsan";

}
