package com.jccdemo.zkclient;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by chenjiacheng on 2016/12/9.
 */
public class ZkNodeWatcher implements Watcher {

    private Logger log = LoggerFactory.getLogger(getClass());
    private ZooKeeper zooKeeper;
    private String selfPath;

    public ZkNodeWatcher(ZooKeeper zooKeeper, String selfPath){
        this.zooKeeper = zooKeeper;
        this.selfPath =selfPath;
    }

    public void process(WatchedEvent event) {
        Event.KeeperState state = event.getState();
        Event.EventType type = event.getType();
        try {
            log.info("------#Zookeeper NodeWather,state={},type={}",state,type);
            if(Event.EventType.NodeChildrenChanged == type){
                List<String> paths = zooKeeper.getChildren(event.getPath(), this);
//                WangKuoGpsUtil.sortAndCalcPath(selfPath,paths);
            }
        } catch (Exception e) {
           log.error("------#处理时间异常",e);
        }
    }
}

