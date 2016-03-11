package com.jd.jcc.server.jws;

import javax.jws.WebService;

import org.jccdemo.api.jws.IJwsHelloService;

@WebService(endpointInterface="org.jccdemo.api.jws.IJwsHelloService",portName="JwsHelloServicePort"
,serviceName="JwsHelloService",targetNamespace="http://server.jws.jd.com/")
public class JwsHelloServiceImpl implements IJwsHelloService {
	public String sayHello(String name) {
		return "Hello,"+name+",this is jws service !";
	}
}
