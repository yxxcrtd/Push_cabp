package cn.digitalpublishing.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间操作工具类
 * @author yul
 *
 */
public class DateUtil {
	public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * @param date 需要转换的时间
	 * @return 格式化到秒的时间字符串
	 */
	public static String getStrDate(Date date){
		return df.format(date);
	}
}
