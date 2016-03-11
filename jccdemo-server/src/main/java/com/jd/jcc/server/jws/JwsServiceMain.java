package com.jd.jcc.server.jws;

import javax.xml.ws.Endpoint;

import org.jccdemo.api.jws.IJwsHelloService;

public class JwsServiceMain {
	/**
	 * 引入cxf包会失败
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting JWS Server");  
		IJwsHelloService implementor = new JwsHelloServiceImpl();  
        Endpoint.publish(IJwsHelloService.address, implementor);  
	}
}
