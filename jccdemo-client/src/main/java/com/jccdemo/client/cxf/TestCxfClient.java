package com.jccdemo.client.cxf;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.jccdemo.api.cxf.ICxfHelloService;

public class TestCxfClient {

	public static void main(String[] args) {
		JaxWsProxyFactoryBean jfb = new JaxWsProxyFactoryBean();
		jfb.setServiceClass(ICxfHelloService.class);
		jfb.setAddress("http://localhost:8080/jccdemo-server/cxf/hello?wsdl");
		ICxfHelloService ics = (ICxfHelloService)jfb.create();
		System.out.println(ics.sayHello("JCCC"));
	}

}
