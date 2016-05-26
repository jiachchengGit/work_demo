/**   
* @Title: LoginController.java 
* @Package com.jccdemo.client.controller.login 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年5月26日 下午2:32:36 
* @version V1.0   
*/
package com.jccdemo.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jccdemo.web.service.login.LoginService;

/** 
 * @ClassName: LoginController 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年5月26日 下午2:32:36 
 *  
 */
@Controller
public class LoginController {
	private Logger log = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/login.do")
	public String login(){
		System.out.println("++++++Login+++++Test++++++");
		return "/login";
	}
	
	@RequestMapping("/doLogin.do")
	public String doLogin(String userName,String passwd){
		log.info("登录信息：userName={},passwd={}",new String[]{userName,passwd});
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken upt =new UsernamePasswordToken();
		upt.setUsername(userName);
		upt.setPassword(passwd.toCharArray());
		subject.login(upt);
		boolean authenticated = subject.isAuthenticated();
		System.out.println("authenticated="+authenticated);
		return "/login-success";
	}
	
	@RequestMapping("/testurl.do")
	public String testURL(){
		return "/test";
	}
}
