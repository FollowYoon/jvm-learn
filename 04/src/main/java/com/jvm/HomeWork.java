package com.jvm;

import java.util.concurrent.CountDownLatch;

/**
 * 使用 CountDownLatch
 * @author qindong
 * @date 2021/10/9 15:48
 */
public class HomeWork {

    private static int result;

    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        new Thread(new readNum(countDownLatch)).start();
        countDownLatch.await();
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程
        System.out.println("主线程任务结束。。。");
    }

    static class readNum implements Runnable{

        private CountDownLatch countDownLatch;

        public readNum(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            synchronized (this){
                result = sum();
                countDownLatch.countDown();
            }
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
