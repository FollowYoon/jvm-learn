package com.jvm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 使用 Future
 * @author qindong
 * @date 2021/10/9 15:48
 */
public class HomeWork05 {

    private static int result;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Thread t = new Thread(new readNum());
        Future future = executorService.submit(t);
        try{
            future.get();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程
        System.out.println("主线程任务结束。。。");
    }

    static class readNum implements Runnable{

        @Override
        public void run() {
            result = sum();
        }
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2){
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }

}
