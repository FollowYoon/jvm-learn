package com.example.mymq.core;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @author qindong
 * @date 2021/12/17 16:12
 */
public class Kmq {

    public Kmq(String topic, int capacity) {
        this.topic = topic;
        this.capacity = capacity;
        this.queue = new MyQueue(capacity);
    }

    private String topic;

    private int capacity;

    private MyQueue queue;


    public boolean send(KmqMessage message) {
        return queue.enqueue(message);
    }

    public KmqMessage poll() {
        return (KmqMessage) queue.dequeue();
    }

    @SneakyThrows
    public KmqMessage poll(long timeout) {
        return (KmqMessage) queue.dequeue(timeout, TimeUnit.MILLISECONDS);
    }

}
