package com.java.redislock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author qindong
 * @date 2021/11/30 15:48
 */
@Component
public class Lock {

    private Lock(){}

    private static class LockInstance{
        private static final Lock INSTANCE = new Lock();
    }

    public static Lock getInstance(){
        return LockInstance.INSTANCE;
    }

    @Autowired
    ApplicationContext applicationContext;
    @PostConstruct
    public void init() {
        stringRedisTemplate = applicationContext.getBean(StringRedisTemplate.class);
    }

    static StringRedisTemplate stringRedisTemplate;

    public Boolean getLock(String key, String value, int lockTime){
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value, lockTime, TimeUnit.SECONDS);
    }

    public boolean releaseLock(String key){
        String luaScript = "if redis.call('get',KEYS[1]) == ARGV[1] then " + "return redis.call('del',KEYS[1]) else return 0 end";
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript(luaScript);
        redisScript.setResultType(Long.class);
        Long res = stringRedisTemplate.execute(redisScript, Collections.singletonList(key));
        return Long.valueOf(1).equals(res);
    }


}
