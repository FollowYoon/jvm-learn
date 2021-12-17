package com.example.mymq.core;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自定义内存数组模拟queue
 * @author qindong
 * @date 2021/12/17 16:33
 */
public class MyQueue {

    /**
     * 定义容量默认值
      */
    private static final int CAPACITY = 1024;

    /**
     * 定义容量
     */
    private static int capacity;

    /**
     * 队列的头
     */
    private static int front;
    /**
     * 队列的尾
     */
    private static int tail;
    /**
     * 队列中的数据
     */
    private static Object[] array;

    private final ReentrantLock takeLock = new ReentrantLock();

    private final AtomicInteger count = new AtomicInteger();

    private final Condition notEmpty = takeLock.newCondition();

    private final ReentrantLock putLock = new ReentrantLock();

    private final Condition notFull = putLock.newCondition();

    public MyQueue(){
        capacity = CAPACITY;
        array = new Object[capacity];
        front = tail = 0;
    }

    public MyQueue(int initCapacity) {
        capacity = initCapacity;
        array = new Object[capacity];
        front = tail = 0;
    }

    /**
     * 获取队列大小
     * @return
     */
    public static int getSize() {
        if (isEmpty())
            return 0;
        else
            return (capacity + tail - front) % capacity;
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public static boolean isEmpty() {
        return (front == tail);
    }

    /**
     * 向队列插入数据
     * @param element
     * @throws Exception
     */
    public boolean enqueue(Object element) {
        if (element == null) throw new NullPointerException();
        final AtomicInteger count = this.count;
        if (count.get() == capacity)
            return false;
        int c = -1;
        final ReentrantLock putLock = this.putLock;
        putLock.lock();
        try {
            if (count.get() < capacity) {
                array[tail] = element;
                tail = (tail + 1) % capacity;
                c = count.getAndIncrement();
                if (c + 1 < capacity)
                    notFull.signal();
            }
        } finally {
            putLock.unlock();
        }
        if (c == 0)
            signalNotEmpty();
        return c >= 0;
    }

    /**
     * 获取队列中的元素
     * @return
     * @throws Exception
     */
    public Object dequeue() {
        Object element;
        if (isEmpty())
            return null;
        element = array[front];
        front = (front + 1) % capacity;
        return element;
    }

    /**
     * 获取队列中的元素
     * @return
     * @throws Exception
     */
    public Object dequeue(long timeout, TimeUnit unit) throws InterruptedException {
        Object element = null;
        int c = -1;
        long nanos = unit.toNanos(timeout);
        AtomicInteger count = this.count;
        ReentrantLock takeLock = this.takeLock;
        takeLock.lockInterruptibly();
        try {
            while (count.get() == 0) {
                if (nanos <= 0)
                    return null;
                nanos = notEmpty.awaitNanos(nanos);
            }
            element = array[front];
            front = (front + 1) % capacity;
            c = count.getAndDecrement();
            if (c > 1)
                notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
        if (c == capacity)
            signalNotFull();
        return element;
    }

    private void signalNotFull() {
        final ReentrantLock putLock = this.putLock;
        putLock.lock();
        try {
            notFull.signal();
        } finally {
            putLock.unlock();
        }
    }

    private void signalNotEmpty() {
        final ReentrantLock takeLock = this.takeLock;
        takeLock.lock();
        try {
            notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
    }

}
