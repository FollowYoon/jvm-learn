package io.kimmking.rpcfx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * RPC provider 初始化注解
 * @author qindong
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Provider {

    /**
     * 对应 API 接口名称
     * @return API service
     */
    String serviceName();

    /**
     * 分组
     * @return group
     */
    String group() default "default";

    /**
     * version
     * @return version
     */
    String version() default "default";
}
