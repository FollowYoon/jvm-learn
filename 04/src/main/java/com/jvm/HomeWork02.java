package com.jvm;

/**
 * 使用 synchronized
 * @author qindong
 * @date 2021/10/9 15:48
 */
public class HomeWork02 {

    private static int result;

    public static void main(String[] args) {
        Object lock = new Object();
        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Thread t = new Thread(new readNum(lock));
        t.start();
        try{
            synchronized (lock){
                // 主线程等待
                lock.wait();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程
        System.out.println("主线程任务结束。。。");
    }

    static class readNum implements Runnable{

        private Object lock;

        public readNum(Object lock){
            this.lock = lock;
        }

        @Override
        public void run() {
            result = sum();
            synchronized (lock){
                // 子线程唤醒
                lock.notify();
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
