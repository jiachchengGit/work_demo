package com.jccdemo.client.jms.amq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.jccdemo.utils.JccConst;

public class ActiveMqTopicClient {

	public static void main(String[] args) {
		Connection connection = null;  
        Session session = null;  
        try{
        	ConnectionFactory connFactory = new ActiveMQConnectionFactory(JccConst.ActiveMq.user, JccConst.ActiveMq.password, JccConst.ActiveMq.url);
        	connection = connFactory.createConnection();
        	connection.start();
        	
        	session = connection.createSession(JccConst.ActiveMq.transacted, Session.AUTO_ACKNOWLEDGE);
        	Topic dest = session.createTopic(JccConst.ActiveMq.topicName);
        	MessageConsumer consumer = session.createConsumer(dest);
        	consumer.setMessageListener(new MessageListener() {
				public void onMessage(Message m) {
					String text;
					try {
						text = ((TextMessage)m).getText();
						System.out.println("Receive Message: "+text);
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
        	
        	MessageConsumer consumer2 = session.createConsumer(dest);
        	consumer2.setMessageListener(new MessageListener() {
				public void onMessage(Message m) {
					String text;
					try {
						text = ((TextMessage)m).getText();
						System.out.println("Receive Message 2: "+text);
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
}
