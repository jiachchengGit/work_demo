/**   
* @Title: FileTools.java 
* @Package utils 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2015年10月13日 上午9:37:47 
* @version V1.0   
*/
package com.jcc.demo.fileutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @ClassName: FileTools 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2015年10月13日 上午9:37:47 
 *  
 */
public class FileTools {
	private static Logger log = LoggerFactory.getLogger(FileTools.class);
	
	public static interface FilterChars{
		public String filter(String src);
	}
	private static FilterChars defaultFilter = new FilterChars() {
		public String filter(String src) {
			return src;
		}
	};
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月11日 下午3:39:34
	 * @param string 字符串内容
	 * @param outpath
	 * @param append  是否追加文件内容标志
	 * @throws
	 */
	public static void writeFile(String string, String outpath,boolean append) {
		byte[] bytes = string.getBytes();
		writeFile(bytes,outpath, append);
	}

	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月11日 下午3:41:10
	 * @param outpath
	 * @param append
	 * @param bytes        
	 * @throws 
	 */
	public static void writeFile( byte[] bytes,String outpath, boolean append) {
		FileOutputStream os = null;
		try{
			os = new FileOutputStream(new File(outpath),append);
			os.write(bytes);
			os.flush();
		}catch(Exception e){
			log.error("",e);
		}finally{
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	/**
	 * @Description: read file lines into list 
	 * @Author chenjiacheng
	 * @Date 2016年3月11日 下午3:34:18
	 * @param path
	 * @param filter filter line
	 * @return        
	 * @throws
	 */
	public static List<String> readLine2List(String path,FilterChars filter){
		if(filter == null){
			filter = defaultFilter;
		}
		BufferedReader br = null; 
		List<String> list = new ArrayList<String>();
		try{
			 br =new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
		    String line = null;
		    while((line=br.readLine()) != null){
		    	String filter2 = filter.filter(line);
				list.add(filter2);
		    }
		}catch(Exception e){
			log.error("",e);
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
		return list;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月11日 下午3:37:12
	 * @param path
	 * @param filter
	 * @return        
	 * @throws
	 */
	public static String readLines(String path,FilterChars filter){
		if(filter == null){
			filter = defaultFilter;
		}
		BufferedReader br = null; 
		StringBuffer sb = new StringBuffer();
		try{
			 br =new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
		    String line = null;
		    while((line=br.readLine()) != null){
		    	String filter2 = filter.filter(line);
		    	sb.append(filter2);
		    	sb.append("\n");
		    }
		}catch(Exception e){
			log.error("",e);
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
		return sb.toString();
	}
}
