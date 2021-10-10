package com.jvm;

import java.util.concurrent.CyclicBarrier;

/**
 * 使用 CyclicBarrier
 * @author qindong
 * @date 2021/10/9 15:48
 */
public class HomeWork04 {

    private static int result;

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(2);
        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Thread t = new Thread(new readNum(barrier));
        t.start();
        try{
            barrier.await();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程
        System.out.println("主线程任务结束。。。");
    }

    static class readNum implements Runnable{

        private CyclicBarrier barrier;

        public readNum(CyclicBarrier barrier){
            this.barrier = barrier;
        }

        @Override
        public void run() {
            result = sum();
            try{
                barrier.await();
            }catch (Exception e){
                e.printStackTrace();
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
