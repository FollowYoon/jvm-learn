package com.example.mymq.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qindong
 * @date 2021/12/17 16:13
 */
public class KmqBroker {

    public static final int CAPACITY = 10000;

    private final Map<String, Kmq> kmqMap = new ConcurrentHashMap<>(64);

    public void createTopic(String name){
        kmqMap.putIfAbsent(name, new Kmq(name,CAPACITY));
    }

    public Kmq findKmq(String topic) {
        return this.kmqMap.get(topic);
    }

    public KmqProducer createProducer() {
        return new KmqProducer(this);
    }

    public KmqConsumer createConsumer() {
        return new KmqConsumer(this);
    }

}
