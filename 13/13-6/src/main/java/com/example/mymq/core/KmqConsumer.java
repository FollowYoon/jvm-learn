package com.example.mymq.core;

/**
 * @author qindong
 * @date 2021/12/17 16:13
 */
public class KmqConsumer<T> {

    private final KmqBroker broker;

    private Kmq kmq;

    public KmqConsumer(KmqBroker broker) {
        this.broker = broker;
    }

    public void subscribe(String topic) {
        this.kmq = this.broker.findKmq(topic);
        if (null == kmq) throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
    }

    public KmqMessage<T> poll(long timeout) {
        return kmq.poll(timeout);
    }

    public KmqMessage<T> poll() {
        return kmq.poll();
    }

}
