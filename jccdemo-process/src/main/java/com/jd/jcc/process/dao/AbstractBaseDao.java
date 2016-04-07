/**   
 * @Title: AbstractBaseDao.java 
 * @Package com.jd.jcc.process.dao 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenjiacheng   
 * @date 2016年4月7日 下午2:24:04 
 * @version V1.0   
 */
package com.jd.jcc.process.dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * @ClassName: AbstractBaseDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author chenjiacheng
 * @date 2016年4月7日 下午2:24:04
 * 
 */
public abstract class AbstractBaseDao {
	
	private static SqlMapClient sqlMapClient;
	
	static {
		try {
			Reader reader = Resources.getResourceAsReader("IbatisSqlMapConfig.xml");
			sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public abstract String getNameSpace();
	
	/**
	 * @return the sqlMapClient
	 */
	public static SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}
}
