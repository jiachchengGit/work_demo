package com.jd.jcc.server.jms.amq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.jccdemo.utils.JccConst;

public class ActiveMqTopicServer {

	public static void main(String[] args){
		Connection connection = null;  
        Session session = null;  
		
        try{
        	ConnectionFactory connFactory = new ActiveMQConnectionFactory(JccConst.ActiveMq.user, JccConst.ActiveMq.password, JccConst.ActiveMq.url);
        	connection = connFactory.createConnection();
        	connection.start();
        	
        	session = connection.createSession(JccConst.ActiveMq.transacted, Session.AUTO_ACKNOWLEDGE);
        	Topic topic = session.createTopic(JccConst.ActiveMq.topicName);
        	MessageProducer producer = session.createProducer(topic);
        	if(JccConst.ActiveMq.persistent){
        		producer.setDeliveryMode(DeliveryMode.PERSISTENT);  
        	}else{
        		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);  
        	}
        	 // create text message  
            Message message = session.createTextMessage("This is Topic message of active Mq !");  
            // send the message  
            producer.send(message);  
            System.out.println("Send message: " + ((TextMessage)message).getText());
            
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	if (session != null){  
                try {
					session.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}  
            } 
        	if(connection != null){
        		try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
        	}
        }
	}
}
