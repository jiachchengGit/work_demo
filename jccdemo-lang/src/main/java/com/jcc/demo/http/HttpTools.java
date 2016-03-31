/**   
* @Title: HttpTools.java 
* @Package com.jcc.demo.http 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月31日 上午9:18:28 
* @version V1.0   
*/
package com.jcc.demo.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

/** 
 * @ClassName: HttpTools 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月31日 上午9:18:28 
 *  
 */
public class HttpTools {
	public static String CHARSET="UTF-8";

	public static HttpResult excutePostMethod(String basePath, Map<String, String> params, Cookie[] reqCookies, List<Header> headers) throws Exception{
		HttpResult ret = new HttpResult();
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(basePath);
		if(headers != null){
			for(Header header:headers){
				method.addRequestHeader(header);
			}
		}
		//设置参数
		if(params != null){
			NameValuePair[] nameValuePairs = new NameValuePair[params.size()];
			int i = 0;
			for(Iterator<Map.Entry<String, String>> it = params.entrySet().iterator(); it.hasNext();){
				Map.Entry<String, String> entry = it.next();
				nameValuePairs[i++] = new NameValuePair(entry.getKey(), entry.getValue());
				
			}
			method.setRequestBody(nameValuePairs);
		}
		
		if(reqCookies != null){
			 HttpState state = new HttpState();
			 state.addCookies(reqCookies);
			 client.setState(state);
		}
		try {
			int status = client.executeMethod(method);
			
			if (status != org.apache.commons.httpclient.HttpStatus.SC_OK) {
				throw new RuntimeException("调用接口出错[url:" + basePath + "],status="+status);
			}else{
				Cookie[] cookies = client.getState().getCookies();
				String body = new String(method.getResponseBody(),CHARSET);
				ret.setBody(body);
				ret.setCookies(cookies);
			}
		} finally {
			method.releaseConnection();
		}
		return ret;
	}
	
	public static HttpResult excuteGetMethod(String basePath, Map<String, String> params, Cookie[] reqCookies, List<Header> headers) throws Exception{
		String uri = assemblyGetMethodURI(basePath,params);
		HttpResult methodHttpResult = getMethodHttpResult(uri,reqCookies,headers);
		return methodHttpResult;
	}
	private static HttpResult getMethodHttpResult(String uri,Cookie[] reqCookies, List<Header> headers) throws Exception{
		HttpResult ret = new HttpResult();
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(uri);
		if(headers != null){
			for(Header header:headers){
				method.addRequestHeader(header);
			}
		}
		if(reqCookies != null){
			 HttpState state = new HttpState();
			 state.addCookies(reqCookies);
			 client.setState(state);
		}
		try {
			int status = client.executeMethod(method);
			
			if (status != org.apache.commons.httpclient.HttpStatus.SC_OK) {
				throw new RuntimeException("调用接口出错[uri:" + uri + "],status="+status);
			}else{
				Cookie[] cookies = client.getState().getCookies();
				String body =  new String(method.getResponseBody(),CHARSET);
				ret.setBody(body);
				ret.setCookies(cookies);
			}
		} finally {
			method.releaseConnection();
		}
		return ret;
	}
	
	private static String assemblyGetMethodURI(String basePath,Map<String, String> params){
		if(params != null && !params.isEmpty()){
			if(!basePath.endsWith("?")){
				basePath = basePath+"?";
			}
			StringBuffer ps = new StringBuffer();
			if(params != null){
				Set<Entry<String, String>> entrySet = params.entrySet();
				try {
					for(Entry<String, String> e:entrySet){
						ps.append(e.getKey()+"="+URLEncoder.encode(e.getValue(),CHARSET));
						ps.append("&");
					}
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				basePath = basePath +ps.toString();
			}
			basePath=basePath.substring(0,basePath.length()-1);
		}
		return basePath;
	}
}
