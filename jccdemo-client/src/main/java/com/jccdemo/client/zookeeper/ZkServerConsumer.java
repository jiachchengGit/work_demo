package com.jccdemo.client.zookeeper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jccdemo.constant.JccConst;

public class ZkServerConsumer {
	private Logger log = LoggerFactory.getLogger(ZkServerConsumer.class);
	private ZooKeeper zk = null;
	private Map<String,List<String>> map = new HashMap<String,List<String>>();
	
	public ZkServerConsumer(){
		zk = ZookeeperUtils.connectServer(JccConst.Zookeepers.ZK_CONNECTIED_URL);
	}
	public void setNodeWather(String fullPath){
			setWatcher(fullPath);
	}
	private void setWatcher(final String fullPath){
		try {
			List<String> children = zk.getChildren(fullPath, new Watcher(){
				public void process(WatchedEvent event) {
					log.info("执行节点Wathcer事件");
					if(event.getType() == Event.EventType.NodeChildrenChanged){
						setWatcher(fullPath);
					}
				}
			});
			
			map.put(fullPath, children);
			log.info("children="+children);
		} catch (Exception e) {
			log.error("获取子节点错误",e);
		}
	}
	
	public List<String> lookup(String fullPath){
		return map.get(fullPath);
	}
}
