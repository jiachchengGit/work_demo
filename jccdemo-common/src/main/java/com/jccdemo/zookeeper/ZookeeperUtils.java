package com.jccdemo.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jccdemo.utils.JccConst;

public class ZookeeperUtils {
	private static Logger log = LoggerFactory.getLogger(ZookeeperUtils.class);
	public static ZooKeeper connectServer(String connectUrl){
		 ZooKeeper zk = null;
		try{
			zk = new ZooKeeper(connectUrl, JccConst.Zookeepers.ZK_SESSION_TIMEOUT, new Watcher(){
				public void process(WatchedEvent event) {
					log.info("create zookeeper,enent type="+event.getType().toString());
				}
			});
		}catch(Exception e){
			log.error("Create zookeeper instance error !",e);
		}
		return zk;
	}
}
