/**   
* @Title: BeanXmlUtils.java 
* @Package utils 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2015年10月21日 下午3:10:57 
* @version V1.0   
*/
package com.jcc.demo.fileutils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/** 
 * @ClassName: BeanXmlUtils 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2015年10月21日 下午3:10:57 
 *  
 */
public class BeanXmlUtils {
	private static Logger log = LoggerFactory.getLogger(BeanXmlUtils.class);

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2015年10月21日 下午3:58:21
	 * @param obj 保存对象
	 * @param path  保存路径     
	 * @throws
	 */
	public static void beanToXml(Object obj,String path) {
		FileOutputStream fos=null;
        try {
        	fos = new FileOutputStream(path);
			JAXBContext context = JAXBContext.newInstance(obj.getClass());  
        	Marshaller marshaller = context.createMarshaller();  
			marshaller.marshal(obj, fos);
		} catch (JAXBException e) {
			log.error("",e);
		} catch (FileNotFoundException e) {
			log.error("",e);
		}finally{
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2015年10月21日 下午3:49:51
	 * @param obj
	 * @param path
	 * @return        
	 * @throws 
	 */
	public static Object xmlToBean(Object obj, String path) {
		FileInputStream fis = null;
		Object result = null;
		try {  
			fis = new FileInputStream(path);
            JAXBContext context = JAXBContext.newInstance(obj.getClass());  
            Unmarshaller unmarshaller = context.createUnmarshaller();  
           result =unmarshaller.unmarshal(fis);  
        } catch (JAXBException e) {  
           log.error("",e); 
        } catch (FileNotFoundException e) {
			log.error("",e);
		}finally{
        	if(fis != null){
        		try {
					fis.close();
				} catch (IOException e) {
				}
        	}
        }
		return result;
	}
}
