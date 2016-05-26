/**   
 * @Title: URLPermissionsFilter.java 
 * @Package com.jccdemo.web.shiro 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenjiacheng   
 * @date 2016年5月26日 下午8:24:57 
 * @version V1.0   
 */
package com.jccdemo.web.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

/**
 * @ClassName: URLPermissionsFilter
 * @Description: 自定义授权方式，这里通过比较用户是否能够访问该URL作为权限
 * @author chenjiacheng
 * @date 2016年5月26日 下午8:24:57
 * 
 */
public class URLPermissionsFilter extends PermissionsAuthorizationFilter {

	@Override
	public boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws IOException {
		return super.isAccessAllowed(request, response, buildUrlPermission(request));
	}

	/**
	 * @Description: 自定义权限，不再使用配置文件中的URL授权方式，
	 * 这里将URL作为权限，在Realm中通过查找用户可访问的url作为权限对比，实现动态授权 
	 * @Author chenjiacheng
	 * @Date 2016年5月26日 下午8:41:32
	 * @param request
	 * @return        
	 * @throws
	 */
	private String[] buildUrlPermission(ServletRequest request) {
		String[] perms = new String[1];
		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getServletPath();
		perms[0] = path;// path直接作为权限字符串
		System.out.println("path="+path);
		return perms;
	}
}
