/**   
* @Title: CollectionsTools.java 
* @Package com.jcc.demo.common 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenjiacheng   
* @date 2016年3月31日 上午9:54:45 
* @version V1.0   
*/
package com.jccdemo.common;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/** 
 * @ClassName: CollectionsTools 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月31日 上午9:54:45 
 *  
 */
public class CollectionsTools {
	
	public static <T> List<T> arrayToList(T[] array){
		List<T> list = new ArrayList<T>();
		if(array != null && array.length > 0){
			for(T o:array){
				list.add(o);
			}
		}
		return list;
	}
	
	public static <T> T[] listToArray(List<T> list){
		T[] array= null;
		if(list != null && list.size() > 0){
			int size = list.size();
			array = (T[]) Array.newInstance(list.get(0).getClass(), size);
			for(int i=0;i< size;i++){
				array[i]=list.get(i);
			}
		}
		return array;
	}
	
	public static void main(String[] args){
		List<String> list= new ArrayList<String>();
		list.add("112");
		String[] listToArray = listToArray(list);
		for(String s:listToArray){
			System.out.println(s);
		}
		System.out.println("================");
		List<String> arrayToList = arrayToList(listToArray);
		for(String s:arrayToList){
			System.out.println(s);
		}
	}
}
