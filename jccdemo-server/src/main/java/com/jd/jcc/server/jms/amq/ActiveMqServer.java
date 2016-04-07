package com.jd.jcc.server.jms.amq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.jccdemo.constant.JccConst;

public class ActiveMqServer {

	public static void main(String[] args){
		Connection connection = null;  
        Session session = null;  
		
        try{
        	ConnectionFactory connFactory = new ActiveMQConnectionFactory(JccConst.ActiveMq.user, JccConst.ActiveMq.password, JccConst.ActiveMq.url);
        	connection = connFactory.createConnection();
        	connection.start();
        	
        	session = connection.createSession(JccConst.ActiveMq.transacted, Session.AUTO_ACKNOWLEDGE);
        	Queue dest = session.createQueue(JccConst.ActiveMq.queueName);
        	MessageProducer producer = session.createProducer(dest);
        	if(JccConst.ActiveMq.persistent){
        		producer.setDeliveryMode(DeliveryMode.PERSISTENT);  
        	}else{
        		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);  
        	}
        	 // create text message  
            Message message = session.createTextMessage(JccConst.ActiveMq.messageBody);  
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
