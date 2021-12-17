package com.example.springkafka.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author qindong
 * @date 2021/12/17 15:46
 */
@Component
public class Consumer {

    /**
     * 消费监听
     * @param record
     */
    @KafkaListener(topics = {"test32"})
    public void onMessage1(ConsumerRecord<?, ?> record){
        // 消费的哪个topic、partition的消息,打印出消息内容
        System.out.println("消费："+record.topic()+"-"+record.partition()+"-"+record.value());
    }

}
