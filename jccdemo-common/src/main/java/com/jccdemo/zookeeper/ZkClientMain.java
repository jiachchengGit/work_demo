package com.jccdemo.zookeeper;

import java.util.List;

import org.jccdemo.api.zookeeper.IHelloZookeeperService;

import com.jccdemo.utils.JccConst;

public class ZkClientMain {

	public static void main(String[] args) {
		ZkServerConsumer consumer = new ZkServerConsumer();
		String fullPath = JccConst.Zookeepers.ZK_ROOT_PATH + "/"
				+ IHelloZookeeperService.class.getName();
		consumer.setNodeWather(fullPath);
		for (int i = 0; i < 10; i++) {
			List<String> list = consumer.lookup(fullPath);
			System.out.println(i+"="+list);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
		}
	}
}
