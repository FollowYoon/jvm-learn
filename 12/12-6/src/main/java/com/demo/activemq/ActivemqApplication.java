package com.demo.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;

//@SpringBootApplication
public class ActivemqApplication {

	public static void main(String[] args) {
		//SpringApplication.run(ActivemqApplication.class, args);
		// 测试topic
		Destination destination = new ActiveMQTopic("testt.topic");
		testMq(destination);

		// 测试queue
		Destination destinationQueue = new ActiveMQQueue("testq.queue");
		testMq(destinationQueue);
	}

	public static void testMq(Destination destination){
		try{
			ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
			ActiveMQConnection connection = (ActiveMQConnection) factory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			MessageConsumer consumer = session.createConsumer(destination);
			final AtomicInteger count = new AtomicInteger(0);
			MessageListener listener = new MessageListener() {
				@Override
				public void onMessage(Message message) {
					try{
						System.out.println(count.incrementAndGet()+ ": =>receive message from " + destination.toString() + ": "+ message);
					}catch (Exception e){
						e.printStackTrace();
					}
				}
			};
			consumer.setMessageListener(listener);

			MessageProducer messageProducer = session.createProducer(destination);
			int index = 0;
			while (index++ < 100){
				TextMessage textMessage = session.createTextMessage(index + "message.");
				messageProducer.send(textMessage);
			}

			Thread.sleep(2000);
			session.close();
			connection.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
