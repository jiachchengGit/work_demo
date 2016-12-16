package com.jccdemo.zkclient;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by chenjiacheng on 2016/12/9.
 */
@Component
public class ZkClientManager implements Watcher,InitializingBean {
    private Logger log = LoggerFactory.getLogger(getClass());

    private final String ROOT_PATH = "/car_position";
    private final String SUB_PATH = ROOT_PATH + "/sub_system_";

    @Value("${zookeeper_address}")
    public String CONNECT_PATH = "127.0.0.1:2181";
    private Integer SESSION_TIME_OUT = 10000;

    private ZooKeeper zk = null;
    private String SELF_PATH = null;

    private boolean createRootPath(String data) throws KeeperException, InterruptedException {
        if (zk.exists(ROOT_PATH, true) == null) {
            String rootpath = zk.create(ROOT_PATH, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            log.info("------#创建根节点成功rootpath：" + rootpath);
        } else {
            log.info("------#根路径节点已经存在，不在重复创建");
        }
        return true;
    }

    private boolean createSubNode() throws KeeperException, InterruptedException {
        SELF_PATH = zk.create(SUB_PATH, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        log.info("------#创建当前zk节点路径成功，path=" + SELF_PATH);
        List<String> paths = zk.getChildren(ROOT_PATH, new ZkNodeWatcher(zk, SELF_PATH));
//        WangKuoGpsUtil.sortAndCalcPath(SELF_PATH,paths);
        return true;
    }

    private void deletePath() {
        try {
            zk.delete(SELF_PATH, -1);
            log.info("-------#删除PATH=" + SELF_PATH);
        } catch (Exception e) {
            log.error("------#删除zk节点路径异常",e);
        }
    }

    private void releaseConn() {
        if (zk != null) {
            try {
                zk.close();
            } catch (InterruptedException e) {
                log.error("------#zookeeper关闭连接异常");
            }
        }
    }

    public void process(WatchedEvent event) {
        Event.KeeperState state = event.getState();
        Event.EventType type = event.getType();
        if (Event.KeeperState.SyncConnected == state) {
            if (Event.EventType.None == type) {
                log.info("------#成功连接上zk服务器");
            }
        } else if (Event.KeeperState.Disconnected == state) {
            log.info("------#与ZK服务器断开连接");
        } else if (Event.KeeperState.Expired == state) {
            log.info("会话失效");
        }
    }

    public void afterPropertiesSet() throws Exception {
        log.info("------#Zookeeper连接服务器地址{}，timeout={}",CONNECT_PATH,SESSION_TIME_OUT);
        zk = new ZooKeeper(CONNECT_PATH, SESSION_TIME_OUT, this);
        log.info("------#初始化zookeeper连接成功");
        createRootPath("");
        createSubNode();
    }
}

