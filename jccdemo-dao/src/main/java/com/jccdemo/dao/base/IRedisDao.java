package com.jccdemo.dao.base;

import java.util.List;
import java.util.Map;

/**
 * Created by chenjiacheng on 2016-12-14.
 */
public interface IRedisDao {
    public String get(final String key);
    public  Boolean set(final String key, final String value);
    public Boolean hSet(final String key, final String field, final String value);
    public String getSet(final String key, final String value);
    public Long del(final String... keys);
    public Boolean exists(final String key);
    public void rename(final String oldName, final String newName);
    public Boolean expire(final String key, final long seconds);
    public void setEx(final String key, final long time, final String value);
    public List<String> mGet(final String... keys);
    public void mSet(final Map<String, String> tuples);
    public String hGet(final String key, final String field);
}
