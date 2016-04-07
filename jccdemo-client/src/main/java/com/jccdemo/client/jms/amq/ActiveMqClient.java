package com.jccdemo.client.jms.amq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.jccdemo.constant.JccConst;

public class ActiveMqClient {

	public static void main(String[] args) {
		Connection connection = null;  
        Session session = null;  
        try{
        	ConnectionFactory connFactory = new ActiveMQConnectionFactory(JccConst.ActiveMq.user, JccConst.ActiveMq.password, JccConst.ActiveMq.url);
        	connection = connFactory.createConnection();
        	connection.start();
        	
        	session = connection.createSession(JccConst.ActiveMq.transacted, Session.AUTO_ACKNOWLEDGE);
        	Queue dest = session.createQueue(JccConst.ActiveMq.queueName);
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
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
}
