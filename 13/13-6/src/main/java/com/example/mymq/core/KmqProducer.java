package com.example.mymq.core;

/**
 * @author qindong
 * @date 2021/12/17 16:14
 */
public class KmqProducer {

    private KmqBroker broker;

    public KmqProducer(KmqBroker broker) {
        this.broker = broker;
    }

    public boolean send(String topic, KmqMessage message) {
        Kmq kmq = this.broker.findKmq(topic);
        if (null == kmq) throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
        return kmq.send(message);
    }

}
