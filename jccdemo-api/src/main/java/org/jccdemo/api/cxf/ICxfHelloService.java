package org.jccdemo.api.cxf;

import javax.jws.WebService;

@WebService
public interface ICxfHelloService {
	public String sayHello(String name);
}
