package com.jccdemo.constant;

public class JccConst {
	public final static int port = 1999;
	public final static String url = "rmi://localhost:1999/com.remote.rmi.ServiceImpl";
	
	public static class ActiveMq{
		public static final String user = "system";  
	    public static final String password = "manager";  
	    public static final String url = "tcp://localhost:61616";  
	    public static final String queueName = "test_queue";  
	    public static final String messageBody = "Hello JMS for active Mq!";  
	    public static final boolean transacted = false;  
	    public static final boolean persistent = false;
	    public static final String topicName="test_topic";
	}
	
	public interface Zookeepers{
		 String ZK_CONNECTIED_URL="10.28.163.32:2181";
		 int ZK_SESSION_TIMEOUT=5000;
		 String ZK_ROOT_PATH="/registryroot";
	}
}
