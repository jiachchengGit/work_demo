package com.jccdemo.client.cxf;

import org.jccdemo.api.cxf.ICxfHelloService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCxfClient2 {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("cxf-client.xml");  
		ICxfHelloService service = ctx.getBean("cxfHello", ICxfHelloService.class);  
		System.out.println(service.sayHello("Cxf Client 2"));
	}
}
