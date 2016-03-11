package com.jccdemo.client.burlap;

import java.net.MalformedURLException;

import org.jccdemo.api.burlap.IBurlapHelloService;

import com.caucho.burlap.client.BurlapProxyFactory;

public class TestBurlapClient {

	public static void main(String[] args) throws MalformedURLException {
		String url = "http://localhost:8080/jccdemo-server/burlap/hello.action";  
		BurlapProxyFactory bpf = new BurlapProxyFactory();
		IBurlapHelloService bh = null;
		bh = (IBurlapHelloService) bpf.create(IBurlapHelloService.class,url);
		System.out.println(bh.sayHello());
	}

}
