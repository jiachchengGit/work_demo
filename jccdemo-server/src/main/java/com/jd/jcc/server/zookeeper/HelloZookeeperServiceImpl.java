package com.jd.jcc.server.zookeeper;

import org.jccdemo.api.zookeeper.IHelloZookeeperService;

public class HelloZookeeperServiceImpl implements IHelloZookeeperService {

	public String sayHello(String name) {
		return "Hello,"+name+",This is the zookeeper service !";
	}

}
