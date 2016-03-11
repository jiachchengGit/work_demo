package com.jd.jcc.server.cxf;

import org.jccdemo.api.cxf.ICxfHelloService;

public class CxfHelloServiceImpl implements ICxfHelloService {
	public String sayHello(String name) {
		return "Hello,"+name+",this is the cxf world !";
	}
}
