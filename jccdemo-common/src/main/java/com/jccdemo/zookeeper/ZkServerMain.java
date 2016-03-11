package com.jccdemo.zookeeper;

import org.jccdemo.api.zookeeper.IHelloZookeeperService;

public class ZkServerMain {
	public static void main(String[] args) {
		ZkServerProvider sp = new ZkServerProvider();
		for (int i = 0; i < 15; i++) {
			sp.registerServer(IHelloZookeeperService.class.getName(), "1111."+i);
			System.out.println(i + ",服务发布完成....");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
