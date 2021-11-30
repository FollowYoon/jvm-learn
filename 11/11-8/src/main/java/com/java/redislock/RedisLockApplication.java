package com.java.redislock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisLockApplication {

    private final static String LOCK = "redis_lock";

    private final static int EXPIRE = 10;

    private static int COUNT = 5;

    public static void test(){
        System.out.println("----lock start");
        Lock lock = Lock.getInstance();
        if (!lock.getLock(LOCK, LOCK, EXPIRE)) {
            System.out.println("获取锁异常！");
            return;
        }
        try{
            Thread.sleep(5000);
            COUNT = COUNT - 1;
            System.out.println("库存减一，最新的库存为:" + COUNT);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        lock.releaseLock(LOCK);
        System.out.println("----lock end");
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisLockApplication.class, args);

        try{
            Thread thread1 = new Thread(RedisLockApplication::test);
            Thread thread2 = new Thread(RedisLockApplication::test);
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

}
