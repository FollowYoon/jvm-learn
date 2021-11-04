package com.jvm.db.datasource;

import java.lang.annotation.*;

/**
 * 指定数据源注解
 * @author qindong
 * @date 2021/11/4 10:21
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DS {

    String name() default "";

}
