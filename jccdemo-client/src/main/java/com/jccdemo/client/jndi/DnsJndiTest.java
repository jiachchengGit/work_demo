/**   
* @Title: DnsJndiQueryTest.java 
* @Package com.jd.demo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2015年11月19日 下午5:39:11 
* @version V1.0   
*/
package com.jccdemo.client.jndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/** 
 * @ClassName: DnsJndiQueryTest 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2015年11月19日 下午5:39:11 
 *  
 */
public class DnsJndiTest {

	/**
	 * @throws NamingException  
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2015年11月19日 下午5:39:11
	 * @param args        
	 * @throws 
	 */
	public static void main(String[] args) throws NamingException {
		String domain = "www.jd.com";
		String dnsServer = "";
		//通过环境属性来指定Context的工厂类
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
		env.put(Context.PROVIDER_URL, "dns:" + dnsServer);
		DirContext ctx = new InitialDirContext(env);
		//分别获取包含所有属性和只包含Mx属性的Attributes对象
		Attributes attrsAll = ctx.getAttributes(domain);	
		Attributes attrsMx = ctx.getAttributes(domain, new String[]{"MX"});	
		
		System.out.println("打印出域" + domain + "的Attributes对象中的信息：");
		System.out.println(attrsAll);
		System.out.println("--------------------------");
		System.out.println("打印只检索域" + domain + "的MX记录的Attributes对象：");		
		System.out.println(attrsMx);
				
		System.out.println("--------------------------");
		System.out.println("逐一打印出Attributes对象中的各个属性：");			
		NamingEnumeration attributes = attrsAll.getAll();
		while(attributes.hasMore()) 
		{	
			System.out.println(attributes.next());
		}
				
		System.out.println("--------------------------");
		//直接调用get方法从attrsMx集合检索MX属性
		System.out.println("直接检索Attributes对象中的MX属性：");		
		Attribute attrMx = attrsAll.get("MX");
		System.out.println(attrMx);	
		System.out.println("--------------------------");			
		//获取Mx属性中的第一个值:
		System.out.println("获取Mx属性中的第一个值:");
		String recordMx = (String)attrMx.get();
		System.out.println(recordMx);
		//从Mx属性的第一个值中提取邮件服务器地址
		System.out.println("从MX属性值中提取的邮件服务器地址：");
		String smtpServer = recordMx.substring(recordMx.indexOf(" ") + 1);
		System.out.println(smtpServer);
	}
}
