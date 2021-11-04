package com.jvm.db.aop;

import com.jvm.db.datasource.DS;
import com.jvm.db.datasource.DSNames;
import com.jvm.db.datasource.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 使用aop的方式获取注解 设置数据源
 * @author qindong
 * @date 2021/11/4 10:32
 */
@Slf4j
@Aspect
@Component
public class DSAspect implements Ordered {

    @Pointcut("@annotation(com.jvm.db.datasource.DS)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        DS ds = method.getAnnotation(DS.class);
        if (ds == null) {
            DynamicDataSource.setDataSource(DSNames.MASTER);
            log.info("set datasource is " + DSNames.MASTER);
        } else {
            DynamicDataSource.setDataSource(ds.name());
            log.info("set datasource is " + ds.name());
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
            log.info("clean datasource");
        }
    }


    @Override
    public int getOrder() {
        return 1;
    }
}
