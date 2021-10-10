package com.jvm;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 使用 BlockingQueue
 * @author qindong
 * @date 2021/10/9 15:48
 */
public class HomeWork03 {

    private static int result;

    public static void main(String[] args) {
        BlockingQueue queue = new ArrayBlockingQueue(1);
        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Thread t = new Thread(new readNum(queue));
        t.start();
        try{
            queue.take();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程
        System.out.println("主线程任务结束。。。");
    }

    static class readNum implements Runnable{

        private BlockingQueue queue;

        public readNum(BlockingQueue queue){
            this.queue = queue;
        }

        @Override
        public void run() {
            result = sum();
            try{
                queue.put("finish");
            }catch (InterruptedException e){
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
