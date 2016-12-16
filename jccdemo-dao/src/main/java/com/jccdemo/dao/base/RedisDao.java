package com.jccdemo.dao.base;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenjiacheng on 2016-12-14.
 */

@Service
public class RedisDao implements IRedisDao{

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public String get(final String key) {
        Object execute = redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection conn) throws DataAccessException {
                byte[] bytes = conn.get(key.getBytes());
                if (bytes != null){
                    return new String(bytes);
                }
                return  null;
            }
        });
        return execute==null?null:execute.toString();
    }

    @Override
    public Boolean set(final String key, final String value) {
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key.getBytes(),value.getBytes());
                return true;
            }
        });
        return true;
    }

    @Override
    public Boolean hSet(final String key,final  String field,final  String value) {
        Object execute = redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                Boolean aBoolean = connection.hSet(key.getBytes(), field.getBytes(), value.getBytes());
                return aBoolean;
            }
        });
        return execute == null?false:(Boolean)execute;
    }

    @Override
    public String getSet(final  String key, final  String value) {
        Object execute = redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] set = connection.getSet(key.getBytes(), value.getBytes());
                if(set != null){
                    return new String(set);
                }
                return  null;
            }
        });
        return execute == null?null:execute.toString();
    }

    @Override
    public Long del(final String... keys) {
        Object execute = redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[][] bytes = new byte[keys.length][];
                for (int i = 0; i < keys.length; i++) {
                    bytes[i] = keys[i].getBytes();
                }
                Long del = connection.del(bytes);
                return del;
            }
        });
        return execute==null?0:(Long)execute;
    }

    @Override
    public Boolean exists(final  String key) {
        Object o = redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                Boolean exists = connection.exists(key.getBytes());
                return exists;
            }
        });
        return o==null?false:(Boolean)o;
    }

    @Override
    public void rename(final  String oldName,final String newName) {
            redisTemplate.execute(new RedisCallback() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    connection.rename(oldName.getBytes(),newName.getBytes());
                    return null;
                }
            });
    }

    @Override
    public Boolean expire(final String key, final long seconds) {
        Object o = redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                Boolean expire = connection.expire(key.getBytes(), seconds);
                return expire;
            }
        });
        return o==null?false:(Boolean)o;
    }

    @Override
    public void setEx(final String key,final  long time,final  String value) {
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.setEx(key.getBytes(),time,value.getBytes());
                return null;
            }
        });
    }

    @Override
    public List<String> mGet(final String... keys) {
        Object execute = redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[][] bytes = new byte[keys.length][];
                for (int i = 0; i < keys.length; i++) {
                    bytes[i] = keys[i].getBytes();
                }
                List<byte[]> bytes1 = connection.mGet(bytes);

                if (bytes != null && bytes1.size() > 0) {
                    List<String> list = new ArrayList<String>();
                    for (byte[] bs : bytes1) {
                        list.add(new String(bs));
                    }
                    return list;
                }
                return null;
            }
        });
        return execute == null?null:(List)execute;
    }

    @Override
    public void mSet(final Map<String, String> tuples) {
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                if (tuples != null && !tuples.isEmpty()){
                    Map<byte[],byte[]> map = new HashMap<byte[], byte[]>();
                    for (Map.Entry<String ,String> s:tuples.entrySet()) {
                        map.put(s.getKey().getBytes(),s.getValue().getBytes());
                    }
                    connection.mSet(map);
                }
                return null;
            }
        });
    }

    @Override
    public String hGet(final String key,final String field) {
        Object execute = redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] bytes = connection.hGet(key.getBytes(), field.getBytes());
                if (bytes != null) {
                    return new String(bytes);
                }
                return null;
            }
        });
        return execute== null?null:execute.toString();
    }
}
