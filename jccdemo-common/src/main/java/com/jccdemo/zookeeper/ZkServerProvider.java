package com.jccdemo.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jccdemo.utils.JccConst;


public class ZkServerProvider {
	
	private Logger log = LoggerFactory.getLogger(ZkServerProvider.class);
	
	private ZooKeeper zk = null;
	
	public ZkServerProvider(){
		zk = ZookeeperUtils.connectServer(JccConst.Zookeepers.ZK_CONNECTIED_URL);
		//创建根节点
		Stat exists;
		try {
			exists = zk.exists(JccConst.Zookeepers.ZK_ROOT_PATH, false);
			if(exists == null){
				createNode(JccConst.Zookeepers.ZK_ROOT_PATH, "root node".getBytes(), CreateMode.PERSISTENT);
			}
		} catch (Exception e) {
			log.error("判断节点是否存在",e);
		}
	}
	
	public void registerServer(String fullMethodPath,String serverIp){
		String pathMethod = JccConst.Zookeepers.ZK_ROOT_PATH+"/"+fullMethodPath;
		try {
			if(zk.exists(pathMethod, true)==null){
				createNode(pathMethod, pathMethod.getBytes(), CreateMode.PERSISTENT);
			}
			String serverPath= pathMethod +"/"+serverIp;
			if(zk.exists(serverPath, true)==null){
				createNode(serverPath, serverPath.getBytes(), CreateMode.EPHEMERAL);
			}
		} catch (Exception e) {
			log.error("发布服务",e);
		}
	}
	
	public void createNode(String path,byte[] data,CreateMode createMode){
		try{
			String create = zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode);
			log.info("create node realpath="+create);
		}catch(Exception e){
			log.error("--创建节点错误",e);
		}
	}
}
