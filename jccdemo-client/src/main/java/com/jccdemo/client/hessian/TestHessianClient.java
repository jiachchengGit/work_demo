package com.jccdemo.client.hessian;

import java.net.MalformedURLException;

import org.jccdemo.api.hessian.IHessianHelloService;

import com.caucho.hessian.client.HessianProxyFactory;

public class TestHessianClient {

	public static void main(String[] args){
		String url = "http://localhost:8080/jccdemo-server/hessian/hello.action";  
        HessianProxyFactory factory = new HessianProxyFactory();  
        IHessianHelloService hello = null;  
        try {  
            hello = (IHessianHelloService) factory.create(IHessianHelloService.class, url);  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        }  
        System.out.println(hello.sayHello()); 
	}
}
