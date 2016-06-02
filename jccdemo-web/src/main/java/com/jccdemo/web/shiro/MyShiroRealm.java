/**   
* @Title: MyShiroRealm.java 
* @Package com.jccdemo.client.shiro 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年5月26日 上午11:04:04 
* @version V1.0   
*/
package com.jccdemo.web.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @ClassName: MyShiroRealm 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年5月26日 上午11:04:04 
 *  
 */
public class MyShiroRealm extends AuthorizingRealm {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		String name = getName();
		String userName = (String) pc.fromRealm(name).iterator().next();
		log.info("[授权信息：user={}]",new String[]{userName});
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRole("jcc");
		info.addStringPermission("/testurl.do");//对用户授权，可与自定义的授权过滤器配合使用，如：URLPermissionsFilter.java
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = token.getPrincipal().toString();
		Object credentials = token.getCredentials();
		log.info("[登录信息：user={},passwd={}]",new String[]{userName,credentials.toString()});
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName,"aaa",getName());
		return info;
	}

}
