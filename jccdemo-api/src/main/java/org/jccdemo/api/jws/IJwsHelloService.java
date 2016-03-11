package org.jccdemo.api.jws;

import javax.jws.WebService;

@WebService
public interface IJwsHelloService {
	public static String address="http://localhost:8081/hellojws";
	public String sayHello(String name);
}
