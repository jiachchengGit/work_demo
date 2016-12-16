import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**   
 * @Title: TestRegex.java 
 * @Package  
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenjiacheng   
 * @date 2016年3月22日 下午5:03:55 
 * @version V1.0   
 */

/** 
 * @ClassName: TestRegex 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenjiacheng
 * @date 2016年3月22日 下午5:03:55 
 *  
 */
public class TestRegex {
	public static void main(String[] args){
		String pt= "<(input.+?code=\".+?\".+?)/>";
		String src = "<label style=\"background:#D1EEEE\"><label>电话号码</label><input readonly=\"readonly\"  code=\"widget_phone\" type=\"text\" name=\"widget_phone\" value=\"15288896532\"/></label>";
		Matcher m = Pattern.compile(pt).matcher(src);
		while(m.find()){
			String group = m.group();
			String group1 = m.group(1);
			System.out.println(group);
			System.out.println(group1);
			String code = getCode(group1);
		}
	}

	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @Author chenjiacheng
	 * @Date 2016年3月22日 下午5:14:06
	 * @param group1
	 * @return        
	 * @throws 
	 */
	private static String getCode(String src) {
		String pt= "code=\"(.+?)\"";
		Matcher m = Pattern.compile(pt).matcher(src);
		while(m.find()){
			String group = m.group();
			String group1 = m.group(1);
			System.out.println(group);
			System.out.println("---"+group1);
		}
		return null;
	}
}
